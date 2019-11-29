package ca.ulaval.glo4002.booking.program.events;

import ca.ulaval.glo4002.booking.festival.Festival;
import ca.ulaval.glo4002.booking.program.activities.Activities;
import ca.ulaval.glo4002.booking.program.artists.ArtistService;
import ca.ulaval.glo4002.booking.program.artists.BookingArtist;
import ca.ulaval.glo4002.booking.program.InvalidProgramException;
import ca.ulaval.glo4002.booking.program.ProgramEventDto;
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

class EventFactoryTest {

    private EventFactory factory;
    private Festival festival;
    private ArtistService artistService;
    private EventDateFactory eventDateFactory;

    @BeforeEach
    void setUpFactory() {
        artistService = mock(ArtistService.class);
        eventDateFactory = mock(EventDateFactory.class);

        this.factory = new EventFactory(festival, artistService, eventDateFactory);
    }

    @BeforeEach
    void setUpConfiguration() {
        festival = mock(Festival.class);

        when(festival.getAllEventDates()).thenReturn(Arrays.asList(
                EventDate.getDefaultStartEventDate(),
                EventDate.getDefaultEndEventDate())
        );
    }

    @Test
    void build_shouldBuildCorrectAmountOfEvents() {
        List<ProgramEventDto> aProgramDto = buildProgramDto(Activities.YOGA, "aArtist");
        Integer expectedSize = aProgramDto.size();

        List<Event> events = factory.build(aProgramDto);

        assertEquals(expectedSize, events.size());
    }

    @Test
    void build_shouldBuildCorrectEventDates() {
        List<EventDate> expectedEventDates = festival.getAllEventDates();
        List<ProgramEventDto> aProgramDto = buildProgramDto(Activities.YOGA, "aArtist");

        List<Event> events = factory.build(aProgramDto);

        assertTrue(events.stream().allMatch(event -> expectedEventDates.contains(event.getEventDate())));
    }

    @ParameterizedTest
    @EnumSource(Activities.class)
    void build_shouldBuildCorrectActivity(Activities activity) {
        List<ProgramEventDto> aProgramDto = buildProgramDto(activity, "aArtist");

        List<Event> events = factory.build(aProgramDto);

        assertEquals(activity, events.get(0).getActivity());
    }

    @Test
    void build_shouldBuildCorrectActivities_whenThereAreMultipleEvents() {
        Activities firstEventActivity = Activities.YOGA;
        Activities otherEventsActivity = Activities.CARDIO;
        List<ProgramEventDto> aProgramDto = buildProgramDto(otherEventsActivity, "aArtist");
        aProgramDto.set(0, buildEventDto(EventDate.getDefaultStartEventDate(), firstEventActivity, "aArtist"));

        List<Event> events = factory.build(aProgramDto);

        assertEquals(firstEventActivity, events.get(0).getActivity());
        assertTrue(events.subList(1, events.size() - 1).stream().allMatch(event -> event.getActivity().equals(otherEventsActivity)));
    }

    @Test
    void build_shouldBuildCorrectArtist() {
        String artistName = "aArtist";
        List<ProgramEventDto> aProgramDto = buildProgramDto(Activities.YOGA, artistName);
        BookingArtist expectedArtist = mock(BookingArtist.class);
        when(artistService.getByName(artistName + 0)).thenReturn(expectedArtist);

        List<Event> events = factory.build(aProgramDto);

        assertEquals(expectedArtist, events.get(0).getArtist());
    }

