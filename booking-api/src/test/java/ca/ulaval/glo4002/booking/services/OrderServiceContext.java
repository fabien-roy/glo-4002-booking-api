package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.builders.VendorBuilder;
import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.constants.VendorConstants;
import ca.ulaval.glo4002.booking.domainObjects.orders.Order;
import ca.ulaval.glo4002.booking.entities.OrderEntity;
import ca.ulaval.glo4002.booking.parsers.OrderParser;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderServiceContext {

    private final static LocalDateTime A_ORDER_EVENT_DATE = FestivalConstants.Dates.ORDER_START_DATE_TIME;
    private final static VendorBuilder vendorBuilder = new VendorBuilder();
    private OrderParser parser = new OrderParser();
    private OrderEntity aOrderEntity;
    private OrderEntity anotherOrderEntity;
    private OrderEntity aNonExistentOrderEntity;
    public OrderRepository repository;
    public Order aOrder;
    public Order anotherOrder;
    public Order aNonExistentOrder;
    public final static Long A_ORDER_ID = 1L;
    public final static Long ANOTHER_ORDER_ID = 2L;
    public final static Long A_NON_EXISTANT_ORDER_ID = 0L;

    public OrderServiceContext() {
        setUpOrders();
        setUpRepository();
    }

    private void setUpOrders() {
        aOrder = new Order(
                A_ORDER_ID,
                A_ORDER_EVENT_DATE,
                vendorBuilder.buildById(VendorConstants.TEAM_VENDOR_ID)
        );

        anotherOrder = new Order(
                ANOTHER_ORDER_ID,
                A_ORDER_EVENT_DATE,
                vendorBuilder.buildById(VendorConstants.TEAM_VENDOR_ID)
        );

        aNonExistentOrder = new Order(
                A_NON_EXISTANT_ORDER_ID,
                A_ORDER_EVENT_DATE,
                vendorBuilder.buildById(VendorConstants.TEAM_VENDOR_ID)
        );

        aOrderEntity = parser.toEntity(aOrder);
        anotherOrderEntity = parser.toEntity(anotherOrder);
        aNonExistentOrderEntity = parser.toEntity(aNonExistentOrder);
    }

    private void setUpRepository() {
        repository = mock(OrderRepository.class);

        when(repository.findAll()).thenReturn(Arrays.asList(aOrderEntity, anotherOrderEntity));
        when(repository.findById(A_ORDER_ID)).thenReturn(Optional.of(aOrderEntity));
        when(repository.findById(ANOTHER_ORDER_ID)).thenReturn(Optional.of(anotherOrderEntity));
        when(repository.findById(A_NON_EXISTANT_ORDER_ID)).thenReturn(Optional.empty());
        when(repository.save(any(OrderEntity.class))).thenReturn(aOrderEntity);
    }

    public void setUpRepositoryForSave() {
        when(repository.findById(A_NON_EXISTANT_ORDER_ID)).thenReturn(Optional.of(aNonExistentOrderEntity));
    }
}
