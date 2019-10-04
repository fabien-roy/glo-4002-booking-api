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
import ca.ulaval.glo4002.booking.repositories.OrderRepository;
import ca.ulaval.glo4002.booking.repositories.OrderRepositoryImpl;
import ca.ulaval.glo4002.booking.repositories.PassRepository;
import ca.ulaval.glo4002.booking.repositories.PassRepositoryImpl;
import ca.ulaval.glo4002.booking.services.*;
import ca.ulaval.glo4002.booking.util.FestivalDateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;

public class PassEndToEndContext {

    private static final LocalDate A_VALID_EVENT_DATE = DateConstants.START_DATE.plusDays(1L);
    private static final LocalDate ANOTHER_VALID_EVENT_DATE = DateConstants.START_DATE.plusDays(2L);
    private static final LocalDate A_INVALID_EVENT_DATE = DateConstants.START_DATE.minusDays(1L);
    private static final Long A_VENDOR_ID = VendorConstants.TEAM_VENDOR_ID;
    private static final String A_VENDOR_CODE = VendorConstants.TEAM_VENDOR_CODE;

    private static final LocalDateTime AN_ORDER_DATE = DateConstants.ORDER_START_DATE_TIME;
    private static final LocalDateTime ANOTHER_ORDER_DATE = DateConstants.ORDER_START_DATE_TIME.plusDays(1);
    public static final List<String> MULTIPLE_VALID_EVENT_DATES = new ArrayList<>(Arrays.asList(
            A_VALID_EVENT_DATE.toString(),
            ANOTHER_VALID_EVENT_DATE.toString()
    ));
    public static final List<String> SOME_INVALID_EVENT_DATES = new ArrayList<>(Collections.singletonList(
            A_INVALID_EVENT_DATE.toString()
    ));
    private static final Double A_ORDER_PRICE = PassConstants.Categories.SUPERGIANT_SINGLE_PASS_PRICE;

    private EntityManager entityManager;
    public PassEntity aSinglePass;
    public PassEntity anotherSinglePass;
    public PassEntity aPackagePass;
    public OrderEntity aSinglePassOrder;
    public OrderEntity anotherSinglePassOrder;
    public OrderEntity aMultipleSinglePassOrder;
    public OrderEntity aPackagePassOrder;
    public PassesDto aSinglePassDto = new PassesDto();
    public PassesDto anotherSinglePassDto = new PassesDto();
    public PassesDto aMultipleSinglePassDto = new PassesDto();
    public PassesDto aPackagePassDto = new PassesDto();
    public OrderWithPassesAsEventDatesDto aSinglePassOrderDto = new OrderWithPassesAsEventDatesDto();
    public OrderWithPassesAsEventDatesDto anotherSinglePassOrderDto = new OrderWithPassesAsEventDatesDto();
    public OrderWithPassesAsEventDatesDto aMultipleSinglePassOrderDto = new OrderWithPassesAsEventDatesDto();
    public OrderWithPassesAsEventDatesDto aPackagePassOrderDto = new OrderWithPassesAsEventDatesDto();
    public Long aSinglePassOrderId = 1L;
    public Long anotherSinglePassOrderId = 2L;
    public Long aMultipleSinglePassOrderId = 3L;
    public Long aPackagePassOrderId = 4L;
    public Long aSinglePassId = 1L;
    public Long anotherSinglePassId = 2L;
    public Long aPackagePassId = 4L;
    public OrderController orderController;

    public PassEndToEndContext() {
        setUpObjects();
        setUpEntityManager();
    }

