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

public class PassEndToEndContext {

    // Copied from old file
    private static final LocalDate A_VALID_PASS_DATE = DateConstants.START_DATE.plusDays(1L);
    private static final LocalDate ANOTHER_VALID_PASS_DATE = DateConstants.START_DATE.plusDays(2L);
    private static final Long A_VENDOR_ID = VendorConstants.TEAM_VENDOR_ID;
    private static final LocalDateTime A_VALID_ORDER_DATE = DateConstants.ORDER_START_DATE_TIME;
    PassEntity aSinglePass = new PassEntity(PassConstants.Categories.SUPERNOVA_ID, PassConstants.Options.SINGLE_ID, A_VALID_PASS_DATE);
    PassEntity anotherSinglePass = new PassEntity(PassConstants.Categories.SUPERGIANT_ID, PassConstants.Options.SINGLE_ID, ANOTHER_VALID_PASS_DATE);
    OrderEntity anOrderEntity = new OrderEntity(A_VALID_ORDER_DATE, A_VENDOR_ID);

    private static final LocalDateTime AN_ORDER_DATE = DateConstants.ORDER_START_DATE_TIME;
    private static final LocalDateTime ANOTHER_ORDER_DATE = DateConstants.ORDER_START_DATE_TIME.plusDays(1);
    public static final Long AN_INVALID_ORDER_ID = -1L;

    private EntityManager entityManager;
    public OrderEntity anOrder;
    public OrderEntity anotherOrder;
    public OrderParser orderParser = new OrderParser();
    public Long anOrderId = 1L;
    public Long anotherOrderId = 2L;
    public OrderController orderController;

    public PassEndToEndContext() {
        setUpOrders();
        setUpEntityManager();
    }

    private void setUpOrders() {
        anOrder = new OrderEntity(
                AN_ORDER_DATE,
                A_VENDOR_ID
        );

        anotherOrder = new OrderEntity(
                ANOTHER_ORDER_DATE,
                A_VENDOR_ID
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
        OxygenTankService oxygenTankService = new OxygenTankServiceImpl();
        OrderService orderService = new OrderServiceImpl(orderRepository, passService, oxygenTankService);

        orderController = new OrderController(orderService);

        return this;
    }

    public PassEndToEndContext withAnOrder() {
        anOrderId = addOrder(anOrder, AN_ORDER_DATE);

        return this;
    }

    public PassEndToEndContext withAnotherOrder() {
        anotherOrderId = addOrder(anotherOrder, ANOTHER_ORDER_DATE);

        return this;
    }

    public Long addOrder(OrderEntity orderEntity, LocalDateTime orderDate) {
        final Long[] orderId = new Long[1];

        entityManager.getTransaction().begin();

        entityManager.persist(orderEntity);
        entityManager.createQuery(RepositoryConstants.ORDER_FIND_ALL_QUERY, OrderEntity.class).getResultList()
                .forEach(currentOrderEntity -> {
            if (currentOrderEntity.getOrderDate().equals(orderDate)) {
                orderId[0] = currentOrderEntity.getId();
            }
        });

        entityManager.getTransaction().commit();

        return orderId[0];
    }}
