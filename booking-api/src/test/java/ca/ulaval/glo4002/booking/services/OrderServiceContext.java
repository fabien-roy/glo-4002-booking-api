package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.builders.VendorBuilder;
import ca.ulaval.glo4002.booking.builders.passes.PassCategoryBuilder;
import ca.ulaval.glo4002.booking.builders.passes.PassOptionBuilder;
import ca.ulaval.glo4002.booking.constants.DateConstants;
import ca.ulaval.glo4002.booking.constants.PassConstants;
import ca.ulaval.glo4002.booking.constants.VendorConstants;
import ca.ulaval.glo4002.booking.domainobjects.orders.Order;
import ca.ulaval.glo4002.booking.domainobjects.passes.Pass;
import ca.ulaval.glo4002.booking.entities.OrderEntity;
import ca.ulaval.glo4002.booking.parsers.OrderParser;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderServiceContext {

    public OrderService subject;

    private final static LocalDateTime A_ORDER_EVENT_DATE = DateConstants.ORDER_START_DATE_TIME;
    private final static LocalDate A_PASS_EVENT_DATE = DateConstants.START_DATE;
    private final static Long A_PASS_ID = 1L;
    private final static VendorBuilder vendorBuilder = new VendorBuilder();
    private final static PassCategoryBuilder categoryBuilder = new PassCategoryBuilder();
    private final static PassOptionBuilder optionBuilder = new PassOptionBuilder();
    private OrderParser parser = new OrderParser();
    private OrderEntity aOrderEntity;
    private OrderEntity anotherOrderEntity;
    private OrderEntity aNonExistentOrderEntity;
    public OrderRepository repository;
    public PassService passService;
    public Order aOrder;
    public Order anotherOrder;
    public Order aNonExistentOrder;
    public Pass aPass;
    public final static Long A_ORDER_ID = 1L;
    public final static Long ANOTHER_ORDER_ID = 2L;
    public final static Long A_NON_EXISTANT_ORDER_ID = 0L;

    public OrderServiceContext() {
        setUpObjects();
        setUpRepository();
        setUpSubject();
    }

    private void setUpObjects() {
        aOrder = new Order(
                A_ORDER_ID,
                A_ORDER_EVENT_DATE,
                vendorBuilder.buildById(VendorConstants.TEAM_VENDOR_ID),
                new ArrayList<>()
        );

        anotherOrder = new Order(
                ANOTHER_ORDER_ID,
                A_ORDER_EVENT_DATE,
                vendorBuilder.buildById(VendorConstants.TEAM_VENDOR_ID),
                new ArrayList<>()
        );

        aNonExistentOrder = new Order(
                A_NON_EXISTANT_ORDER_ID,
                A_ORDER_EVENT_DATE,
                vendorBuilder.buildById(VendorConstants.TEAM_VENDOR_ID),
                new ArrayList<>()
        );

        aPass = new Pass(
                A_PASS_ID,
                categoryBuilder.buildById(PassConstants.Categories.SUPERNOVA_ID),
                optionBuilder.buildById(PassConstants.Options.SINGLE_ID),
                A_PASS_EVENT_DATE
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

    private void setUpSubject() {
        passService = mock(PassService.class);
        OxygenTankService oxygenTankService = mock(OxygenTankService.class);

        subject = new OrderServiceImpl(repository, passService, oxygenTankService);
    }

    public void setUpForSave() {
        when(passService.order(any(), any())).thenReturn(Collections.singletonList(aPass));
        when(repository.findById(A_NON_EXISTANT_ORDER_ID)).thenReturn(Optional.of(aNonExistentOrderEntity));
        when(repository.save(aOrderEntity)).thenReturn(aOrderEntity);
        when(repository.update(aOrderEntity)).thenReturn(aOrderEntity);
    }
}
