package ca.ulaval.glo4002.booking.program.events.domain;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.program.events.rest.exceptions.InvalidEventDateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EventDateFactoryTest {

    EventDateFactory factory;
    private FestivalConfiguration festivalConfiguration;

    @BeforeEach
    void setUpFactory() {
        factory = new EventDateFactory(festivalConfiguration);
    }

    @BeforeEach
    void setUpConfiguration() {
        festivalConfiguration = mock(FestivalConfiguration.class);

        when(festivalConfiguration.getStartEventDate()).thenReturn(EventDate.getDefaultStartEventDate());
        when(festivalConfiguration.getEndEventDate()).thenReturn(EventDate.getDefaultEndEventDate());
    }

    @Test
    void create_shouldCreateEventDate() {
        EventDate expectedEventDate  = festivalConfiguration.getStartEventDate();

        EventDate eventDate = factory.create(expectedEventDate.toString());

        assertEquals(expectedEventDate, eventDate);
    }

    @Test
    void create_shouldThrowInvalidEventDateException_whenEventDateIsUnderBounds() {
        EventDate aUnderBoundEventDate  = festivalConfiguration.getStartEventDate().minusDays(1);

        assertThrows(InvalidEventDateException.class, () -> factory.create(aUnderBoundEventDate.toString()));
    }

    @Test
    void create_shouldThrowInvalidEventDateException_whenEventDateIsOverBounds() {
        EventDate aOverBoundEventDate  = festivalConfiguration.getEndEventDate().plusDays(1);

        assertThrows(InvalidEventDateException.class, () -> factory.create(aOverBoundEventDate.toString()));
    }

    @Test
    void create_shouldThrowInvalidFormatException_whenEventDateIsInvalid() {
        String anInvalidDate = "anInvalidDate";

        assertThrows(InvalidFormatException.class, () -> factory.create(anInvalidDate));
    }
}