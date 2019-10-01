package ca.ulaval.glo4002.booking.endToEnd;

import ca.ulaval.glo4002.booking.constants.DateConstants;
import ca.ulaval.glo4002.booking.constants.PassConstants;
import ca.ulaval.glo4002.booking.constants.RepositoryConstants;
import ca.ulaval.glo4002.booking.constants.VendorConstants;
import ca.ulaval.glo4002.booking.controllers.OrderController;
import ca.ulaval.glo4002.booking.entities.OrderEntity;
import ca.ulaval.glo4002.booking.entities.PassEntity;
import ca.ulaval.glo4002.booking.parsers.OrderParser;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;
import ca.ulaval.glo4002.booking.repositories.OrderRepositoryImpl;
import ca.ulaval.glo4002.booking.repositories.PassRepository;
import ca.ulaval.glo4002.booking.repositories.PassRepositoryImpl;
import ca.ulaval.glo4002.booking.services.*;

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

    // Copied from old file
    private static final LocalDate A_VALID_EVENT_DATE = DateConstants.START_DATE.plusDays(1L);
    private static final LocalDate ANOTHER_VALID_EVENT_DATE = DateConstants.START_DATE.plusDays(2L);
    private static final LocalDate A_INVALID_EVENT_DATE = DateConstants.START_DATE.minusDays(1L);
    private static final Long A_VENDOR_ID = VendorConstants.TEAM_VENDOR_ID;
    private static final LocalDateTime A_VALID_ORDER_DATE = DateConstants.ORDER_START_DATE_TIME;

    private static final LocalDateTime AN_ORDER_DATE = DateConstants.ORDER_START_DATE_TIME;
    private static final LocalDateTime ANOTHER_ORDER_DATE = DateConstants.ORDER_START_DATE_TIME.plusDays(1);
    public static final List<String> MULTIPLE_VALID_EVENT_DATES = new ArrayList<>(Arrays.asList(
            A_VALID_EVENT_DATE.toString(),
            ANOTHER_VALID_EVENT_DATE.toString()
    ));
    public static final List<String> SOME_INVALID_EVENT_DATES = new ArrayList<>(Collections.singletonList(
            A_INVALID_EVENT_DATE.toString()
    ));

    private EntityManager entityManager;
    public PassEntity aSinglePass;
    public PassEntity anotherSinglePass;
    public PassEntity aPackagePass;
    public OrderEntity aSinglePassOrder;
    public OrderEntity anotherSinglePassOrder;
    public OrderEntity aPackagePassOrder;
    public OrderParser orderParser = new OrderParser();
    public Long aSinglePassOrderId = 1L;
    public Long anotherSinglePassOrderId = 2L;
    public Long aPackagePassOrderId = 3L;
    public Long aSinglePassId = 1L;
    public Long anotherSinglePassId = 2L;
    public Long aPackagePassId = 3L;
    public OrderController orderController;

    public PassEndToEndContext() {
        Objects();
        setUpEntityManager();
    }

    private void Objects() {
        aSinglePass = new PassEntity(
                PassConstants.Categories.SUPERNOVA_ID,
                PassConstants.Options.SINGLE_ID,
                A_VALID_EVENT_DATE
        );

        anotherSinglePass = new PassEntity(
                PassConstants.Categories.SUPERGIANT_ID,
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
                new ArrayList<>(Collections.singletonList(aSinglePass))
        );

        anotherSinglePassOrder = new OrderEntity(
                ANOTHER_ORDER_DATE,
                A_VENDOR_ID,
                new ArrayList<>(Collections.singletonList(anotherSinglePass))
        );

        aPackagePassOrder = new OrderEntity(
                AN_ORDER_DATE,
                A_VENDOR_ID,
                new ArrayList<>(Collections.singletonList(aPackagePass))
        );
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
        OrderService orderService = new OrderServiceImpl(orderRepository, passService, oxygenTankService);

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
        aSinglePassOrderId = addOrder(aSinglePassOrder);

        aSinglePass.setOrder(aSinglePassOrder);
        aSinglePassId = addPass(aSinglePass);
        aSinglePass.setId(aSinglePassId);
        anotherSinglePass.setOrder(aSinglePassOrder);
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
