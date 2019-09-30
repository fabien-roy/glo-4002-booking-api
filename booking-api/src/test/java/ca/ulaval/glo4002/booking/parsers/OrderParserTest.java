package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.constants.VendorConstants;
import ca.ulaval.glo4002.booking.domainObjects.orders.Order;
import ca.ulaval.glo4002.booking.dto.OrderDto;
import ca.ulaval.glo4002.booking.exceptions.VendorNotFoundException;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderDtoInvalidException;
import ca.ulaval.glo4002.booking.util.FestivalDateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderParserTest {

    private static final String A_DATE_BEFORE_ORDER_START_DATE_TIME = FestivalConstants.Dates.ORDER_START_DATE_TIME.minusDays(1).toString();
    private static final String A_DATE_AFTER_ORDER_START_END_TIME = FestivalConstants.Dates.ORDER_END_DATE_TIME.plusDays(1).toString();
    private static final LocalDateTime A_VALID_DATE = FestivalConstants.Dates.ORDER_START_DATE_TIME;
    private static final String AN_INVALID_VENDOR_CODE = "An invalid vendor code";
    private static final String A_VALID_VENDOR_CODE = VendorConstants.TEAM_VENDOR_CODE;
    private OrderParser subject;
    private OrderDto orderDto = new OrderDto();

    @BeforeEach
    void setUp() {
        subject = new OrderParser();
        orderDto.orderDate = FestivalDateUtil.toZonedDateTimeString(A_VALID_DATE);
        orderDto.vendorCode = A_VALID_VENDOR_CODE;
    }

    @Test
    void whenOrderDateIsBeforeOrderStart_thenOrderDtoInvalidExceptionIsThrown() {
        orderDto.orderDate = A_DATE_BEFORE_ORDER_START_DATE_TIME;

        OrderDtoInvalidException thrown = assertThrows(
                OrderDtoInvalidException.class,
                ()->subject.parseDto(orderDto)
        );

        assertEquals(ExceptionConstants.ORDER_DTO_INVALID_MESSAGE, thrown.getMessage());
    }

    @Test
    void whenOrderDateIsAfterOrderStart_thenOrderDtoInvalidExceptionIsThrown() {
        orderDto.orderDate = A_DATE_AFTER_ORDER_START_END_TIME;

        OrderDtoInvalidException thrown = assertThrows(
                OrderDtoInvalidException.class,
                ()->subject.parseDto(orderDto)
        );

        assertEquals(ExceptionConstants.ORDER_DTO_INVALID_MESSAGE, thrown.getMessage());
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

        assertEquals(ExceptionConstants.VENDOR_NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    void whenVendorCodeIsValid_thenVendorIsAssignedToOrder() {
        orderDto.vendorCode = A_VALID_VENDOR_CODE;

        Order order = subject.parseDto(orderDto);

        assertNotNull(order.getVendor().getCode());
        assertEquals(A_VALID_VENDOR_CODE, order.getVendor().getCode());
    }

    // TODO : Test parseEntity

    // TODO : Test toEntity

    // TODO : Test toDto
}