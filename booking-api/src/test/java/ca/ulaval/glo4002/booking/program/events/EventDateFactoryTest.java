package ca.ulaval.glo4002.booking.program.events;

import ca.ulaval.glo4002.booking.festival.Festival;
import ca.ulaval.glo4002.booking.errors.InvalidFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EventDateFactoryTest {

    EventDateFactory factory;
    private Festival festival;

    @BeforeEach
    void setUpFactory() {
        factory = new EventDateFactory(festival);
    }

    @BeforeEach
    void setUpConfiguration() {
        festival = mock(Festival.class);

        when(festival.getStartEventDate()).thenReturn(EventDate.getDefaultStartEventDate());
        when(festival.getEndEventDate()).thenReturn(EventDate.getDefaultEndEventDate());
    }

    @Test
    void build_shouldThrowInvalidEventDateException_whenEventDateIsUnderBounds() {
        EventDate aUnderBoundEventDate  = festival.getStartEventDate().minusDays(1);

        assertThrows(InvalidEventDateException.class, () -> factory.build(aUnderBoundEventDate.toString()));
    }

    @Test
    void build_shouldThrowInvalidEventDateException_whenEventDateIsOverBounds() {
        EventDate aOverBoundEventDate  = festival.getEndEventDate().plusDays(1);

        assertThrows(InvalidEventDateException.class, () -> factory.build(aOverBoundEventDate.toString()));
    }

    @Test
    void build_shouldThrowInvalidFormatException_whenEventDateIsInvalid() {
        String anInvalidDate = "anInvalidDate";

        assertThrows(InvalidFormatException.class, () -> factory.build(anInvalidDate));
    }
}