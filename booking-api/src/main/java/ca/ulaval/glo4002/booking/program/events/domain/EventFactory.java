package ca.ulaval.glo4002.booking.program.events.domain;

import ca.ulaval.glo4002.booking.festival.domain.Festival;
import ca.ulaval.glo4002.booking.program.activities.domain.Activities;
import ca.ulaval.glo4002.booking.program.artists.services.ArtistService;
import ca.ulaval.glo4002.booking.program.artists.domain.BookingArtist;
import ca.ulaval.glo4002.booking.program.rest.exceptions.InvalidProgramException;
import ca.ulaval.glo4002.booking.program.rest.ProgramEventDto;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EventFactory {

    private final Festival festival;
    private final ArtistService artistService;
    private final EventDateFactory eventDateFactory;

    @Inject
    public EventFactory(Festival festival, ArtistService artistService, EventDateFactory eventDateFactory) {
        this.festival = festival;
        this.artistService = artistService;
        this.eventDateFactory = eventDateFactory;
    }

    public List<Event> build(List<ProgramEventDto> eventDtos) {
        List<Event> events = new ArrayList<>();

        validateEventDates(eventDtos);
        validateArtists(eventDtos);

        eventDtos.forEach(eventDto -> {
            EventDate eventDate = eventDateFactory.build(eventDto.getEventDate());
            Activities activity = Activities.get(eventDto.getAm());
            BookingArtist bookingArtist = artistService.getByName(eventDto.getPm());

            Event event = new Event(eventDate, activity, bookingArtist);

            events.add(event);
        });

        return events;
    }

    private void validateEventDates(List<ProgramEventDto> eventDtos) {
        List<String> eventDates = eventDtos.stream().map(ProgramEventDto::getEventDate).collect(Collectors.toList());

        validateAllDifferent(eventDates);
        validateAllPresent(eventDates);
    }

    private void validateArtists(List<ProgramEventDto> eventDtos) {
        boolean hasNull = eventDtos.stream().anyMatch(eventDto -> eventDto.getPm() == null);

        if (hasNull) throw new InvalidProgramException();

        List<String> eventDates = eventDtos.stream().map(ProgramEventDto::getPm).collect(Collectors.toList());

        validateAllDifferent(eventDates);
    }

    private void validateAllDifferent(List<String> elements) {
        boolean hasDuplicates = elements.stream().anyMatch(element -> Collections.frequency(elements, element) > 1);

        if (hasDuplicates) throw new InvalidProgramException();
    }

    private void validateAllPresent(List<String> eventDates) {
        List<String> festivalEventDates = festival.getAllEventDates().stream().map(EventDate::toString).collect(Collectors.toList());

        boolean hasAllFestivalEventDates = eventDates.containsAll(festivalEventDates);

        if (!hasAllFestivalEventDates) throw new InvalidProgramException();
    }
}