    @Test
    void build_shouldBuildCorrectArtists_whenThereAreMultipleEvents() {
        String firstArtistName = "aFirstArtist";
        List<ProgramEventDto> aProgramDto = buildProgramDto(Activities.YOGA, firstArtistName);
        BookingArtist expectedFirstArtist = mock(BookingArtist.class);
        BookingArtist expectedOtherArtist = mock(BookingArtist.class);
        when(artistService.getByName(firstArtistName)).thenReturn(expectedFirstArtist);
        when(artistService.getByName(not(eq(firstArtistName)))).thenReturn(expectedOtherArtist);
        aProgramDto.set(0, buildEventDto(EventDate.getDefaultStartEventDate(), Activities.YOGA, firstArtistName));

        List<Event> events = factory.build(aProgramDto);

        assertEquals(expectedFirstArtist, events.get(0).getArtist());
        assertTrue(events.subList(1, events.size() - 1).stream().allMatch(event -> event.getArtist().equals(expectedOtherArtist)));
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenEventDateIsDuplicate() {
        EventDate aEventDate = EventDate.getDefaultStartEventDate();
        List<ProgramEventDto> aProgramDto = buildProgramDto(Activities.YOGA, "aArtist");
        aProgramDto.set(0, new ProgramEventDto(aEventDate.toString(), Activities.YOGA.toString(), "aArtist"));
        aProgramDto.set(1, new ProgramEventDto(aEventDate.toString(), Activities.YOGA.toString(), "anotherArtist"));

        assertThrows(InvalidProgramException.class, () -> factory.build(aProgramDto));
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenEventDateIsAbsent() {
        List<ProgramEventDto> aProgramDto = buildProgramDto(Activities.YOGA, "aArtist");
        aProgramDto.set(0, new ProgramEventDto(null, Activities.YOGA.toString(), "aArtist"));

        assertThrows(InvalidProgramException.class, () -> factory.build(aProgramDto));
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenProgramDoesNotIncludeAllFestivalDates() {
        ProgramEventDto aEventDto = new ProgramEventDto(EventDate.getDefaultStartEventDate().toString(), Activities.YOGA.toString(), "aArtist");

        assertThrows(InvalidProgramException.class, () -> factory.build(Collections.singletonList(aEventDto)));
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenActivityIsInvalid() {
        String anInvalidActivity = "anInvalidActivity";
        List<ProgramEventDto> aProgramDto = buildProgramDto(Activities.YOGA, "aArtist");
        aProgramDto.set(0, new ProgramEventDto(null, anInvalidActivity, "aArtist"));

        assertThrows(InvalidProgramException.class, () -> factory.build(aProgramDto));
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenAmIsAbsent() {
        List<ProgramEventDto> aProgramDto = buildProgramDto(Activities.YOGA, "aArtist");
        aProgramDto.set(0, new ProgramEventDto(EventDate.getDefaultStartEventDate().toString(), null, "aArtist"));

        assertThrows(InvalidProgramException.class, () -> factory.build(aProgramDto));
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenPmIsAbsent() {
        List<ProgramEventDto> aProgramDto = buildProgramDto(Activities.YOGA, "aArtist");
        aProgramDto.set(0, new ProgramEventDto(EventDate.getDefaultStartEventDate().toString(), Activities.YOGA.toString(), null));

        assertThrows(InvalidProgramException.class, () -> factory.build(aProgramDto));
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenArtistIsDuplicate() {
        String aArtist = "aArtist";
        List<ProgramEventDto> aProgramDto = buildProgramDto(Activities.YOGA, "aArtist");
        aProgramDto.set(0, new ProgramEventDto(EventDate.getDefaultStartEventDate().toString(), Activities.YOGA.toString(), aArtist));
        aProgramDto.set(1, new ProgramEventDto(EventDate.getDefaultStartEventDate().plusDays(1).toString(), Activities.YOGA.toString(), aArtist));

        assertThrows(InvalidProgramException.class, () -> factory.build(aProgramDto));
    }

    private List<ProgramEventDto> buildProgramDto(Activities activity, String artist) {
        List<ProgramEventDto> eventDtos = new ArrayList<>();
        List<EventDate> eventDates = festival.getAllEventDates();

        for (int i = 0; i < eventDates.size(); i++) {
            eventDtos.add(buildEventDto(eventDates.get(i), activity, artist + i));
        }

        return eventDtos;
    }

    private ProgramEventDto buildEventDto(EventDate eventDate, Activities activity, String artist) {
        when(eventDateFactory.build(eq(eventDate.toString()))).thenReturn(eventDate);

        return new ProgramEventDto(eventDate.toString(), activity.toString(), artist);
    }
}

