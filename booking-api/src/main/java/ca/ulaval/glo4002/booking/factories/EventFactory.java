package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.events.Event;
import ca.ulaval.glo4002.booking.domain.events.EventDate;
import ca.ulaval.glo4002.booking.dto.events.ProgramEventDto;
import ca.ulaval.glo4002.booking.enums.Activities;
import ca.ulaval.glo4002.booking.exceptions.InvalidProgramException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EventFactory {

    public List<Event> build(List<ProgramEventDto> eventDtos) {
        List<Event> events = new ArrayList<>();

        validateEventDates(eventDtos);
        validateArtists(eventDtos);

        eventDtos.forEach(eventDto -> {
            EventDate eventDate = buildEventDate(eventDto.getEventDate());
            Activities activity = Activities.get(eventDto.getAm());

            Event event = new Event(eventDate, activity, eventDto.getPm());

            events.add(event);
        });

        return events;
    }

    private void validateEventDates(List<ProgramEventDto> eventDtos) {
        List<String> eventDates = eventDtos.stream().map(ProgramEventDto::getEventDate).collect(Collectors.toList());
        validateAllDifferent(eventDates);
    }

    private void validateArtists(List<ProgramEventDto> eventDtos) {
        List<String> eventDates = eventDtos.stream().map(ProgramEventDto::getPm).collect(Collectors.toList());
        validateAllDifferent(eventDates);
    }

    private void validateAllDifferent(List<String> elements) {
        boolean hasDuplicates = elements.stream().anyMatch(element -> Collections.frequency(elements, element) > 1);

        if (hasDuplicates) throw new InvalidProgramException();
    }

    private EventDate buildEventDate(String eventDate) {
        EventDate parsedEventDate;

        try {
            LocalDate localDate = LocalDate.parse(eventDate);
            parsedEventDate = new EventDate(localDate);
        } catch (Exception exception) {
            throw new InvalidProgramException();
        }

        return parsedEventDate;
    }
}
