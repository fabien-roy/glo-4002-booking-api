package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.events.Event;

import java.util.List;

public interface EventRepository {

    void addAll(List<Event> event);
    
    List<Event> findAll();
}
