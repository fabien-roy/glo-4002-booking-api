package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.events.Event;
import ca.ulaval.glo4002.booking.domain.events.EventDate;
import ca.ulaval.glo4002.booking.dto.events.ProgramEventDto;
import ca.ulaval.glo4002.booking.enums.Activities;
import ca.ulaval.glo4002.booking.exceptions.InvalidProgramException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventFactoryTest {

    private EventFactory eventFactory;

    @BeforeEach
    void setUpFactory() {
        this.eventFactory = new EventFactory();
    }

    @Test
    void build_shouldBuildCorrectAmountOfEvents_whenThereIsOneEvent() {
        ProgramEventDto aEventDto = buildEventDto(new EventDate(EventDate.START_DATE), Activities.YOGA, "aArtist");

        List<Event> events = eventFactory.build(Collections.singletonList(aEventDto));

        assertEquals(1, events.size());
    }

    @Test
    void build_shouldBuildCorrectAmountOfEvents_whenThereAreMultipleEvents() {
        ProgramEventDto aEventDto = buildEventDto(new EventDate(EventDate.START_DATE), Activities.YOGA, "aArtist");
        ProgramEventDto anotherEventDto = buildEventDto(new EventDate(EventDate.START_DATE.plusDays(1)), Activities.YOGA, "anotherArtist");

        List<Event> events = eventFactory.build(Arrays.asList(aEventDto, anotherEventDto));

        assertEquals(2, events.size());
    }

    @Test
    void build_shouldBuildCorrectEventDate() {
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        ProgramEventDto aEventDto = buildEventDto(aEventDate, Activities.YOGA, "aArtist");

        List<Event> events = eventFactory.build(Collections.singletonList(aEventDto));

        assertEquals(aEventDate, events.get(0).getEventDate());
    }

    @Test
    void build_shouldBuildCorrectEventDates_whenThereAreMultipleEvents() {
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        EventDate anotherEventDate = new EventDate(EventDate.START_DATE.plusDays(1));
        ProgramEventDto aEventDto = buildEventDto(aEventDate, Activities.YOGA, "aArtist");
        ProgramEventDto anotherEventDto = buildEventDto(anotherEventDate, Activities.YOGA, "anotherArtist");

        List<Event> events = eventFactory.build(Arrays.asList(aEventDto, anotherEventDto));

        assertTrue(events.stream().anyMatch(event -> event.getEventDate().equals(aEventDate)));
        assertTrue(events.stream().anyMatch(event -> event.getEventDate().equals(anotherEventDate)));
    }

    @ParameterizedTest
    @EnumSource(Activities.class)
    void build_shouldBuildCorrectActivity(Activities activity) {
        ProgramEventDto aEventDto = buildEventDto(new EventDate(EventDate.START_DATE), activity, "aArtist");

        List<Event> events = eventFactory.build(Collections.singletonList(aEventDto));

        assertEquals(activity, events.get(0).getActivity());
    }

    @Test
    void build_shouldBuildCorrectActivities_whenThereAreMultipleEvents() {
        Activities aActivity = Activities.YOGA;
        Activities anotherActivity = Activities.CARDIO;
        ProgramEventDto aEventDto = buildEventDto(new EventDate(EventDate.START_DATE), aActivity, "aArtist");
        ProgramEventDto anotherEventDto = buildEventDto(new EventDate(EventDate.START_DATE.plusDays(1)), anotherActivity, "anotherArtist");

        List<Event> events = eventFactory.build(Arrays.asList(aEventDto, anotherEventDto));

        assertTrue(events.stream().anyMatch(event -> event.getActivity().equals(aActivity)));
        assertTrue(events.stream().anyMatch(event -> event.getActivity().equals(anotherActivity)));
    }

    @Test
    void build_shouldBuildCorrectArtist() {
        String aArtist = "aArtist";
        ProgramEventDto aEventDto = buildEventDto(new EventDate(EventDate.START_DATE), Activities.YOGA, aArtist);

        List<Event> events = eventFactory.build(Collections.singletonList(aEventDto));

        assertEquals(aArtist, events.get(0).getArtist());
    }

    @Test
    void build_shouldBuildCorrectArtists_whenThereAreMultipleEvents() {
        String aArtist = "aArtist";
        String anotherArtist = "anotherArtist";
        ProgramEventDto aEventDto = buildEventDto(new EventDate(EventDate.START_DATE), Activities.YOGA, aArtist);
        ProgramEventDto anotherEventDto = buildEventDto(new EventDate(EventDate.START_DATE.plusDays(1)), Activities.YOGA, anotherArtist);

        List<Event> events = eventFactory.build(Arrays.asList(aEventDto, anotherEventDto));

        assertTrue(events.stream().anyMatch(event -> event.getArtist().equals(aArtist)));
        assertTrue(events.stream().anyMatch(event -> event.getArtist().equals(anotherArtist)));
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenEventDateIsInvalid() {
        String anInvalidDate = "anInvalidDate";
        ProgramEventDto aEventDto = new ProgramEventDto(anInvalidDate, Activities.YOGA.toString(), "aArtist");

        assertThrows(InvalidProgramException.class, () -> eventFactory.build(Collections.singletonList(aEventDto)));
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenEventDateIsUnderBounds() {
        String anUnderBoundsEventDate = EventDate.START_DATE.minusDays(1).toString();
        ProgramEventDto aEventDto = new ProgramEventDto(anUnderBoundsEventDate, Activities.YOGA.toString(), "aArtist");

        assertThrows(InvalidProgramException.class, () -> eventFactory.build(Collections.singletonList(aEventDto)));
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenEventDateIsOverBounds() {
        String anOverBoundsEventDate = EventDate.END_DATE.plusDays(1).toString();
        ProgramEventDto aEventDto = new ProgramEventDto(anOverBoundsEventDate, Activities.YOGA.toString(), "aArtist");

        assertThrows(InvalidProgramException.class, () -> eventFactory.build(Collections.singletonList(aEventDto)));
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenEventDateIsDuplicate() {
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        ProgramEventDto aEventDto = buildEventDto(aEventDate, Activities.YOGA, "aArtist");
        ProgramEventDto anotherEventDto = buildEventDto(aEventDate, Activities.YOGA, "anotherArtist");

        assertThrows(InvalidProgramException.class, () -> eventFactory.build(Arrays.asList(aEventDto, anotherEventDto)));
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenEventDateIsAbsent() {
        // TODO
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenActivityIsInvalid() {
        String anInvalidActivity = "anInvalidActivity";
        ProgramEventDto aEventDto = new ProgramEventDto(EventDate.START_DATE.toString(), anInvalidActivity, "aArtist");

        assertThrows(InvalidProgramException.class, () -> eventFactory.build(Collections.singletonList(aEventDto)));
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenPmIsDefinedTwice() {
        // TODO
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenAmIsDefinedTwice() {
        // TODO
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenAmIsArtist() {
        // TODO
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenAmIsAbsent() {
        // TODO
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenPmIsActivity() {
        // TODO
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenPmIsAbsent() {
        // TODO
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenArtistIsDuplicate() {
        String aArtist = "aArtist";
        ProgramEventDto aEventDto = buildEventDto(new EventDate(EventDate.START_DATE), Activities.YOGA, aArtist);
        ProgramEventDto anotherEventDto = buildEventDto(new EventDate(EventDate.START_DATE), Activities.YOGA, aArtist);

        assertThrows(InvalidProgramException.class, () -> eventFactory.build(Arrays.asList(aEventDto, anotherEventDto)));
    }

    private ProgramEventDto buildEventDto(EventDate eventDate, Activities activity, String artist) {
        return new ProgramEventDto(eventDate.toString(), activity.toString(), artist);
    }
}