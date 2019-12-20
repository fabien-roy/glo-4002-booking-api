package ca.ulaval.glo4002.booking.program.events.domain;

import java.util.List;

public interface EventRepository {

    void addAll(List<Event> event);

    List<Event> findAll();
}
