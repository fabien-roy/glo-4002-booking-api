package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.constants.RepositoryConstants;
import ca.ulaval.glo4002.booking.constants.VendorConstants;
import ca.ulaval.glo4002.booking.entities.OrderEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderRepositoryContext {

    private final static String A_ORDER_DATE_TIME = FestivalConstants.Dates.ORDER_START_DATE_TIME.toString();
    private final static Long A_VENDOR_ID = VendorConstants.TEAM_VENDOR_ID;
    public EntityManager entityManager;
    public OrderEntity aOrder;
    public OrderEntity anotherOrder;
    public OrderEntity aNonExistentOrder;
    public final static Long A_ORDER_ID = 1L;
    public final static Long ANOTHER_ORDER_ID = 2L;
    public final static Long A_NON_EXISTANT_ORDER_ID = 0L;

    public OrderRepositoryContext() {
        setUpOrders();
        setUpEntityManager();
    }

    private void setUpOrders() {
        aOrder = new OrderEntity(
                A_ORDER_ID,
                A_ORDER_DATE_TIME,
                A_VENDOR_ID
        );

        anotherOrder = new OrderEntity(
                ANOTHER_ORDER_ID,
                A_ORDER_DATE_TIME,
                A_VENDOR_ID
        );

        aNonExistentOrder = new OrderEntity(
                A_NON_EXISTANT_ORDER_ID,
                A_ORDER_DATE_TIME,
                A_VENDOR_ID
        );
    }

    private void setUpEntityManager() {
        entityManager = mock(EntityManager.class);
        TypedQuery<OrderEntity> createQuery = mock(TypedQuery.class);

        when(createQuery.getResultList()).thenReturn(Arrays.asList(aOrder, anotherOrder));
        when(entityManager.createQuery(RepositoryConstants.ORDER_FIND_ALL_QUERY, OrderEntity.class)).thenReturn(createQuery);
        when(entityManager.find(OrderEntity.class, A_ORDER_ID)).thenReturn(aOrder);
        when(entityManager.find(OrderEntity.class, ANOTHER_ORDER_ID)).thenReturn(anotherOrder);
        when(entityManager.find(OrderEntity.class, A_NON_EXISTANT_ORDER_ID)).thenReturn(null);
    }

    public void setUpEntityManagerForSave() {
        aNonExistentOrder.id = null;
        when(entityManager.find(OrderEntity.class, A_NON_EXISTANT_ORDER_ID)).thenReturn(aNonExistentOrder);
    }
}
