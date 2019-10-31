package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.NumberGenerator;
import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.dto.PassListDto;
import ca.ulaval.glo4002.booking.enums.PassOptions;
import ca.ulaval.glo4002.booking.exceptions.orders.InvalidOrderDateFormatException;
import ca.ulaval.glo4002.booking.exceptions.orders.InvalidOrderFormatException;
import ca.ulaval.glo4002.booking.exceptions.orders.OutOfBoundsOrderDateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderFactoryTest {

    private OrderFactory subject;

    @BeforeEach
    void setUpSubject() {
        NumberGenerator numberGenerator = new NumberGenerator();
        PassListFactory passListFactory = mock(PassListFactory.class);

        this.subject = new OrderFactory(numberGenerator, passListFactory);
    }

    @Test
    void buildWithDto_shouldParseDtoWithCorrectOrderDate() {
        ZonedDateTime orderDate = ZonedDateTime.of(OrderFactory.START_DATE_TIME.plusDays(1), ZoneId.systemDefault());
        PassListDto passListDto = mock(PassListDto.class);
        when(passListDto.getPassOption()).thenReturn(PassOptions.PACKAGE.toString());
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                orderDate.toString(),
                "TEAM",
                passListDto
        );

        Order order = subject.buildWithDto(orderDto);

        assertEquals(orderDate.toLocalDateTime(), order.getOrderDate());
    }

    @Test
    void buildWithDto_shouldParseDtoWithCorrectVendorCode() {
        ZonedDateTime orderDate = ZonedDateTime.of(OrderFactory.START_DATE_TIME.plusDays(1), ZoneId.systemDefault());
        PassListDto passListDto = mock(PassListDto.class);
        when(passListDto.getPassOption()).thenReturn(PassOptions.PACKAGE.toString());
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                orderDate.toString(),
                "TEAM",
                passListDto
        );

        Order order = subject.buildWithDto(orderDto);

        assertEquals(orderDto.getVendorCode(), order.getVendorCode());
    }

    @Test
    void buildWithDto_shouldThrowInvalidOrderFormatException_whenThereIsNoPass() {
        ZonedDateTime orderDate = ZonedDateTime.of(OrderFactory.START_DATE_TIME.plusDays(1), ZoneId.systemDefault());
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                orderDate.toString(),
                "TEAM",
                null
        );

        assertThrows(InvalidOrderFormatException.class, () -> subject.buildWithDto(orderDto));
    }

    @Test
    void buildWithDto_shouldThrowInvalidOrderDateException_whenOrderDateIsInvalid() {
        String anInvalidOrderDate = "anInvalidDate";
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                anInvalidOrderDate,
                "TEAM",
                mock(PassListDto.class)
        );

        assertThrows(
                InvalidOrderDateFormatException.class,
                () -> subject.buildWithDto(orderDto)
        );
    }

    @Test
    void buildWithDto_shouldThrowOutOfBoundsOrderDateException_whenOrderDateIsUnderBounds() {
        LocalDateTime aUnderBoundValue = OrderFactory.START_DATE_TIME.minusDays(1);
        ZonedDateTime aUnderBoundZonedValue = ZonedDateTime.of(aUnderBoundValue, ZoneId.systemDefault());
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                aUnderBoundZonedValue.toString(),
                "TEAM",
                mock(PassListDto.class)
        );

        assertThrows(
                OutOfBoundsOrderDateException.class,
                () -> subject.buildWithDto(orderDto)
        );
    }

    @Test
    void buildWithDto_shouldThrowOutOfBoundsOrderDateException_whenOrderDateIsOverBounds() {
        LocalDateTime aOverBoundValue = OrderFactory.END_DATE_TIME.plusDays(1);
        ZonedDateTime aOverBoundZonedValue = ZonedDateTime.of(aOverBoundValue, ZoneId.systemDefault());
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                aOverBoundZonedValue.toString(),
                "TEAM",
                mock(PassListDto.class)
        );

        assertThrows(
                OutOfBoundsOrderDateException.class,
                () -> subject.buildWithDto(orderDto)
        );
    }
}