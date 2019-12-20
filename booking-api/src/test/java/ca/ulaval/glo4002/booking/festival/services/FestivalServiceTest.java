package ca.ulaval.glo4002.booking.festival.services;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.festival.rest.EventDatesRequest;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.program.events.rest.mappers.EventDateMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class FestivalServiceTest {

    private FestivalService service;
    private FestivalConfiguration festivalConfiguration;
    private EventDateMapper eventDateMapper;

    @BeforeEach
    void setUpService() {
        festivalConfiguration = mock(FestivalConfiguration.class);
        eventDateMapper = mock(EventDateMapper.class);

        service = new FestivalService(festivalConfiguration, eventDateMapper);
    }

    @Test
    void setConfiguration_shouldSetStartEventDate() {
        EventDate expectedStartEventDate = FestivalConfiguration.getDefaultStartEventDate().plusDays(1);
        EventDatesRequest eventDatesRequest = mock(EventDatesRequest.class);
        when(eventDatesRequest.getBeginDate()).thenReturn(expectedStartEventDate.toString());
        when(eventDateMapper.parse(expectedStartEventDate.toString())).thenReturn(expectedStartEventDate);

        service.setEventDates(eventDatesRequest);

        verify(festivalConfiguration).setStartEventDate(eq(expectedStartEventDate));
    }

    @Test
    void setConfiguration_shouldSetEndEventDate() {
        EventDate expectedEndEventDate = FestivalConfiguration.getDefaultEndEventDate().minusDays(1);
        EventDatesRequest eventDatesRequest = mock(EventDatesRequest.class);
        when(eventDatesRequest.getEndDate()).thenReturn(expectedEndEventDate.toString());
        when(eventDateMapper.parse(expectedEndEventDate.toString())).thenReturn(expectedEndEventDate);

        service.setEventDates(eventDatesRequest);

        verify(festivalConfiguration).setEndEventDate(eq(expectedEndEventDate));
    }
}