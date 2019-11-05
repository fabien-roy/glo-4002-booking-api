package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.events.Event;
import ca.ulaval.glo4002.booking.domain.events.EventDate;
import ca.ulaval.glo4002.booking.dto.events.ProgramEventDto;
import ca.ulaval.glo4002.booking.enums.Activities;
import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventFactory {

    public List<Event> build(List<ProgramEventDto> eventDtos) {
        List<Event> events = new ArrayList<>();

        eventDtos.forEach(eventDto -> {
            EventDate eventDate = buildEventDate(eventDto.getEventDate());
            Activities activity = Activities.get(eventDto.getAm());

            Event event = new Event(eventDate, activity, eventDto.getPm());

            events.add(event);
        });

        return events;
    }

    private EventDate buildEventDate(String eventDate) {
        LocalDate parsedEventDate;

        try {
            parsedEventDate = LocalDate.parse(eventDate);
        } catch (Exception exception) {
            throw new InvalidFormatException();
        }

        return new EventDate(parsedEventDate);
    }
}
