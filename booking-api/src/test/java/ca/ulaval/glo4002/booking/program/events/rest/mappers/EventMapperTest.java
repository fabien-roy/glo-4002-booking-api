package ca.ulaval.glo4002.booking.program.events.rest.mappers;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.program.activities.domain.Activities;
import ca.ulaval.glo4002.booking.program.artists.domain.Artist;
import ca.ulaval.glo4002.booking.program.events.domain.Event;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.program.rest.ProgramEventRequest;
import ca.ulaval.glo4002.booking.program.rest.exceptions.InvalidProgramException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EventMapperTest {

    private static final EventDate FIRST_EVENT_DATE = FestivalConfiguration.getDefaultStartEventDate();
    private static final EventDate SECOND_EVENT_DATE = FIRST_EVENT_DATE.plusDays(1);

    private EventMapper factory;
    private FestivalConfiguration festivalConfiguration;
    private EventDateMapper eventDateMapper;

    @BeforeEach
    void setUpFactory() {

        this.factory = new EventMapper(festivalConfiguration, eventDateMapper);
    }

    @BeforeEach
    void setUpConfiguration() {
        festivalConfiguration = mock(FestivalConfiguration.class);

        when(festivalConfiguration.getAllEventDates()).thenReturn(Arrays.asList(FIRST_EVENT_DATE, SECOND_EVENT_DATE));
    }

    @BeforeEach
    void setUpMapper() {
        eventDateMapper = mock(EventDateMapper.class);

        when(eventDateMapper.fromString(FIRST_EVENT_DATE.toString())).thenReturn(FIRST_EVENT_DATE);
        when(eventDateMapper.fromString(SECOND_EVENT_DATE.toString())).thenReturn(SECOND_EVENT_DATE);
    }

    @Test
    void fromString_shouldSetEventDates() {
        EventDate expectedEventDate = FIRST_EVENT_DATE;
        EventDate otherExpectedEventDate = SECOND_EVENT_DATE;
        List<String> activities = Arrays.asList(Activities.YOGA.toString(), Activities.YOGA.toString());
        List<String> artistNames = Arrays.asList("artistName", "anotherArtistName");
        List<String> eventDates = Arrays.asList(expectedEventDate.toString(), otherExpectedEventDate.toString());
        List<ProgramEventRequest> requests = buildRequests(activities, artistNames, eventDates);
        List<Artist> existingArtists = buildArtists(artistNames);

        List<Event> events = factory.fromRequests(requests, existingArtists);

        assertEquals(expectedEventDate, events.get(0).getEventDate());
        assertEquals(otherExpectedEventDate, events.get(1).getEventDate());
    }

    @Test
    void fromString_shouldSetActivities() {
        Activities expectedActivity = Activities.YOGA;
        Activities otherExpectedActivity = Activities.CARDIO;
        List<String> activities = Arrays.asList(expectedActivity.toString(), otherExpectedActivity.toString());
        List<String> artistNames = Arrays.asList("artistName", "anotherArtistName");
        List<String> eventDates = Arrays.asList(FIRST_EVENT_DATE.toString(), SECOND_EVENT_DATE.toString());
        List<ProgramEventRequest> requests = buildRequests(activities, artistNames, eventDates);
        List<Artist> existingArtists = buildArtists(artistNames);

        List<Event> events = factory.fromRequests(requests, existingArtists);

        assertEquals(expectedActivity, events.get(0).getActivity());
        assertEquals(otherExpectedActivity, events.get(1).getActivity());
    }

    @Test
    void fromString_shouldSetArtists_whenThereIsAreMultipleRequests() {
        List<String> activities = Arrays.asList(Activities.YOGA.toString(), Activities.YOGA.toString());
        List<String> artistNames = Arrays.asList("artistName", "anotherArtistName");
        List<String> eventDates = Arrays.asList(FIRST_EVENT_DATE.toString(), SECOND_EVENT_DATE.toString());
        List<ProgramEventRequest> requests = buildRequests(activities, artistNames, eventDates);
        Artist expectedArtist = mock(Artist.class);
        Artist otherExpectedArtist = mock(Artist.class);
        when(expectedArtist.getName()).thenReturn(artistNames.get(0));
        when(otherExpectedArtist.getName()).thenReturn(artistNames.get(1));
        List<Artist> existingArtists = Arrays.asList(expectedArtist, otherExpectedArtist);

        List<Event> events = factory.fromRequests(requests, existingArtists);

        assertEquals(expectedArtist, events.get(0).getArtist());
        assertEquals(otherExpectedArtist, events.get(1).getArtist());
    }

    @Test
    void fromRequests_shouldThrowInvalidProgramException_whenEventDateIsDuplicate() {
        EventDate eventDate = FIRST_EVENT_DATE;
        List<String> activities = Arrays.asList(Activities.YOGA.toString(), Activities.YOGA.toString());
        List<String> artistNames = Arrays.asList("artistName", "anotherArtistName");
        List<String> eventDates = Collections.nCopies(2, eventDate.toString());
        List<ProgramEventRequest> requests = buildRequests(activities, artistNames, eventDates);
        List<Artist> existingArtists = buildArtists(artistNames);

        assertThrows(InvalidProgramException.class, () -> factory.fromRequests(requests, existingArtists));
    }

    @Test
    void fromRequests_shouldThrowInvalidProgramException_whenEventDateIsAbsent() {
        List<String> activities = Arrays.asList(Activities.YOGA.toString(), Activities.YOGA.toString());
        List<String> artistNames = Arrays.asList("artistName", "anotherArtistName");
        List<String> eventDates = Arrays.asList(null, SECOND_EVENT_DATE.toString());
        List<ProgramEventRequest> requests = buildRequests(activities, artistNames, eventDates);
        List<Artist> existingArtists = buildArtists(artistNames);

        assertThrows(InvalidProgramException.class, () -> factory.fromRequests(requests, existingArtists));
    }

    @Test
    void create_shouldThrowInvalidProgramException_whenProgramDoesNotIncludeAllFestivalDates() {
        List<String> activities = Collections.singletonList(Activities.YOGA.toString());
        List<String> artistNames = Collections.singletonList("artistName");
        List<String> eventDates = Collections.singletonList(FIRST_EVENT_DATE.toString());
        List<ProgramEventRequest> requests = buildRequests(activities, artistNames, eventDates);
        List<Artist> existingArtists = buildArtists(artistNames);

        assertThrows(InvalidProgramException.class, () -> factory.fromRequests(requests, existingArtists));
    }

    @Test
    void create_shouldThrowInvalidProgramException_whenActivityIsInvalid() {
        List<String> activities = Arrays.asList("invalidActivity", Activities.YOGA.toString());
        List<String> artistNames = Arrays.asList("artistName", "anotherArtistName");
        List<String> eventDates = Arrays.asList(FIRST_EVENT_DATE.toString(), SECOND_EVENT_DATE.toString());
        List<ProgramEventRequest> requests = buildRequests(activities, artistNames, eventDates);
        List<Artist> existingArtists = buildArtists(artistNames);

        assertThrows(InvalidProgramException.class, () -> factory.fromRequests(requests, existingArtists));
    }

    @Test
    void create_shouldThrowInvalidProgramException_whenAmIsAbsent() {
        List<String> activities = Arrays.asList(null, Activities.YOGA.toString());
        List<String> artistNames = Arrays.asList("artistName", "anotherArtistName");
        List<String> eventDates = Arrays.asList(FIRST_EVENT_DATE.toString(), SECOND_EVENT_DATE.toString());
        List<ProgramEventRequest> requests = buildRequests(activities, artistNames, eventDates);
        List<Artist> existingArtists = buildArtists(artistNames);

        assertThrows(InvalidProgramException.class, () -> factory.fromRequests(requests, existingArtists));
    }

    @Test
    void create_shouldThrowInvalidProgramException_whenPmIsAbsent() {
        List<String> activities = Arrays.asList(Activities.YOGA.toString(), Activities.YOGA.toString());
        List<String> artistNames = Arrays.asList(null, "anotherArtistName");
        List<String> eventDates = Arrays.asList(FIRST_EVENT_DATE.toString(), SECOND_EVENT_DATE.toString());
        List<ProgramEventRequest> requests = buildRequests(activities, artistNames, eventDates);
        List<Artist> existingArtists = buildArtists(artistNames);

        assertThrows(InvalidProgramException.class, () -> factory.fromRequests(requests, existingArtists));
    }

    @Test
    void create_shouldThrowInvalidProgramException_whenArtistIsDuplicate() {
        List<String> activities = Arrays.asList(Activities.YOGA.toString(), Activities.YOGA.toString());
        List<String> artistNames = Collections.nCopies(2, "artistName");
        List<String> eventDates = Arrays.asList(FIRST_EVENT_DATE.toString(), SECOND_EVENT_DATE.toString());
        List<ProgramEventRequest> requests = buildRequests(activities, artistNames, eventDates);
        List<Artist> existingArtists = buildArtists(artistNames);

        assertThrows(InvalidProgramException.class, () -> factory.fromRequests(requests, existingArtists));
    }

    @Test
    void create_shouldThrowInvalidProgramException_whenArtistIsInvalid() {
        List<String> activities = Arrays.asList(Activities.YOGA.toString(), Activities.YOGA.toString());
        List<String> artistNames = Arrays.asList("invalidArtist", "anotherArtistName");
        List<String> eventDates = Arrays.asList(FIRST_EVENT_DATE.toString(), SECOND_EVENT_DATE.toString());
        List<ProgramEventRequest> requests = buildRequests(activities, artistNames, eventDates);
        List<String> existingArtistNames = Arrays.asList("artistName", "anotherArtistName");
        List<Artist> existingArtists = buildArtists(existingArtistNames);

        assertThrows(InvalidProgramException.class, () -> factory.fromRequests(requests, existingArtists));
    }

    private List<ProgramEventRequest> buildRequests(List<String> activities, List<String> artistNames, List<String> eventDates) {
        List<ProgramEventRequest> requests = new ArrayList<>();

        for (int i = 0; i < activities.size(); i++) {
            ProgramEventRequest eventRequest = mock(ProgramEventRequest.class);
            when(eventRequest.getAm()).thenReturn(activities.get(i));
            when(eventRequest.getPm()).thenReturn(artistNames.get(i));
            when(eventRequest.getEventDate()).thenReturn(eventDates.get(i));
            requests.add(eventRequest);
        }

        return requests;
    }

    private List<Artist> buildArtists(List<String> artistNames) {
        List<Artist> existingArtists = new ArrayList<>();

        for (String artistName : artistNames) {
            Artist artist = mock(Artist.class);
            when(artist.getName()).thenReturn(artistName);
            existingArtists.add(artist);
        }

        return existingArtists;
    }
}

