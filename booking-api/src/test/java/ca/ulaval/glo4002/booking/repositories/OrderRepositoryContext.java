package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.constants.DateConstants;
import ca.ulaval.glo4002.booking.constants.RepositoryConstants;
import ca.ulaval.glo4002.booking.constants.VendorConstants;
import ca.ulaval.glo4002.booking.entities.OrderEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderRepositoryContext {

    private final static LocalDateTime A_ORDER_DATE_TIME = DateConstants.ORDER_START_DATE_TIME;
    private final static Long A_VENDOR_ID = VendorConstants.TEAM_VENDOR_ID;
    public final static Long A_ORDER_ID = 1L;
    public final static Long ANOTHER_ORDER_ID = 2L;
    public final static Long A_NON_EXISTENT_ORDER_ID = 0L;

    public EntityManager entityManager;
    public OrderEntity aOrder;
    public OrderEntity anotherOrder;
    public OrderEntity aNonExistentOrder;

    public OrderRepositoryContext() {
        setUpOrders();
        setUpEntityManager();
    }

    private void setUpOrders() {
        aOrder = new OrderEntity(
                A_ORDER_ID,
                A_ORDER_DATE_TIME,
                A_VENDOR_ID,
                new ArrayList<>()
        );

        anotherOrder = new OrderEntity(
                ANOTHER_ORDER_ID,
                A_ORDER_DATE_TIME,
                A_VENDOR_ID,
                new ArrayList<>()
        );

        aNonExistentOrder = new OrderEntity(
                A_NON_EXISTENT_ORDER_ID,
                A_ORDER_DATE_TIME,
                A_VENDOR_ID,
                new ArrayList<>()
        );
    }

    private void setUpEntityManager() {
        entityManager = mock(EntityManager.class);
        TypedQuery<OrderEntity> createQuery = mock(TypedQuery.class);

        when(createQuery.getResultList()).thenReturn(Arrays.asList(aOrder, anotherOrder));
        when(entityManager.createQuery(RepositoryConstants.ORDER_FIND_ALL_QUERY, OrderEntity.class)).thenReturn(createQuery);
        when(entityManager.find(OrderEntity.class, A_ORDER_ID)).thenReturn(aOrder);
        when(entityManager.find(OrderEntity.class, ANOTHER_ORDER_ID)).thenReturn(anotherOrder);
        when(entityManager.find(OrderEntity.class, A_NON_EXISTENT_ORDER_ID)).thenReturn(null);
    }

    public void setUpEntityManagerForSave() {
        EntityTransaction transaction = mock(EntityTransaction.class);
        when(entityManager.getTransaction()).thenReturn(transaction);

        aNonExistentOrder.setId(null);
        when(entityManager.find(OrderEntity.class, A_NON_EXISTENT_ORDER_ID)).thenReturn(aNonExistentOrder);
    }
}
