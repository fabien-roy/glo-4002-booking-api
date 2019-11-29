package ca.ulaval.glo4002.booking.program.events.infrastructure;

import ca.ulaval.glo4002.booking.program.events.domain.Event;
import ca.ulaval.glo4002.booking.program.events.infrastructure.EventRepository;
import ca.ulaval.glo4002.booking.program.events.infrastructure.InMemoryEventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class InMemoryEventRepositoryTest {

    private EventRepository repository;

    @BeforeEach
    void setUpRepository() {
        repository = new InMemoryEventRepository();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void addAll_shouldSaveAllEvents(int amountOfEvents) {
        List<Event> events = Collections.nCopies(amountOfEvents, mock(Event.class));

        repository.addAll(events);
        List<Event> returnedEvents = repository.findAll();

        assertEquals(amountOfEvents, returnedEvents.size());
    }
}