package ca.ulaval.glo4002.booking.configuration;

import ca.ulaval.glo4002.booking.events.EventDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationTest {

    private Configuration configuration;

    @BeforeEach
    void setUpConfiguration() {
        this.configuration = new Configuration();
    }

    @Test
    void getStartEventDate_shouldReturnDefaultStartEventDate_whenNoneIsSet() {
        EventDate expectedEventDate = EventDate.getDefaultStartEventDate();

        EventDate eventDate = configuration.getStartEventDate();

        assertEquals(expectedEventDate, eventDate);
    }

    @Test
    void getEndEventDate_shouldReturnDefaultEndEventDate_whenNoneIsSet() {
        EventDate expectedEventDate = EventDate.getDefaultEndEventDate();

        EventDate eventDate = configuration.getEndEventDate();

        assertEquals(expectedEventDate, eventDate);
    }

    @Test
    void setStartEventDate_shouldSetStartEventDate() {
        EventDate expectedEventDate = EventDate.getDefaultStartEventDate().plusDays(1);

        configuration.setStartEventDate(expectedEventDate);
        EventDate eventDate = configuration.getStartEventDate();

        assertEquals(expectedEventDate, eventDate);
    }

    @Test
    void setEndEventDate_shouldSetEndEventDate() {
        EventDate expectedEventDate = EventDate.getDefaultEndEventDate().minusDays(1);

        configuration.setEndEventDate(expectedEventDate);
        EventDate eventDate = configuration.getEndEventDate();

        assertEquals(expectedEventDate, eventDate);
    }
}