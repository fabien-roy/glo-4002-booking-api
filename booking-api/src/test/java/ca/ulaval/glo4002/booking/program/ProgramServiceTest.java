package ca.ulaval.glo4002.booking.program;

import ca.ulaval.glo4002.booking.events.EventFactory;
import ca.ulaval.glo4002.booking.events.EventRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventoryService;
import ca.ulaval.glo4002.booking.shuttles.trips.TripService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
        // TODO
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void add_shouldOrderOxygenTanksForEachArtist(int eventQuantity) {
        // TODO
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void add_shouldOrderOxygenTanksForEachActivity(int eventQuantity) {
        // TODO
    }
}