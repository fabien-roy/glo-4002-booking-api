package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.events.Event;

import java.util.ArrayList;
import java.util.List;

public class InMemoryEventRepository implements EventRepository {

    private List<Event> events;

    public InMemoryEventRepository() {
        events = new ArrayList<>();
    }

    public void add(Event event) {
        // TODO
    }
}
