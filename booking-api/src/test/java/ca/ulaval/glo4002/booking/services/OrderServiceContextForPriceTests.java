package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.builders.VendorBuilder;
import ca.ulaval.glo4002.booking.builders.passes.PassCategoryBuilder;
import ca.ulaval.glo4002.booking.builders.passes.PassOptionBuilder;
import ca.ulaval.glo4002.booking.constants.DateConstants;
import ca.ulaval.glo4002.booking.constants.PassConstants;
import ca.ulaval.glo4002.booking.constants.VendorConstants;
import ca.ulaval.glo4002.booking.domainobjects.orders.Order;
import ca.ulaval.glo4002.booking.domainobjects.passes.Pass;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;

class OrderServiceContextForPriceTests {
    private static final Long A_PASS_ID = 1L;
    private static final PassCategoryBuilder categoryBuilder = new PassCategoryBuilder();
    private static final PassOptionBuilder optionBuilder = new PassOptionBuilder();
    private static final VendorBuilder vendorBuilder = new VendorBuilder();
    private static final LocalDate A_EVENT_DATE = DateConstants.START_DATE;
    private static final Long A_ORDER_ID = 2L;
    private static final LocalDateTime A_ORDER_EVENT_DATE = DateConstants.ORDER_START_DATE_TIME;

    OrderService subject;
    PassService passService;
    OrderRepository repository;
    Pass aSupernovaPackagePass;
    Order aOrder;

    public OrderServiceContextForPriceTests() {
        setUpObjects();
        setUpSubject();
    }

    private void setUpSubject() {
        passService = mock(PassService.class);
        repository = mock(OrderRepository.class);
        OxygenTankService oxygenTankService = mock(OxygenTankService.class);

        subject = new OrderServiceImpl(repository, passService, oxygenTankService);
    }

    private void setUpObjects() {
        aSupernovaPackagePass = new Pass(
                A_PASS_ID,
                categoryBuilder.buildById(PassConstants.Categories.SUPERNOVA_ID),
                optionBuilder.buildById(PassConstants.Options.PACKAGE_ID),
                A_EVENT_DATE
        );

        aOrder = new Order(
                A_ORDER_ID,
                A_ORDER_EVENT_DATE,
                vendorBuilder.buildById(VendorConstants.TEAM_VENDOR_ID),
                new ArrayList<>()
        );
    }

}
