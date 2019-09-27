package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.constants.VendorConstants;
import ca.ulaval.glo4002.booking.dto.OrderDto;
import ca.ulaval.glo4002.booking.entities.orders.Order;
import ca.ulaval.glo4002.booking.exceptions.VendorNotFoundException;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderDtoInvalidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderParserTest {

    private static final LocalDateTime A_DATE_BEFORE_ORDER_START_DATE_TIME = LocalDateTime.of(2049, 1, 1, 0, 0, 0);
    private static final LocalDateTime A_DATE_AFTER_ORDER_START_END_TIME = LocalDateTime.of(2051, 1, 1, 0, 0);
    private static final LocalDateTime A_VALID_DATE = LocalDateTime.of(2050, 6, 6, 1, 0);
    private static final String AN_INVALID_VENDOR_CODE = "FOO";
    private static final String A_VALID_VENDOR_CODE = VendorConstants.TEAM_VENDOR_CODE;
    private OrderParser subject;
    private OrderDto orderDto = new OrderDto();

    @BeforeEach
    void setUp() {
        subject = new OrderParser();
        orderDto.orderDate = A_VALID_DATE;
        orderDto.vendorCode = A_VALID_VENDOR_CODE;
    }

    @Test
    void whenOrderDateIsBeforeOrderStart_thenOrderDtoInvalidExceptionIsThrown() {
        orderDto.orderDate = A_DATE_BEFORE_ORDER_START_DATE_TIME;

        OrderDtoInvalidException thrown = assertThrows(
                OrderDtoInvalidException.class,
                ()->subject.parse(orderDto)
        );

        assertEquals(ExceptionConstants.ORDER_DTO_INVALID_MESSAGE, thrown.getMessage());
    }

    @Test
    void whenOrderDateIsAfterOrderStart_thenOrderDtoInvalidExceptionIsThrown() {
        orderDto.orderDate = A_DATE_AFTER_ORDER_START_END_TIME;

        OrderDtoInvalidException thrown = assertThrows(
                OrderDtoInvalidException.class,
                ()->subject.parse(orderDto)
        );

        assertEquals(ExceptionConstants.ORDER_DTO_INVALID_MESSAGE, thrown.getMessage());
    }

    @Test
    void whenOrderDateIsBetweenValidOrderDate_thenOrderDateIsAssignedToOrder() {
        orderDto.orderDate = A_VALID_DATE;

        Order order = subject.parse(orderDto);

        assertEquals(A_VALID_DATE, order.getOrderDate());
    }

    @Test
    void whenVendorCodeIsInvalid_thenVendorNotFoundExceptionIsThrown() {
        orderDto.vendorCode = AN_INVALID_VENDOR_CODE;

        VendorNotFoundException thrown = assertThrows(
                VendorNotFoundException.class,
                ()->subject.parse(orderDto)
        );

        assertEquals(ExceptionConstants.VENDOR_NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    void whenVendorCodeIsValid_thenVendorIsAssignedToOrder() {
        orderDto.vendorCode = A_VALID_VENDOR_CODE;

        Order order = subject.parse(orderDto);

        assertNotNull(order.getVendor().getName());
        assertEquals(A_VALID_VENDOR_CODE, order.getVendor().getName());
    }

}