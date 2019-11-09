package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.dto.events.ProgramDto;
import ca.ulaval.glo4002.booking.factories.EventFactory;
import ca.ulaval.glo4002.booking.repositories.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ProgramServiceTest {

    private ProgramService programService;
    private EventRepository eventRepository;

    @BeforeEach
    void setUpService() {
        eventRepository = mock(EventRepository.class);
        EventFactory eventFactory = mock(EventFactory.class);
        TripService tripService = mock(TripService.class);
        OxygenTankInventoryService oxygenTankInventoryService = mock(OxygenTankInventoryService.class);

        programService = new ProgramService(eventRepository, eventFactory, tripService, oxygenTankInventoryService);
    }

    @Test
    void add_shouldAddProgram() {
        ProgramDto aProgramDto = mock(ProgramDto.class);

        programService.add(aProgramDto);

        verify(eventRepository).addAll(any());
    }
}