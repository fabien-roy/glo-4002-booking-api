package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.events.Event;
import ca.ulaval.glo4002.booking.dto.events.ProgramEventDto;

import java.util.ArrayList;
import java.util.List;

public class EventFactory {

    public List<Event> build(List<ProgramEventDto> eventDtos) {
        List<Event> events = new ArrayList<>();

        eventDtos.forEach(eventDto -> {
            Event event = new Event();

            events.add(event);
        });

        return events;
    }
}
