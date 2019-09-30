package ca.ulaval.glo4002.booking.endToEnd;

import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.constants.RepositoryConstants;
import ca.ulaval.glo4002.booking.constants.VendorConstants;
import ca.ulaval.glo4002.booking.controllers.OrderController;
import ca.ulaval.glo4002.booking.entities.OrderEntity;
import ca.ulaval.glo4002.booking.parsers.OrderParser;
import ca.ulaval.glo4002.booking.parsers.PassParser;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;
import ca.ulaval.glo4002.booking.repositories.OrderRepositoryImpl;
import ca.ulaval.glo4002.booking.repositories.PassRepository;
import ca.ulaval.glo4002.booking.repositories.PassRepositoryImpl;
import ca.ulaval.glo4002.booking.services.OrderService;
import ca.ulaval.glo4002.booking.services.OrderServiceImpl;
import ca.ulaval.glo4002.booking.services.PassService;
import ca.ulaval.glo4002.booking.services.PassServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class OrderEndToEndContext {

    private static final LocalDateTime AN_ORDER_DATE = FestivalConstants.Dates.ORDER_START_DATE_TIME;
    private static final LocalDateTime ANOTHER_ORDER_DATE = FestivalConstants.Dates.ORDER_START_DATE_TIME.plusDays(1);
    private static final Long A_VENDOR_ID = VendorConstants.TEAM_VENDOR_ID;
    public static final Long AN_INVALID_ORDER_ID = -1L;

    private EntityManager entityManager;
    private OrderParser orderParser = new OrderParser();
    private PassParser passParser = new PassParser();
    private OrderEntity anOrder;
    private OrderEntity anotherOrder;
    public Long anOrderId = 1L;
    public Long anotherOrderId = 2L;

    public OrderController orderController;

    public OrderEndToEndContext() {
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

    public OrderEndToEndContext setUp() {
        OrderRepository orderRepository = new OrderRepositoryImpl(entityManager);
        PassRepository passRepository = new PassRepositoryImpl(entityManager);
        OrderService orderService = new OrderServiceImpl(orderRepository);
        PassService passService = new PassServiceImpl(passRepository);
        orderController = new OrderController(orderService, passService, orderParser, passParser);

        return this;
    }

    public OrderEndToEndContext withAnOrder() {
        anOrderId = addOrder(anOrder, AN_ORDER_DATE);

        return this;
    }

    public OrderEndToEndContext withAnotherOrder() {
        anotherOrderId = addOrder(anotherOrder, ANOTHER_ORDER_DATE);

        return this;
    }

    public Long addOrder(OrderEntity orderEntity, LocalDateTime orderDate) {
        final Long[] orderId = new Long[1];

        entityManager.getTransaction().begin();

        entityManager.persist(orderEntity);
        entityManager.createQuery(RepositoryConstants.ORDER_FIND_ALL_QUERY, OrderEntity.class).getResultList()
                .forEach(currentOrderEntity -> {
            if (currentOrderEntity.orderDate.equals(orderDate)) {
                orderId[0] = currentOrderEntity.id;
            }
        });

        entityManager.getTransaction().commit();

        return orderId[0];
    }}
