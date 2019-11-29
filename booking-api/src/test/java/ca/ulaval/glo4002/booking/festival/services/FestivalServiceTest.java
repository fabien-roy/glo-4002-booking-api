package ca.ulaval.glo4002.booking.festival.services;

import ca.ulaval.glo4002.booking.festival.domain.Festival;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.program.events.domain.EventDateFactory;
import ca.ulaval.glo4002.booking.program.events.rest.EventDatesDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class FestivalServiceTest {

    private FestivalService service;
    private Festival festival;
    private EventDateFactory eventDateFactory;

    @BeforeEach
    void setUpService() {
        festival = mock(Festival.class);
        eventDateFactory = mock(EventDateFactory.class);

        service = new FestivalService(festival, eventDateFactory);
    }

    @Test
    void setConfiguration_shouldSetStartEventDate() {
        EventDate expectedStartEventDate = EventDate.getDefaultStartEventDate().plusDays(1);
        EventDatesDto eventDatesDto = mock(EventDatesDto.class);
        when(eventDatesDto.getBeginDate()).thenReturn(expectedStartEventDate.toString());
        when(eventDateFactory.parse(expectedStartEventDate.toString())).thenReturn(expectedStartEventDate);

        service.setEventDates(eventDatesDto);

        verify(festival).setStartEventDate(eq(expectedStartEventDate));
    }

    @Test
    void setConfiguration_shouldSetEndEventDate() {
        EventDate expectedEndEventDate = EventDate.getDefaultEndEventDate().minusDays(1);
        EventDatesDto eventDatesDto = mock(EventDatesDto.class);
        when(eventDatesDto.getEndDate()).thenReturn(expectedEndEventDate.toString());
        when(eventDateFactory.parse(expectedEndEventDate.toString())).thenReturn(expectedEndEventDate);

        service.setEventDates(eventDatesDto);

        verify(festival).setEndEventDate(eq(expectedEndEventDate));
    }
}