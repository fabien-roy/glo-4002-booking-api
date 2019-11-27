package ca.ulaval.glo4002.booking;

import ca.ulaval.glo4002.booking.events.EventDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookingConfigurationTest {

    private BookingConfiguration configuration;

    @BeforeEach
    void setUpConfiguration() {
        this.configuration = new BookingConfiguration();
    }

    @Test
    void getStartEventDate_shouldReturnDefaultStartEventDate_whenNoneIsSet() {
        EventDate expectedEventDate = EventDate.getStartEventDate();

        EventDate eventDate = configuration.getStartEventDate();

        assertEquals(expectedEventDate, eventDate);
    }

    @Test
    void getEndEventDate_shouldReturnDefaultEndEventDate_whenNoneIsSet() {
        EventDate expectedEventDate = EventDate.getEndEventDate();

        EventDate eventDate = configuration.getEndEventDate();

        assertEquals(expectedEventDate, eventDate);
    }

    @Test
    void setStartEventDate_shouldSetStartEventDate() {
        EventDate expectedEventDate = EventDate.getStartEventDate().plusDays(1);

        configuration.setStartEventDate(expectedEventDate);
        EventDate eventDate = configuration.getStartEventDate();

        assertEquals(expectedEventDate, eventDate);
    }

    @Test
    void setEndEventDate_shouldSetEndEventDate() {
        EventDate expectedEventDate = EventDate.getEndEventDate().minusDays(1);

        configuration.setEndEventDate(expectedEventDate);
        EventDate eventDate = configuration.getEndEventDate();

        assertEquals(expectedEventDate, eventDate);
    }
}