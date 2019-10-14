package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.builders.VendorBuilder;
import ca.ulaval.glo4002.booking.builders.passes.PassCategoryBuilder;
import ca.ulaval.glo4002.booking.builders.passes.PassOptionBuilder;
import ca.ulaval.glo4002.booking.constants.*;
import ca.ulaval.glo4002.booking.domainobjects.orders.Order;
import ca.ulaval.glo4002.booking.domainobjects.passes.Pass;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsPassesDto;
import ca.ulaval.glo4002.booking.dto.PassesDto;
import ca.ulaval.glo4002.booking.entities.OrderEntity;
import ca.ulaval.glo4002.booking.exceptions.VendorNotFoundException;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderInvalidFormatException;
import ca.ulaval.glo4002.booking.util.FestivalDateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class OrderParserTest {

    private static final Long A_VALID_ID = 1L;
    private final static Long A_PASS_NUMBER = 1L;
    private final static Long ANOTHER_PASS_NUMBER = 2L;
    private final static String A_PASS_CATEGORY = PassConstants.Categories.SUPERNOVA_NAME;
    private final static String SINGLE_PASS_OPTION = PassConstants.Options.SINGLE_NAME;
    private final static String PACKAGE_PASS_OPTION = PassConstants.Options.PACKAGE_NAME;
    private static final String A_DATE_BEFORE_ORDER_START_DATE_TIME = DateConstants.ORDER_START_DATE_TIME.minusDays(1).toString();
    private static final String A_DATE_AFTER_ORDER_START_END_TIME = DateConstants.ORDER_END_DATE_TIME.plusDays(1).toString();
    private static final String AN_INVALID_VENDOR_CODE = "An invalid vendor code";
    private static final String A_VALID_VENDOR_CODE = VendorConstants.TEAM_VENDOR_CODE;
    private static final LocalDateTime A_VALID_DATE = DateConstants.ORDER_START_DATE_TIME;
    private final static LocalDate SOME_EVENT_DATE = DateConstants.START_DATE;
    private final static LocalDate SOME_OTHER_EVENT_DATE = DateConstants.START_DATE.plusDays(1);
    private OrderParser subject;
    private OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto();
    private Order order;
    private VendorBuilder vendorBuilder = new VendorBuilder();
    private PassCategoryBuilder categoryBuilder = new PassCategoryBuilder();
    private PassOptionBuilder optionBuilder = new PassOptionBuilder();

    @BeforeEach
    void setUp() {
        subject = new OrderParser();
        orderDto.orderDate = FestivalDateUtil.toZonedDateTimeString(A_VALID_DATE);
        orderDto.vendorCode = A_VALID_VENDOR_CODE;

        PassesDto passesDto = new PassesDto();
        passesDto.passNumber = A_PASS_NUMBER;
        passesDto.passCategory = A_PASS_CATEGORY;
        passesDto.passOption = SINGLE_PASS_OPTION;
        passesDto.eventDates = new ArrayList<>(Collections.singletonList(SOME_EVENT_DATE.toString()));
        orderDto.passes = passesDto;

        order = new Order(
                A_VALID_ID,
                A_VALID_DATE,
                vendorBuilder.buildByCode(A_VALID_VENDOR_CODE),
                new ArrayList<>(Collections.singletonList(new Pass(
                        A_PASS_NUMBER,
                        categoryBuilder.buildByName(A_PASS_CATEGORY),
                        optionBuilder.buildByName(SINGLE_PASS_OPTION),
                        SOME_EVENT_DATE
                )))
        );
    }

    @Test
    void whenOrderDateIsBeforeOrderStart_thenOrderDtoInvalidExceptionIsThrown() {
        orderDto.orderDate = A_DATE_BEFORE_ORDER_START_DATE_TIME;

        OrderInvalidFormatException thrown = assertThrows(
                OrderInvalidFormatException.class,
                ()->subject.parseDto(orderDto)
        );

        assertEquals(ExceptionConstants.Order.INVALID_FORMAT_ERROR, thrown.getMessage());
    }

    @Test
    void whenOrderDateIsAfterOrderStart_thenOrderDtoInvalidExceptionIsThrown() {
        orderDto.orderDate = A_DATE_AFTER_ORDER_START_END_TIME;

        OrderInvalidFormatException thrown = assertThrows(
                OrderInvalidFormatException.class,
                ()->subject.parseDto(orderDto)
        );

        assertEquals(ExceptionConstants.Order.INVALID_FORMAT_ERROR, thrown.getMessage());
    }

    @Test
    void whenOrderDateIsBetweenValidOrderDate_thenOrderDateIsAssignedToOrder() {
        orderDto.orderDate = FestivalDateUtil.toZonedDateTimeString(A_VALID_DATE);

        Order order = subject.parseDto(orderDto);

        assertEquals(A_VALID_DATE.toString(), order.getOrderDate().toString());
    }

    @Test
    void whenVendorCodeIsInvalid_thenVendorNotFoundExceptionIsThrown() {
        orderDto.vendorCode = AN_INVALID_VENDOR_CODE;

        VendorNotFoundException thrown = assertThrows(
                VendorNotFoundException.class,
                ()->subject.parseDto(orderDto)
        );

        assertEquals(ExceptionConstants.Vendor.NOT_FOUND_ERROR, thrown.getMessage());
    }

    @Test
    void whenVendorCodeIsValid_thenVendorIsAssignedToOrder() {
        orderDto.vendorCode = A_VALID_VENDOR_CODE;

        Order order = subject.parseDto(orderDto);

        assertNotNull(order.getVendor().getCode());
        assertEquals(A_VALID_VENDOR_CODE, order.getVendor().getCode());
    }

    @Test
    void whenParsingEntity_orderShouldBeValid() {
        OrderEntity entity = subject.toEntity(order);

        Order parsedOrder = subject.parseEntity(entity);

        assertEquals(entity.getId(), parsedOrder.getId());
        assertEquals(entity.getVendorId(), parsedOrder.getVendor().getId());
        assertEquals(entity.getOrderDate(), parsedOrder.getOrderDate());
        assertEquals(entity.getPrice(), parsedOrder.getPrice());
        entity.getPasses().forEach(orderItemEntity -> {
            assertTrue(parsedOrder.getPasses().stream().anyMatch(orderItem -> orderItemEntity.getId().equals(orderItem.getId())));
        });
    }

    @Test
    void whenParsingToEntity_entityShouldBeValid() {
        OrderEntity entity = subject.toEntity(order);

        assertEquals(order.getId(), entity.getId());
        assertEquals(order.getVendor().getId(), entity.getVendorId());
        assertEquals(order.getOrderDate(), entity.getOrderDate());
        assertEquals(order.getPrice(), entity.getPrice());
        order.getPasses().forEach(orderItem -> {
            assertTrue(entity.getPasses().stream().anyMatch(orderItemEntity -> orderItem.getId().equals(orderItemEntity.getId())
            ));
        });
    }

    @Test
    void whenParsingToDto_dtoShouldBeValid() {
        OrderWithPassesAsPassesDto dto = subject.toDto(order);

        assertNotNull(dto.orderNumber);
        assertEquals(order.getVendor().getCode(), dto.vendorCode);
        assertEquals(order.getOrderDate().toString().concat("Z"), dto.orderDate);
        assertEquals(1, order.getPasses().size());
    }

    @Test
    void whenParsingToDto_dtoOrderNumberShouldBeValid() {
        OrderWithPassesAsPassesDto dto = subject.toDto(order);

        String vendorCode = dto.orderNumber.substring(0, dto.orderNumber.indexOf(OrderConstants.ORDER_NUMBER_SEPARATOR));
        String orderNumber = dto.orderNumber.substring(dto.orderNumber.indexOf(OrderConstants.ORDER_NUMBER_SEPARATOR) + 1);

        assertEquals(order.getVendor().getCode(), vendorCode);
        assertEquals(order.getId().toString(), orderNumber);
    }

    @Test
    void parseToDto_passEventDateShouldBeMultiple_whenOrderHasMultiplePasses() {
        order.setPasses(new ArrayList<>(Arrays.asList(
                new Pass(
                        A_PASS_NUMBER,
                        categoryBuilder.buildByName(A_PASS_CATEGORY),
                        optionBuilder.buildByName(SINGLE_PASS_OPTION),
                        SOME_EVENT_DATE
                ),
                new Pass(
                        ANOTHER_PASS_NUMBER,
                        categoryBuilder.buildByName(A_PASS_CATEGORY),
                        optionBuilder.buildByName(SINGLE_PASS_OPTION),
                        SOME_OTHER_EVENT_DATE
                )
        )));

        OrderWithPassesAsPassesDto dto = subject.toDto(order);

        assertEquals(2, dto.passes.size());
    }

    @Test
    void parseToDto_passEventDateShouldBeNull_whenOrderHasPackagePass() {
        order.setPasses(new ArrayList<>(Collections.singletonList(new Pass(
                        A_PASS_NUMBER,
                        categoryBuilder.buildByName(A_PASS_CATEGORY),
                        optionBuilder.buildByName(PACKAGE_PASS_OPTION)
                )
        )));

        OrderWithPassesAsPassesDto dto = subject.toDto(order);

        assertNull(dto.passes.get(0).eventDate);
    }
}