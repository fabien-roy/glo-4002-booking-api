package ca.ulaval.glo4002.booking.orders.domain;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderDateTest {

    OrderDate orderDate;

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void plusDays_shouldAddCorrectNumberOfDays(int days) {
        LocalDateTime originalValue = FestivalConfiguration.getDefaultStartOrderDate().getValue();
        orderDate = new OrderDate(originalValue);
        LocalDateTime expectedValue = originalValue.plusDays(days);

        OrderDate newOrderDate = orderDate.plusDays(days);

        assertEquals(expectedValue, newOrderDate.getValue());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void minusDays_shouldSubtractCorrectNumberOfDays(int days) {
        LocalDateTime originalValue = FestivalConfiguration.getDefaultEndOrderDate().getValue();
        orderDate = new OrderDate(originalValue);
        LocalDateTime expectedValue = originalValue.minusDays(days);

        OrderDate newOrderDate = orderDate.minusDays(days);

        assertEquals(expectedValue, newOrderDate.getValue());
    }
}