package ca.ulaval.glo4002.booking.program.events.domain;

import ca.ulaval.glo4002.booking.program.events.domain.Event;

import java.util.List;

public interface EventRepository {

    void addAll(List<Event> event);

    List<Event> findAll();
}
