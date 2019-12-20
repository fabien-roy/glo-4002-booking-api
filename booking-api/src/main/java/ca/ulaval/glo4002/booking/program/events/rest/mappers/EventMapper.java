package ca.ulaval.glo4002.booking.program.events.rest.mappers;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.program.activities.domain.Activities;
import ca.ulaval.glo4002.booking.program.artists.domain.Artist;
import ca.ulaval.glo4002.booking.program.artists.services.ArtistService;
import ca.ulaval.glo4002.booking.program.events.domain.Event;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.program.rest.ProgramEventRequest;
import ca.ulaval.glo4002.booking.program.rest.exceptions.InvalidProgramException;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EventMapper {

    private final FestivalConfiguration festivalConfiguration;
    private final ArtistService artistService; // TODO : Do not use ArtistService in EventFactory
    private final EventDateMapper eventDateMapper;

    @Inject
    public EventMapper(FestivalConfiguration festivalConfiguration, ArtistService artistService, EventDateMapper eventDateMapper) {
        this.festivalConfiguration = festivalConfiguration;
        this.artistService = artistService;
        this.eventDateMapper = eventDateMapper;
    }

    public List<Event> fromRequests(List<ProgramEventRequest> eventRequests) {
        List<Event> events = new ArrayList<>();

        validateEventDates(eventRequests);
        validateArtists(eventRequests);

        eventRequests.forEach(eventRequest -> {
            EventDate eventDate = eventDateMapper.fromString(eventRequest.getEventDate());
            Activities activity = Activities.get(eventRequest.getAm());
            Artist artist = artistService.getByName(eventRequest.getPm());

            Event event = new Event(eventDate, activity, artist);

            events.add(event);
        });

        return events;
    }

    private void validateEventDates(List<ProgramEventRequest> eventRequests) {
        List<String> eventDates = eventRequests.stream().map(ProgramEventRequest::getEventDate).collect(Collectors.toList());

        validateAllDifferent(eventDates);
        validateAllPresent(eventDates);
    }

    private void validateArtists(List<ProgramEventRequest> eventRequests) {
        boolean hasNull = eventRequests.stream().anyMatch(eventRequest -> eventRequest.getPm() == null);

        if (hasNull) throw new InvalidProgramException();

        List<String> eventDates = eventRequests.stream().map(ProgramEventRequest::getPm).collect(Collectors.toList());

        validateAllDifferent(eventDates);
    }

    private void validateAllDifferent(List<String> elements) {
        boolean hasDuplicates = elements.stream().anyMatch(element -> Collections.frequency(elements, element) > 1);

        if (hasDuplicates) throw new InvalidProgramException();
    }

    private void validateAllPresent(List<String> eventDates) {
        List<String> festivalEventDates = festivalConfiguration.getAllEventDates().stream().map(EventDate::toString).collect(Collectors.toList());

        boolean hasAllFestivalEventDates = eventDates.containsAll(festivalEventDates);

        if (!hasAllFestivalEventDates) throw new InvalidProgramException();
    }
}
