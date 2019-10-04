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
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderServiceTestContext {

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
    private OrderRepository repository;
    private PassService passService;
    private Pass aPass;

    Pass aNebulaSinglePass;
    Pass aSupergiantSinglePass;
    Pass aSupernovaPackagePass;
    Order aOrder;
    Order anotherOrder;
    Order aNonExistentOrder;

    final static Long A_ORDER_ID = 1L;
    private final static Long ANOTHER_ORDER_ID = 2L;
    final static Long A_NON_EXISTANT_ORDER_ID = 0L;

    public OrderServiceTestContext() {
        setUpObjects();
        setUpRepository();
        setUpSubject();
    }

    private void setUpObjects() {
        aPass = new Pass(
                A_PASS_ID,
                categoryBuilder.buildById(PassConstants.Categories.SUPERNOVA_ID),
                optionBuilder.buildById(PassConstants.Options.SINGLE_ID),
                A_PASS_EVENT_DATE
        );

        aSupernovaPackagePass = new Pass(
                A_PASS_ID,
                categoryBuilder.buildById(PassConstants.Categories.SUPERNOVA_ID),
                optionBuilder.buildById(PassConstants.Options.PACKAGE_ID)
        );

        aSupergiantSinglePass = new Pass(
                A_PASS_ID,
                categoryBuilder.buildById(PassConstants.Categories.SUPERGIANT_ID),
                optionBuilder.buildById(PassConstants.Options.SINGLE_ID),
                A_PASS_EVENT_DATE
        );

        aNebulaSinglePass = new Pass(
                A_PASS_ID,
                categoryBuilder.buildById(PassConstants.Categories.NEBULA_ID),
                optionBuilder.buildById(PassConstants.Options.SINGLE_ID),
                A_PASS_EVENT_DATE
        );

        aOrder = new Order(
                A_ORDER_ID,
                A_ORDER_EVENT_DATE,
                vendorBuilder.buildById(VendorConstants.TEAM_VENDOR_ID),
                Collections.singletonList(aPass)
        );

        anotherOrder = new Order(
                ANOTHER_ORDER_ID,
                A_ORDER_EVENT_DATE,
                vendorBuilder.buildById(VendorConstants.TEAM_VENDOR_ID),
                Collections.singletonList(aPass)
        );

        aNonExistentOrder = new Order(
                A_NON_EXISTANT_ORDER_ID,
                A_ORDER_EVENT_DATE,
                vendorBuilder.buildById(VendorConstants.TEAM_VENDOR_ID),
                Collections.singletonList(aPass)
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
        ShuttleService shuttleService = mock(ShuttleService.class);

        subject = new OrderServiceImpl(repository, passService, oxygenTankService, shuttleService);
    }

    public void setUpForSave() {
        when(passService.order(any(), any())).thenReturn(Collections.singletonList(aPass));
        when(repository.findById(A_NON_EXISTANT_ORDER_ID)).thenReturn(Optional.of(aNonExistentOrderEntity));
        when(repository.save(aOrderEntity)).thenReturn(aOrderEntity);
        when(repository.update(aOrderEntity)).thenReturn(aOrderEntity);
    }
}
