package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.dto.OrderDto;
import ca.ulaval.glo4002.booking.exceptions.VendorNotFoundException;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderDtoInvalidException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderParserTest {

    private static final LocalDateTime INVALID_DATE_BEFORE = LocalDateTime.of(2049, 1, 1, 0, 0, 0);
    private static final LocalDateTime INVALID_DATE_AFTER = LocalDateTime.of(2051, 1, 1, 0, 0);
    private static final LocalDateTime VALID_DATE = LocalDateTime.of(2050, 6, 6, 1, 0);
    private static final String INVALID_VENDOR_CODE = "FOO";
    private static final String VALID_VENDOR_CODE = "TEAM";
    private OrderParser subject;
    private OrderDto orderDto;

    @BeforeEach
    void setUp(){
        subject = new OrderParser();
        orderDto = new OrderDto();
    }

    @Test
    void whenOrderDateIsBeforeOrderStart_thenOrderDtoInvalidExceptionIsThrown(){
        orderDto.setOrderDate(INVALID_DATE_BEFORE);
        OrderDtoInvalidException thrown = assertThrows(
                OrderDtoInvalidException.class,
                ()->subject.parse(orderDto)
        );

        assertEquals(ExceptionConstants.ORDER_DTO_INVALID_MESSAGE, thrown.getMessage());
    }

    @Test
    void whenOrderDateIsAfterOrderStart_thenOrderDtoInvalidExceptionIsThrown(){
        orderDto.setOrderDate(INVALID_DATE_AFTER);
        OrderDtoInvalidException thrown = assertThrows(
                OrderDtoInvalidException.class,
                ()->subject.parse(orderDto)
        );

        assertEquals(ExceptionConstants.ORDER_DTO_INVALID_MESSAGE, thrown.getMessage());
    }

    @Test
    void whenOrderDateIsBetweenValidOrderDate_thenOrderDateIsAssignedToOrder(){
        orderDto.setOrderDate(VALID_DATE);
        subject.parse(orderDto);
        assertTrue(VALID_DATE.isEqual(subject.getOrder().getOrderDate()));
    }

    @Test
    void whenVendorCodeIsInvalid_thenVendorNotFoundExceptionIsThrown(){
        orderDto.setOrderDate(VALID_DATE);
        orderDto.setVendorCode(INVALID_VENDOR_CODE);
        VendorNotFoundException thrown = assertThrows(
                VendorNotFoundException.class,
                ()->subject.parse(orderDto)
        );
        assertEquals(ExceptionConstants.VENDOR_NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    void whenVendorCodeIsValid_thenVendorIsAssignedToOrder(){
        orderDto.setOrderDate(VALID_DATE);
        orderDto.setVendorCode(VALID_VENDOR_CODE);
        subject.parse(orderDto);
        assertNotNull(subject.getOrder().getVendor().getName());
        assertEquals(VALID_VENDOR_CODE, subject.getOrder().getVendor().getName());
    }

}