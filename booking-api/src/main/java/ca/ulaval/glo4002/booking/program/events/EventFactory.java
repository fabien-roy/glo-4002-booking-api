package ca.ulaval.glo4002.booking.program.events;

import ca.ulaval.glo4002.booking.program.activities.Activities;
import ca.ulaval.glo4002.booking.program.artists.ArtistService;
import ca.ulaval.glo4002.booking.program.artists.BookingArtist;
import ca.ulaval.glo4002.booking.configuration.Configuration;
import ca.ulaval.glo4002.booking.program.InvalidProgramException;
import ca.ulaval.glo4002.booking.program.ProgramEventDto;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EventFactory {

    private final Configuration configuration;
    private final ArtistService artistService;
    private final EventDateFactory eventDateFactory;

    @Inject
    public EventFactory(Configuration configuration, ArtistService artistService, EventDateFactory eventDateFactory) {
        this.configuration = configuration;
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
        List<String> festivalEventDates = configuration.getAllEventDates().stream().map(EventDate::toString).collect(Collectors.toList());

        boolean hasAllFestivalEventDates = eventDates.containsAll(festivalEventDates);

        if (!hasAllFestivalEventDates) throw new InvalidProgramException();
    }
}
