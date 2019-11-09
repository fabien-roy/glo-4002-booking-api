package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.BookingArtist;
import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.events.Event;
import ca.ulaval.glo4002.booking.domain.events.EventDate;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.dto.events.ProgramDto;
import ca.ulaval.glo4002.booking.enums.Activities;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;
import ca.ulaval.glo4002.booking.factories.EventFactory;
import ca.ulaval.glo4002.booking.repositories.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProgramServiceTest {

    private ProgramService programService;
    private EventRepository eventRepository;
    private EventFactory eventFactory;
    private TripService tripService;

    @BeforeEach
    void setUpService() {
        eventRepository = mock(EventRepository.class);
        eventFactory = mock(EventFactory.class);
        tripService = mock(TripService.class);
        OxygenTankInventoryService oxygenTankInventoryService = mock(OxygenTankInventoryService.class);

        programService = new ProgramService(eventRepository, eventFactory, tripService, oxygenTankInventoryService);
    }

    @Test
    void add_shouldAddProgram() {
        ProgramDto aProgramDto = mock(ProgramDto.class);

        programService.add(aProgramDto);

        verify(eventRepository).addAll(any());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void add_shouldOrderTripForArtistOnceForEveryEvent(int eventAmount) {
        Integer memberAmount = 1;
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        BookingArtist aArtist = new BookingArtist(new Number(1L), "aArtist", mock(Money.class), memberAmount, "aMusicStyle", 1);
        Event aEvent = new Event(aEventDate, Activities.YOGA, aArtist);
        doReturn(Collections.nCopies(eventAmount, aEvent)).when(eventFactory).build(any());

        programService.add(mock(ProgramDto.class));

        verify(tripService, times(eventAmount)).orderForArtist(any(), any());
    }

    @Test
    void add_shouldOrderTripForArtistForCorrectEventDate() {
        Integer memberAmount = 1;
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        BookingArtist aArtist = new BookingArtist(new Number(1L), "aArtist", mock(Money.class), memberAmount, "aMusicStyle", 1);
        Event aEvent = new Event(aEventDate, Activities.YOGA, aArtist);
        doReturn(Collections.singletonList(aEvent)).when(eventFactory).build(any());

        programService.add(mock(ProgramDto.class));

        verify(tripService).orderForArtist(any(), eq(aEventDate));
    }

    // TODO : Oxygen tests for artists

    // TODO : Oxygen tests for activities
}