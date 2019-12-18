package ca.ulaval.glo4002.booking.program.events.infrastructure;

import ca.ulaval.glo4002.booking.program.events.domain.Event;
import ca.ulaval.glo4002.booking.program.events.domain.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class InMemoryEventRepositoryTest {

    private EventRepository repository;

    @BeforeEach
    void setUpRepository() {
        repository = new InMemoryEventRepository();
    }

    @Test
    void addAll_shouldSaveAllEvents() {
        Event expectedEvent = mock(Event.class);
        Event otherExpectedEvent = mock(Event.class);
        List<Event> events = Arrays.asList(expectedEvent, otherExpectedEvent);

        repository.addAll(events);
        List<Event> returnedEvents = repository.findAll();

        assertTrue(returnedEvents.stream().anyMatch(expectedEvent::equals));
        assertTrue(returnedEvents.stream().anyMatch(otherExpectedEvent::equals));
    }
}