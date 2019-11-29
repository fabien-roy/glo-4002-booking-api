package ca.ulaval.glo4002.booking.festival.services;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.program.events.domain.EventDateFactory;
import ca.ulaval.glo4002.booking.festival.rest.EventDatesRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class FestivalServiceTest {

    private FestivalService service;
    private FestivalConfiguration festivalConfiguration;
    private EventDateFactory eventDateFactory;

    @BeforeEach
    void setUpService() {
        festivalConfiguration = mock(FestivalConfiguration.class);
        eventDateFactory = mock(EventDateFactory.class);

        service = new FestivalService(festivalConfiguration, eventDateFactory);
    }

    @Test
    void setConfiguration_shouldSetStartEventDate() {
        EventDate expectedStartEventDate = FestivalConfiguration.getDefaultStartEventDate().plusDays(1);
        EventDatesRequest eventDatesRequest = mock(EventDatesRequest.class);
        when(eventDatesRequest.getBeginDate()).thenReturn(expectedStartEventDate.toString());
        when(eventDateFactory.parse(expectedStartEventDate.toString())).thenReturn(expectedStartEventDate);

        service.setEventDates(eventDatesRequest);

        verify(festivalConfiguration).setStartEventDate(eq(expectedStartEventDate));
    }

    @Test
    void setConfiguration_shouldSetEndEventDate() {
        EventDate expectedEndEventDate = FestivalConfiguration.getDefaultEndEventDate().minusDays(1);
        EventDatesRequest eventDatesRequest = mock(EventDatesRequest.class);
        when(eventDatesRequest.getEndDate()).thenReturn(expectedEndEventDate.toString());
        when(eventDateFactory.parse(expectedEndEventDate.toString())).thenReturn(expectedEndEventDate);

        service.setEventDates(eventDatesRequest);

        verify(festivalConfiguration).setEndEventDate(eq(expectedEndEventDate));
    }
}