    private void setUpObjects() {
        aSinglePass = new PassEntity(
                PassConstants.Categories.SUPERNOVA_ID,
                PassConstants.Options.SINGLE_ID,
                A_VALID_EVENT_DATE
        );

        anotherSinglePass = new PassEntity(
                PassConstants.Categories.SUPERNOVA_ID,
                PassConstants.Options.SINGLE_ID,
                ANOTHER_VALID_EVENT_DATE
        );

        aPackagePass = new PassEntity(
                PassConstants.Categories.SUPERGIANT_ID,
                PassConstants.Options.PACKAGE_ID
        );

        aSinglePassOrder = new OrderEntity(
                AN_ORDER_DATE,
                A_VENDOR_ID,
                new ArrayList<>(Collections.singletonList(aSinglePass)),
                A_ORDER_PRICE
        );

        anotherSinglePassOrder = new OrderEntity(
                ANOTHER_ORDER_DATE,
                A_VENDOR_ID,
                new ArrayList<>(Collections.singletonList(anotherSinglePass)),
                A_ORDER_PRICE
        );

        aMultipleSinglePassOrder = new OrderEntity(
                AN_ORDER_DATE,
                A_VENDOR_ID,
                new ArrayList<>(Arrays.asList(aSinglePass, anotherSinglePass)),
                A_ORDER_PRICE
        );

        aPackagePassOrder = new OrderEntity(
                AN_ORDER_DATE,
                A_VENDOR_ID,
                new ArrayList<>(Collections.singletonList(aPackagePass)),
                A_ORDER_PRICE
        );

        aSinglePassDto.passCategory = PassConstants.Categories.SUPERNOVA_NAME;
        aSinglePassDto.passOption = PassConstants.Options.SINGLE_NAME;
        aSinglePassDto.eventDates = new ArrayList<>(Collections.singletonList(A_VALID_EVENT_DATE.toString()));

        anotherSinglePassDto.passCategory = PassConstants.Categories.SUPERNOVA_NAME;
        anotherSinglePassDto.passOption = PassConstants.Options.SINGLE_NAME;
        anotherSinglePassDto.eventDates = new ArrayList<>(Collections.singletonList(ANOTHER_VALID_EVENT_DATE.toString()));

        aMultipleSinglePassDto.passCategory = PassConstants.Categories.SUPERNOVA_NAME;
        aMultipleSinglePassDto.passOption = PassConstants.Options.SINGLE_NAME;
        aMultipleSinglePassDto.eventDates = new ArrayList<>(Arrays.asList(
                A_VALID_EVENT_DATE.toString(),
                ANOTHER_VALID_EVENT_DATE.toString()
        ));

        aPackagePassDto.passCategory = PassConstants.Categories.SUPERGIANT_NAME;
        aPackagePassDto.passOption = PassConstants.Options.PACKAGE_NAME;

        aSinglePassOrderDto.orderDate = FestivalDateUtil.toZonedDateTimeString(AN_ORDER_DATE);
        aSinglePassOrderDto.vendorCode = A_VENDOR_CODE;
        aSinglePassOrderDto.passes = aSinglePassDto;

        anotherSinglePassOrderDto.orderDate = FestivalDateUtil.toZonedDateTimeString(ANOTHER_ORDER_DATE);
        anotherSinglePassOrderDto.vendorCode = A_VENDOR_CODE;
        anotherSinglePassOrderDto.passes = anotherSinglePassDto;

        aMultipleSinglePassOrderDto.orderDate = FestivalDateUtil.toZonedDateTimeString(AN_ORDER_DATE);
        aMultipleSinglePassOrderDto.vendorCode = A_VENDOR_CODE;
        aMultipleSinglePassOrderDto.passes = aMultipleSinglePassDto;

        aPackagePassOrderDto.orderDate = FestivalDateUtil.toZonedDateTimeString(AN_ORDER_DATE);
        aPackagePassOrderDto.vendorCode = A_VENDOR_CODE;
        aPackagePassOrderDto.passes = aPackagePassDto;
    }

    private void setUpEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(RepositoryConstants.STAGING_PERSISTENCE_NAME);
        entityManager = entityManagerFactory.createEntityManager();
    }

    public PassEndToEndContext setUp() {
        OrderRepository orderRepository = new OrderRepositoryImpl(entityManager);
        PassRepository passRepository = new PassRepositoryImpl(entityManager);

        PassService passService = new PassServiceImpl(passRepository);
        OxygenTankService oxygenTankService = mock(OxygenTankService.class);
        ShuttleService shuttleService = mock(ShuttleService.class);
        OrderService orderService = new OrderServiceImpl(orderRepository, passService, oxygenTankService, shuttleService);

        orderController = new OrderController(orderService);

        return this;
    }

    public PassEndToEndContext withASinglePassOrder() {
        aSinglePassOrderId = addOrder(aSinglePassOrder);

        aSinglePass.setOrder(aSinglePassOrder);
        aSinglePassId = addPass(aSinglePass);
        aSinglePass.setId(aSinglePassId);

        return this;
    }

    public PassEndToEndContext withAnotherSinglePassOrder() {
        anotherSinglePassOrderId = addOrder(anotherSinglePassOrder);

        anotherSinglePass.setOrder(anotherSinglePassOrder);
        anotherSinglePassId = addPass(anotherSinglePass);
        anotherSinglePass.setId(anotherSinglePassId);

        return this;
    }

    public PassEndToEndContext withMultipleSinglePassOrder() {
        aMultipleSinglePassOrderId = addOrder(aMultipleSinglePassOrder);

        aSinglePass.setOrder(aMultipleSinglePassOrder);
        aSinglePassId = addPass(aSinglePass);
        aSinglePass.setId(aSinglePassId);
        anotherSinglePass.setOrder(aMultipleSinglePassOrder);
        anotherSinglePassId = addPass(anotherSinglePass);
        anotherSinglePass.setId(anotherSinglePassId);

        return this;
    }

    public PassEndToEndContext withAPackagePassOrder() {
        aPackagePassOrderId = addOrder(aPackagePassOrder);

        aPackagePass.setOrder(aPackagePassOrder);
        aPackagePassId = addPass(aPackagePass);
        aPackagePass.setId(aPackagePassId);

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
                    if (currentPassEntity.getOptionId().equals(PassConstants.Options.PACKAGE_ID)) {
                        passId[0] = currentPassEntity.getId();
                    } else if (currentPassEntity.getOptionId().equals(PassConstants.Options.SINGLE_ID)) {
                        if (currentPassEntity.getEventDate().equals(passEntity.getEventDate())) {
                            passId[0] = currentPassEntity.getId();
                        }
                    }
                });

        entityManager.getTransaction().commit();

        return passId[0];
    }
}
