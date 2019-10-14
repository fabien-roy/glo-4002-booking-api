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
    private static final Double ANOTHER_ORDER_PRICE = PassConstants.Categories.SUPERGIANT_SINGLE_PASS_PRICE;
    private static final Double A_ORDER_PRICE = PassConstants.Categories.NEBULA_SINGLE_PASS_PRICE;
    static final Long AN_INVALID_ORDER_ID = -1L;

    OrderParser orderParser = new OrderParser();
    OrderEntity anOrder;
    OrderEntity anotherOrder;

    private EntityManager entityManager;
    private PassEntity aPass;
    private PassEntity anotherPass;
    private Long aPassId = 1L;
    private Long anotherPassId = 2L;
    private PassesDto aPassDto = new PassesDto();
    private PassesDto anotherPassDto = new PassesDto();
    OrderWithPassesAsEventDatesDto anOrderDto = new OrderWithPassesAsEventDatesDto();
    OrderWithPassesAsEventDatesDto anotherOrderDto = new OrderWithPassesAsEventDatesDto();
    Long anOrderId = 1L;
    Long anotherOrderId = 2L;
    OrderController orderController;

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
                new ArrayList<>(Collections.singletonList(aPass)),
                A_ORDER_PRICE
        );

        anotherOrder = new OrderEntity(
                ANOTHER_ORDER_DATE,
                A_VENDOR_ID,
                new ArrayList<>(Collections.singletonList(anotherPass)),
                ANOTHER_ORDER_PRICE
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
        ShuttleRepository shuttleRepository = new ShuttleRepositoryImpl(entityManager);
        PassengerRepository passengerRepository = new PassengerRepositoryImpl(entityManager);
        ShuttleInventoryRepository shuttleInventoryRepository = new ShuttleInventoryRepositoryImpl(entityManager);

        PassService passService = new PassServiceImpl(passRepository);
        PassengerService passengerService =new PassengerServiceImpl(passengerRepository);
        ShuttleService shuttleService = new ShuttleServiceImpl(shuttleRepository, passengerService);
        OxygenTankInventoryService oxygenTankInventoryService = mock(OxygenTankInventoryService.class); // Unmock when ready
        ShuttleInventoryService shuttleInventoryService = new ShuttleInventoryServiceImpl(shuttleInventoryRepository, shuttleService);
        OrderService orderService = new OrderServiceImpl(orderRepository, passService, oxygenTankInventoryService, shuttleInventoryService);

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
