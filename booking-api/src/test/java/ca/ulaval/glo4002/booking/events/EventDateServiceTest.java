package ca.ulaval.glo4002.booking.events;

import ca.ulaval.glo4002.booking.configuration.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class EventDateServiceTest {

    private EventDateService service;
    private Configuration configuration;
    private EventDateFactory eventDateFactory;

    @BeforeEach
    void setUpService() {
        configuration = mock(Configuration.class);
        eventDateFactory = mock(EventDateFactory.class);

        service = new EventDateService(configuration, eventDateFactory);
    }

    @Test
    void setConfiguration_shouldSetStartEventDate() {
        EventDate expectedStartEventDate = EventDate.getStartEventDate().plusDays(1);
        EventDatesDto eventDatesDto = mock(EventDatesDto.class);
        when(eventDatesDto.getBeginDate()).thenReturn(expectedStartEventDate.toString());
        when(eventDateFactory.parse(expectedStartEventDate.toString())).thenReturn(expectedStartEventDate);

        service.setConfiguration(eventDatesDto);

        verify(configuration).setStartEventDate(eq(expectedStartEventDate));
    }

    @Test
    void setConfiguration_shouldSetEndEventDate() {
        EventDate expectedEndEventDate = EventDate.getEndEventDate().minusDays(1);
        EventDatesDto eventDatesDto = mock(EventDatesDto.class);
        when(eventDatesDto.getEndDate()).thenReturn(expectedEndEventDate.toString());
        when(eventDateFactory.parse(expectedEndEventDate.toString())).thenReturn(expectedEndEventDate);

        service.setConfiguration(eventDatesDto);

        verify(configuration).setEndEventDate(eq(expectedEndEventDate));
    }
}