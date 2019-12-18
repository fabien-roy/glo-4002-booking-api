package ca.ulaval.glo4002.booking.program.services;

import ca.ulaval.glo4002.booking.program.artists.domain.Artist;
import ca.ulaval.glo4002.booking.program.events.domain.Event;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.program.events.domain.EventFactory;
import ca.ulaval.glo4002.booking.program.events.domain.EventRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.services.OxygenInventoryService;
import ca.ulaval.glo4002.booking.program.rest.ProgramRequest;
import ca.ulaval.glo4002.booking.shuttles.trips.services.TripService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProgramServiceTest {

    private ProgramService service;
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
        ProgramRequest aProgramRequest = mock(ProgramRequest.class);

        service.add(aProgramRequest);

        verify(eventRepository).addAll(any());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void add_shouldOrderTripsForEachArtist(int eventQuantity) {
        ProgramRequest aProgramRequest = mock(ProgramRequest.class);
        Event aEvent = mock(Event.class);
        Artist expectedArtist = mock(Artist.class);
        EventDate expectedEventDate = mock(EventDate.class);
        when(aEvent.getArtist()).thenReturn(expectedArtist);
        when(aEvent.getEventDate()).thenReturn(expectedEventDate);
        when(eventFactory.create(any())).thenReturn(Collections.nCopies(eventQuantity, aEvent));

        service.add(aProgramRequest);

        verify(tripService, times(eventQuantity)).orderForArtist(eq(expectedArtist), eq(expectedEventDate));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void add_shouldOrderOxygenTanksForEachArtist(int eventQuantity) {
        ProgramRequest aProgramRequest = mock(ProgramRequest.class);
        Event aEvent = mock(Event.class);
        Artist expectedArtist = mock(Artist.class);
        EventDate expectedEventDate = mock(EventDate.class);
        when(aEvent.getArtist()).thenReturn(expectedArtist);
        when(aEvent.getEventDate()).thenReturn(expectedEventDate);
        when(eventFactory.create(any())).thenReturn(Collections.nCopies(eventQuantity, aEvent));

        service.add(aProgramRequest);

        verify(oxygenInventoryService, times(eventQuantity)).orderForArtist(eq(expectedArtist), eq(expectedEventDate));
    }
}