package ca.ulaval.glo4002.booking.orders.rest.mappers;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.orders.domain.OrderDate;
import ca.ulaval.glo4002.booking.orders.rest.exceptions.InvalidOrderDateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderDateMapperTest {

    private OrderDateMapper factory;
    private FestivalConfiguration festivalConfiguration;

    @BeforeEach
    void setUpFactory() {
        festivalConfiguration = new FestivalConfiguration();

        factory = new OrderDateMapper(festivalConfiguration);
    }

    @Test
    void create_shouldCreateOrderDate() {
        OrderDate expectedOrderDate  = festivalConfiguration.getStartOrderDate();
        ZonedDateTime zonedExpectedOrderDate = ZonedDateTime.of(expectedOrderDate.getValue(), ZoneId.systemDefault());

        OrderDate orderDate = factory.fromString(zonedExpectedOrderDate.toString());

        assertEquals(expectedOrderDate, orderDate);
    }

    @Test
    void create_shouldThrowInvalidOrderDateException_whenOrderDateIsUnderBounds() {
        OrderDate aUnderBoundOrderDate  = festivalConfiguration.getStartOrderDate().minusDays(1);
        ZonedDateTime aZonedUnderBoundOrderDate = ZonedDateTime.of(aUnderBoundOrderDate.getValue(), ZoneId.systemDefault());

        assertThrows(InvalidOrderDateException.class, () -> factory.fromString(aZonedUnderBoundOrderDate.toString()));
    }

    @Test
    void create_shouldThrowInvalidOrderDateException_whenOrderDateIsOverBounds() {
        OrderDate aOverBoundOrderDate  = festivalConfiguration.getEndOrderDate().plusDays(1);
        ZonedDateTime aZonedOverBoundOrderDate = ZonedDateTime.of(aOverBoundOrderDate.getValue(), ZoneId.systemDefault());

        assertThrows(InvalidOrderDateException.class, () -> factory.fromString(aZonedOverBoundOrderDate.toString()));
    }

    @Test
    void create_shouldThrowInvalidFormatException_whenOrderDateIsInvalid() {
        String anInvalidDate = "anInvalidDate";

        assertThrows(InvalidFormatException.class, () -> factory.fromString(anInvalidDate));
    }
}