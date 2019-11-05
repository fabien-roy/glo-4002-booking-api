package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.events.Event;
import ca.ulaval.glo4002.booking.domain.events.EventDate;
import ca.ulaval.glo4002.booking.dto.events.ProgramEventDto;
import ca.ulaval.glo4002.booking.enums.Activities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventFactoryTest {

    private EventFactory eventFactory;

    @BeforeEach
    void setUpFactory() {
        this.eventFactory = new EventFactory();
    }

    @Test
    void build_shouldBuildCorrectAmountOfEvents_whenThereIsOneEvent() {
        String aEventDate = EventDate.START_DATE.toString();
        String aActivity = Activities.YOGA.toString();
        String aArtist = "aArtist";
        ProgramEventDto aEventDto = new ProgramEventDto(aEventDate, aActivity, aArtist);

        List<Event> events = eventFactory.build(Collections.singletonList(aEventDto));

        assertEquals(1, events.size());
    }

    @Test
    void build_shouldBuildCorrectAmountOfEvents_whenThereAreMultipleEvents() {
        String aEventDate = EventDate.START_DATE.toString();
        String anotherEventDate = EventDate.START_DATE.plusDays(1).toString();
        String aActivity = Activities.YOGA.toString();
        String aArtist = "aArtist";
        String anotherArtist = "aArtist";
        ProgramEventDto aEventDto = new ProgramEventDto(aEventDate, aActivity, aArtist);
        ProgramEventDto anotherEventDto = new ProgramEventDto(anotherEventDate, aActivity, anotherArtist);

        List<Event> events = eventFactory.build(Arrays.asList(aEventDto, anotherEventDto));

        assertEquals(2, events.size());
    }

    @Test
    void build_shouldBuildCorrectEventDate() {
        // TODO
    }

    @Test
    void build_shouldBuildCorrectEventDates_whenThereAreMultipleEvents() {
        // TODO
    }

    @Test
    void build_shouldBuildCorrectActivity() {
        // TODO
    }

    @Test
    void build_shouldBuildCorrectActivities_whenThereAreMultipleEvents() {
        // TODO
    }

    @Test
    void build_shouldBuildCorrectArtist() {
        // TODO
    }

    @Test
    void build_shouldBuildCorrectArtists_whenThereAreMultipleEvents() {
        // TODO
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenEventDateIsDuplicate() {
        // TODO
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenEventDateIsAbsent() {
        // TODO
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
    void build_shouldThrowInvalidProgramException_whenPmIsActivity() {
        // TODO
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenArtistIsDuplicate() {
        // TODO
    }
}