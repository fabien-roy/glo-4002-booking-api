package ca.ulaval.glo4002.booking.endToEnd;

import ca.ulaval.glo4002.booking.constants.DateConstants;
import ca.ulaval.glo4002.booking.constants.PassConstants;
import ca.ulaval.glo4002.booking.constants.RepositoryConstants;
import ca.ulaval.glo4002.booking.constants.VendorConstants;
import ca.ulaval.glo4002.booking.controllers.OrderController;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.dto.PassesDto;
import ca.ulaval.glo4002.booking.entities.OrderEntity;
import ca.ulaval.glo4002.booking.entities.PassEntity;
import ca.ulaval.glo4002.booking.parsers.OrderParser;
import ca.ulaval.glo4002.booking.repositories.*;
import ca.ulaval.glo4002.booking.services.*;
import ca.ulaval.glo4002.booking.util.FestivalDateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.Mockito.mock;

public class OrderEndToEndContext {

    private static final LocalDateTime AN_ORDER_DATE = DateConstants.ORDER_START_DATE_TIME;
    private static final LocalDateTime ANOTHER_ORDER_DATE = DateConstants.ORDER_START_DATE_TIME.plusDays(1);
    private static final LocalDate A_VALID_EVENT_DATE = DateConstants.START_DATE.plusDays(1L);
    private static final Long A_VENDOR_ID = VendorConstants.TEAM_VENDOR_ID;
    private static final String A_VENDOR_CODE = VendorConstants.TEAM_VENDOR_CODE;
    public static final Long AN_INVALID_ORDER_ID = -1L;

    private EntityManager entityManager;
    public OrderEntity anOrder;
    public OrderEntity anotherOrder;
    public PassEntity aPass;
    public PassEntity anotherPass;
    public OrderWithPassesAsEventDatesDto anOrderDto = new OrderWithPassesAsEventDatesDto();
    public OrderWithPassesAsEventDatesDto anotherOrderDto = new OrderWithPassesAsEventDatesDto();
    public PassesDto aPassDto = new PassesDto();
    public PassesDto anotherPassDto = new PassesDto();
    public OrderParser orderParser = new OrderParser();
    public Long anOrderId = 1L;
    public Long anotherOrderId = 2L;
    public Long aPassId = 1L;
    public Long anotherPassId = 2L;
    public OrderController orderController;

    public OrderEndToEndContext() {
        setUpObjects();
        setUpEntityManager();
    }

    private void setUpObjects() {
        aPass = new PassEntity(
                PassConstants.Categories.SUPERNOVA_ID,
                PassConstants.Options.SINGLE_ID,
                A_VALID_EVENT_DATE
        );

        anotherPass = new PassEntity(
                PassConstants.Categories.SUPERGIANT_ID,
                PassConstants.Options.SINGLE_ID,
                A_VALID_EVENT_DATE
        );

        anOrder = new OrderEntity(
                AN_ORDER_DATE,
                A_VENDOR_ID,
                new ArrayList<>(Collections.singletonList(aPass))
        );

        anotherOrder = new OrderEntity(
                ANOTHER_ORDER_DATE,
                A_VENDOR_ID,
                new ArrayList<>(Collections.singletonList(anotherPass))
        );

        aPassDto.passCategory = PassConstants.Categories.SUPERNOVA_NAME;
        aPassDto.passOption = PassConstants.Options.SINGLE_NAME;
        aPassDto.eventDates = new ArrayList<>(Collections.singletonList(A_VALID_EVENT_DATE.toString()));

        anotherPassDto.passCategory = PassConstants.Categories.SUPERNOVA_NAME;
        anotherPassDto.passOption = PassConstants.Options.SINGLE_NAME;
        anotherPassDto.eventDates = new ArrayList<>(Collections.singletonList(A_VALID_EVENT_DATE.toString()));

        anOrderDto.orderDate = FestivalDateUtil.toZonedDateTimeString(AN_ORDER_DATE);
        anOrderDto.vendorCode = A_VENDOR_CODE;
        anOrderDto.passes = aPassDto;

        anotherOrderDto.orderDate = FestivalDateUtil.toZonedDateTimeString(ANOTHER_ORDER_DATE);
        anotherOrderDto.vendorCode = A_VENDOR_CODE;
        anotherOrderDto.passes = anotherPassDto;
    }

    private void setUpEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(RepositoryConstants.STAGING_PERSISTENCE_NAME);
        entityManager = entityManagerFactory.createEntityManager();
    }

    public OrderEndToEndContext setUp() {
        OrderRepository orderRepository = new OrderRepositoryImpl(entityManager);
        PassRepository passRepository = new PassRepositoryImpl(entityManager);
        OxygenTankRepository oxygenTankRepository = new OxygenTankRepositoryImpl(entityManager);
        InventoryRepository inventoryRepository = new InventoryRepositoryImpl(entityManager);

        PassService passService = new PassServiceImpl(passRepository);
        InventoryService inventoryService = new InventoryServiceImpl(inventoryRepository);
        OxygenTankService oxygenTankService = new OxygenTankServiceImpl(oxygenTankRepository, inventoryService);
        OrderService orderService = new OrderServiceImpl(orderRepository, passService, oxygenTankService);

        orderController = new OrderController(orderService);

        return this;
    }

    public OrderEndToEndContext withAnOrder() {
        anOrderId = addOrder(anOrder);

        aPass.setOrder(anOrder);
        aPassId = addPass(aPass);
        aPass.setId(aPassId);

        return this;
    }

    public OrderEndToEndContext withAnotherOrder() {
        anotherOrderId = addOrder(anotherOrder);

        anotherPass.setOrder(anotherOrder);
        anotherPassId = addPass(anotherPass);
        anotherPass.setId(anotherPassId);

        return this;
    }

    public Long addOrder(OrderEntity orderEntity) {
        final Long[] orderId = new Long[1];

        entityManager.getTransaction().begin();

        entityManager.persist(orderEntity);
        entityManager.createQuery(RepositoryConstants.ORDER_FIND_ALL_QUERY, OrderEntity.class).getResultList()
                .forEach(currentOrderEntity -> {
            if (currentOrderEntity.getOrderDate().equals(orderEntity.getOrderDate())) {
                orderId[0] = currentOrderEntity.getId();
            }
        });

        entityManager.getTransaction().commit();

        return orderId[0];
    }

    public Long addPass(PassEntity passEntity) {
        final Long[] passId = new Long[1];

        entityManager.getTransaction().begin();

        entityManager.persist(passEntity);
        entityManager.createQuery(RepositoryConstants.PASS_FIND_ALL_QUERY, PassEntity.class).getResultList()
                .forEach(currentPassEntity -> {
                    if (currentPassEntity.getEventDate().equals(passEntity.getEventDate())) {
                        passId[0] = currentPassEntity.getId();
                    }
                });

        entityManager.getTransaction().commit();

        return passId[0];
    }
}
