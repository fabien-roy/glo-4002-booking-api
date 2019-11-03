package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.NumberGenerator;
import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.dto.PassBundleDto;
import ca.ulaval.glo4002.booking.enums.PassOptions;
import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.exceptions.InvalidOrderDateException;
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
        PassBundleFactory passBundleFactory = mock(PassBundleFactory.class);

        this.subject = new OrderFactory(numberGenerator, passBundleFactory);
    }

    @Test
    void build_shouldParseDtoWithCorrectOrderDate() {
        ZonedDateTime orderDate = ZonedDateTime.of(OrderFactory.START_DATE_TIME.plusDays(1), ZoneId.systemDefault());
        PassBundleDto passBundleDto = mock(PassBundleDto.class);
        when(passBundleDto.getPassOption()).thenReturn(PassOptions.PACKAGE.toString());
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                orderDate.toString(),
                "TEAM",
                passBundleDto
        );

        Order order = subject.build(orderDto);

        assertEquals(orderDate.toLocalDateTime(), order.getOrderDate());
    }

    @Test
    void build_shouldParseDtoWithCorrectVendorCode() {
        ZonedDateTime orderDate = ZonedDateTime.of(OrderFactory.START_DATE_TIME.plusDays(1), ZoneId.systemDefault());
        PassBundleDto passBundleDto = mock(PassBundleDto.class);
        when(passBundleDto.getPassOption()).thenReturn(PassOptions.PACKAGE.toString());
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                orderDate.toString(),
                "TEAM",
                passBundleDto
        );

        Order order = subject.build(orderDto);

        assertEquals(orderDto.getVendorCode(), order.getVendorCode());
    }

    @Test
    void build_shouldThrowInvalidFormatException_whenThereIsNoPass() {
        ZonedDateTime orderDate = ZonedDateTime.of(OrderFactory.START_DATE_TIME.plusDays(1), ZoneId.systemDefault());
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                orderDate.toString(),
                "TEAM",
                null
        );

        assertThrows(InvalidFormatException.class, () -> subject.build(orderDto));
    }

    @Test
    void build_shouldThrowInvalidFormatException_whenOrderDateIsInvalid() {
        String anInvalidOrderDate = "anInvalidDate";
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                anInvalidOrderDate,
                "TEAM",
                mock(PassBundleDto.class)
        );

        assertThrows(InvalidFormatException.class, () -> subject.build(orderDto));
    }

    @Test
    void build_shouldThrowInvalidOrderDateException_whenOrderDateIsUnderBounds() {
        LocalDateTime aUnderBoundValue  = OrderFactory.START_DATE_TIME.minusDays(1);
        ZonedDateTime aUnderBoundZonedValue = ZonedDateTime.of(aUnderBoundValue, ZoneId.systemDefault());
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                aUnderBoundZonedValue.toString(),
                "TEAM",
                mock(PassBundleDto.class)
        );

        assertThrows(
                InvalidOrderDateException.class,
                () -> subject.build(orderDto)
        );
    }

    @Test
    void build_shouldThrowInvalidOrderDateException_whenOrderDateIsOverBounds() {
        LocalDateTime aOverBoundValue  = OrderFactory.END_DATE_TIME.plusDays(1);
        ZonedDateTime aOverBoundZonedValue = ZonedDateTime.of(aOverBoundValue, ZoneId.systemDefault());
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                aOverBoundZonedValue.toString(),
                "TEAM",
                mock(PassBundleDto.class)
        );

        assertThrows(InvalidOrderDateException.class, () -> subject.build(orderDto));
    }
}