package ca.ulaval.glo4002.booking.program.events.rest.mappers;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.program.activities.domain.Activities;
import ca.ulaval.glo4002.booking.program.artists.domain.Artist;
import ca.ulaval.glo4002.booking.program.artists.services.ArtistService;
import ca.ulaval.glo4002.booking.program.events.domain.Event;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.program.events.rest.mappers.EventDateMapper;
import ca.ulaval.glo4002.booking.program.events.rest.mappers.EventMapper;
import ca.ulaval.glo4002.booking.program.rest.ProgramEventRequest;
import ca.ulaval.glo4002.booking.program.rest.exceptions.InvalidProgramException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EventMapperTest {

    private EventMapper factory;
    private FestivalConfiguration festivalConfiguration;
    private ArtistService artistService;
    private EventDateMapper eventDateMapper;

    @BeforeEach
    void setUpFactory() {
        artistService = mock(ArtistService.class);
        eventDateMapper = mock(EventDateMapper.class);

        this.factory = new EventMapper(festivalConfiguration, artistService, eventDateMapper);
    }

    @BeforeEach
    void setUpConfiguration() {
        festivalConfiguration = mock(FestivalConfiguration.class);

        when(festivalConfiguration.getAllEventDates()).thenReturn(Arrays.asList(
                FestivalConfiguration.getDefaultStartEventDate(),
                FestivalConfiguration.getDefaultEndEventDate())
        );
    }

    @Test
    void create_shouldCreateCorrectAmountOfEvents() {
        List<ProgramEventRequest> aProgramRequest = buildProgramRequest(Activities.YOGA, "aArtist");
        Integer expectedSize = aProgramRequest.size();

        List<Event> events = factory.fromRequests(aProgramRequest);

        assertEquals(expectedSize, events.size());
    }

    @Test
    void create_shouldCreateEventDates() {
        List<EventDate> expectedEventDates = festivalConfiguration.getAllEventDates();
        List<ProgramEventRequest> aProgramRequest = buildProgramRequest(Activities.YOGA, "aArtist");

        List<Event> events = factory.fromRequests(aProgramRequest);

        assertTrue(events.stream().allMatch(event -> expectedEventDates.contains(event.getEventDate())));
    }

    @ParameterizedTest
    @EnumSource(Activities.class)
    void create_shouldCreateActivity(Activities activity) {
        List<ProgramEventRequest> aProgramRequest = buildProgramRequest(activity, "aArtist");

        List<Event> events = factory.fromRequests(aProgramRequest);

        assertEquals(activity, events.get(0).getActivity());
    }

    @Test
    void create_shouldCreateActivities_whenThereAreMultipleEvents() {
        Activities firstEventActivity = Activities.YOGA;
        Activities otherEventsActivity = Activities.CARDIO;
        List<ProgramEventRequest> aProgramRequest = buildProgramRequest(otherEventsActivity, "aArtist");
        aProgramRequest.set(0, buildEventRequest(FestivalConfiguration.getDefaultStartEventDate(), firstEventActivity, "aArtist"));

        List<Event> events = factory.fromRequests(aProgramRequest);

        assertEquals(firstEventActivity, events.get(0).getActivity());
        assertTrue(events.subList(1, events.size() - 1).stream().allMatch(event -> event.getActivity().equals(otherEventsActivity)));
    }

    @Test
    void create_shouldCreateArtist() {
        String artistName = "aArtist";
        List<ProgramEventRequest> aProgramRequest = buildProgramRequest(Activities.YOGA, artistName);
        Artist expectedArtist = mock(Artist.class);
        String expectedArtistName = "aArtist0"; // Since we use numbers in the artist building for tests
        when(artistService.getByName(expectedArtistName)).thenReturn(expectedArtist);

        List<Event> events = factory.fromRequests(aProgramRequest);

        assertEquals(expectedArtist, events.get(0).getArtist());
    }

    @Test
    void create_shouldCreateArtists_whenThereAreMultipleEvents() {
        String firstArtistName = "aFirstArtist";
        List<ProgramEventRequest> aProgramRequest = buildProgramRequest(Activities.YOGA, firstArtistName);
        Artist expectedFirstArtist = mock(Artist.class);
        Artist expectedOtherArtist = mock(Artist.class);
        when(artistService.getByName(firstArtistName)).thenReturn(expectedFirstArtist);
        when(artistService.getByName(not(eq(firstArtistName)))).thenReturn(expectedOtherArtist);
        aProgramRequest.set(0, buildEventRequest(FestivalConfiguration.getDefaultStartEventDate(), Activities.YOGA, firstArtistName));

        List<Event> events = factory.fromRequests(aProgramRequest);

        assertEquals(expectedFirstArtist, events.get(0).getArtist());
        assertTrue(events.subList(1, events.size() - 1).stream().allMatch(event -> event.getArtist().equals(expectedOtherArtist)));
    }

    @Test
    void create_shouldThrowInvalidProgramException_whenEventDateIsDuplicate() {
        EventDate aEventDate = FestivalConfiguration.getDefaultStartEventDate();
        List<ProgramEventRequest> aProgramRequest = buildProgramRequest(Activities.YOGA, "aArtist");
        aProgramRequest.set(0, new ProgramEventRequest(aEventDate.toString(), Activities.YOGA.toString(), "aArtist"));
        aProgramRequest.set(1, new ProgramEventRequest(aEventDate.toString(), Activities.YOGA.toString(), "anotherArtist"));

        assertThrows(InvalidProgramException.class, () -> factory.fromRequests(aProgramRequest));
    }

    @Test
    void create_shouldThrowInvalidProgramException_whenEventDateIsAbsent() {
        List<ProgramEventRequest> aProgramRequest = buildProgramRequest(Activities.YOGA, "aArtist");
        aProgramRequest.set(0, new ProgramEventRequest(null, Activities.YOGA.toString(), "aArtist"));

        assertThrows(InvalidProgramException.class, () -> factory.fromRequests(aProgramRequest));
    }

    @Test
    void create_shouldThrowInvalidProgramException_whenProgramDoesNotIncludeAllFestivalDates() {
        ProgramEventRequest aEventRequest = new ProgramEventRequest(FestivalConfiguration.getDefaultStartEventDate().toString(), Activities.YOGA.toString(),"aArtist");

        assertThrows(InvalidProgramException.class, () -> factory.fromRequests(Collections.singletonList(aEventRequest)));
    }

    @Test
    void create_shouldThrowInvalidProgramException_whenActivityIsInvalid() {
        String anInvalidActivity = "anInvalidActivity";
        List<ProgramEventRequest> aProgramRequest = buildProgramRequest(Activities.YOGA, "aArtist");
        aProgramRequest.set(0, new ProgramEventRequest(null, anInvalidActivity, "aArtist"));

        assertThrows(InvalidProgramException.class, () -> factory.fromRequests(aProgramRequest));
    }

    @Test
    void create_shouldThrowInvalidProgramException_whenAmIsAbsent() {
        List<ProgramEventRequest> aProgramRequest = buildProgramRequest(Activities.YOGA, "aArtist");
        aProgramRequest.set(0, new ProgramEventRequest(FestivalConfiguration.getDefaultStartEventDate().toString(),null,"aArtist"));

        assertThrows(InvalidProgramException.class, () -> factory.fromRequests(aProgramRequest));
    }

    @Test
    void create_shouldThrowInvalidProgramException_whenPmIsAbsent() {
        List<ProgramEventRequest> aProgramRequest = buildProgramRequest(Activities.YOGA, "aArtist");
        aProgramRequest.set(0, new ProgramEventRequest(FestivalConfiguration.getDefaultStartEventDate().toString(), Activities.YOGA.toString(), null));

        assertThrows(InvalidProgramException.class, () -> factory.fromRequests(aProgramRequest));
    }

    @Test
    void create_shouldThrowInvalidProgramException_whenArtistIsDuplicate() {
        String aArtist = "aArtist";
        List<ProgramEventRequest> aProgramRequest = buildProgramRequest(Activities.YOGA, "aArtist");
        aProgramRequest.set(0, new ProgramEventRequest(FestivalConfiguration.getDefaultStartEventDate().toString(), Activities.YOGA.toString(), aArtist));
        aProgramRequest.set(1, new ProgramEventRequest(FestivalConfiguration.getDefaultStartEventDate().plusDays(1).toString(), Activities.YOGA.toString(), aArtist));

        assertThrows(InvalidProgramException.class, () -> factory.fromRequests(aProgramRequest));
    }

    private List<ProgramEventRequest> buildProgramRequest(Activities activity, String artist) {
        List<ProgramEventRequest> eventRequests = new ArrayList<>();
        List<EventDate> eventDates = festivalConfiguration.getAllEventDates();

        for (int i = 0; i < eventDates.size(); i++) {
            eventRequests.add(buildEventRequest(eventDates.get(i), activity, artist + i));
        }

        return eventRequests;
    }

    private ProgramEventRequest buildEventRequest(EventDate eventDate, Activities activity, String artist) {
        when(eventDateMapper.fromString(eq(eventDate.toString()))).thenReturn(eventDate);

        return new ProgramEventRequest(eventDate.toString(), activity.toString(), artist);
    }
}

