package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.events.Event;
import ca.ulaval.glo4002.booking.domain.events.EventDate;
import ca.ulaval.glo4002.booking.dto.events.ProgramEventDto;
import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventFactory {

    public List<Event> build(List<ProgramEventDto> eventDtos) {
        List<Event> events = new ArrayList<>();

        eventDtos.forEach(eventDto -> {
            EventDate eventDate = buildEventDate(eventDto.getEventDate());
            Event event = new Event(eventDate);

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
