package ca.ulaval.glo4002.booking.orders.domain;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void toLocalDate_shouldReturnValueAsLocalDate() {
        LocalDateTime value = FestivalConfiguration.getDefaultStartOrderDate().getValue();
        LocalDate expectedLocalDate = value.toLocalDate();
        orderDate = new OrderDate(value);

        LocalDate localDate = orderDate.toLocalDate();

        assertEquals(expectedLocalDate, localDate);
    }

    @Test
    void equals_shouldReturnFalse_whenObjectIsNotEventDate() {
        orderDate = FestivalConfiguration.getDefaultStartOrderDate();
        Object object = new Object();

        boolean result = orderDate.equals(object);

        assertFalse(result);
    }

    @Test
    void equals_shouldReturnTrue_whenEventDateHasSameValue() {
        LocalDateTime aValue = FestivalConfiguration.getDefaultStartOrderDate().getValue();
        orderDate = new OrderDate(aValue);
        OrderDate other = new OrderDate(aValue);

        boolean result = orderDate.equals(other);

        assertTrue(result);
    }

    @Test
    void equals_shouldReturnFalse_whenEventDateHasDifferentValue() {
        orderDate = FestivalConfiguration.getDefaultStartOrderDate();
        OrderDate other = orderDate.plusDays(1);

        boolean result = orderDate.equals(other);

        assertFalse(result);
    }

    @Test
    void hashCode_shouldReturnValueHashCode() {
        LocalDateTime aValue = FestivalConfiguration.getDefaultStartOrderDate().getValue();
        int expectedHashCode = aValue.hashCode();
        orderDate = new OrderDate(aValue);

        int hashCode = orderDate.hashCode();

        assertEquals(expectedHashCode, hashCode);
    }
}