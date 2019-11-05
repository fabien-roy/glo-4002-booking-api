package ca.ulaval.glo4002.booking.factories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventFactoryTest {

    private EventFactory eventFactory;

    @BeforeEach
    void setUpFactory() {
        this.eventFactory = new EventFactory();
    }

    @Test
    void build_shouldBuildCorrectAmountOfEvents() {
        // TODO
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