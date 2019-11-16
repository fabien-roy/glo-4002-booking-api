package ca.ulaval.glo4002.booking.program;

import ca.ulaval.glo4002.booking.artists.BookingArtist;
import ca.ulaval.glo4002.booking.events.Event;
import ca.ulaval.glo4002.booking.events.EventDate;
import ca.ulaval.glo4002.booking.events.EventFactory;
import ca.ulaval.glo4002.booking.events.EventRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventoryService;
import ca.ulaval.glo4002.booking.shuttles.trips.TripService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProgramServiceTest {

    ProgramService service;
    private EventRepository eventRepository;
    private EventFactory eventFactory;
    private TripService tripService;
    private OxygenInventoryService oxygenInventoryService;

    @BeforeEach
    void setUpService() {
        eventRepository = mock(EventRepository.class);
        eventFactory = mock(EventFactory.class);
        tripService = mock(TripService.class);
        oxygenInventoryService = mock(OxygenInventoryService.class);

        service = new ProgramService(eventRepository, eventFactory, tripService, oxygenInventoryService);
    }

    @Test
    void add_shouldSaveEventsToRepository() {
        ProgramDto aProgramDto = mock(ProgramDto.class);

        service.add(aProgramDto);

        verify(eventRepository).addAll(any());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void add_shouldOrderTripsForEachArtist(int eventQuantity) {
        ProgramDto aProgramDto = mock(ProgramDto.class);
        Event aEvent = mock(Event.class);
        BookingArtist expectedArtist = mock(BookingArtist.class);
        EventDate expectedEventDate = mock(EventDate.class);
        when(aEvent.getArtist()).thenReturn(expectedArtist);
        when(aEvent.getEventDate()).thenReturn(expectedEventDate);
        when(eventFactory.build(any())).thenReturn(Collections.nCopies(eventQuantity, aEvent));

        service.add(aProgramDto);

        verify(tripService, times(eventQuantity)).orderForArtist(eq(expectedArtist), eq(expectedEventDate));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void add_shouldOrderOxygenTanksForEachArtist(int eventQuantity) {
        ProgramDto aProgramDto = mock(ProgramDto.class);
        Event aEvent = mock(Event.class);
        BookingArtist expectedArtist = mock(BookingArtist.class);
        LocalDate expectedOrderDate = LocalDate.now();
        when(aEvent.getArtist()).thenReturn(expectedArtist);
        when(eventFactory.build(any())).thenReturn(Collections.nCopies(eventQuantity, aEvent));

        service.add(aProgramDto);

        verify(oxygenInventoryService, times(eventQuantity)).orderForArtist(eq(expectedArtist), eq(expectedOrderDate));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void add_shouldOrderOxygenTanksForEachActivity(int eventQuantity) {
        ProgramDto aProgramDto = mock(ProgramDto.class);
        Event aEvent = mock(Event.class);
        when(eventFactory.build(any())).thenReturn(Collections.nCopies(eventQuantity, aEvent));

        service.add(aProgramDto);

        verify(oxygenInventoryService, times(eventQuantity)).orderForActivity();
    }
}