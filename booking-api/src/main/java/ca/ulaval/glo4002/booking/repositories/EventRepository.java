package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.events.Event;

public interface EventRepository {

    void add(Event event);
}
