package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.BookingArtist;
import ca.ulaval.glo4002.booking.domain.events.Event;
import ca.ulaval.glo4002.booking.domain.events.EventDate;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.dto.events.ProgramEventDto;
import ca.ulaval.glo4002.booking.enums.Activities;
import ca.ulaval.glo4002.booking.exceptions.InvalidProgramException;
import ca.ulaval.glo4002.organisation.repositories.ArtistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventFactoryTest {

    private EventFactory eventFactory;
    private ArtistRepository artistRepository;

    @BeforeEach
    void setUpFactory() {
        artistRepository = mock(ArtistRepository.class);

        this.eventFactory = new EventFactory(artistRepository);
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
        String aArtistName = "aArtist";
        BookingArtist aArtist = new BookingArtist(1, aArtistName, mock(Money.class), 1, "aMusicStyle", 1);
        doReturn(Collections.singletonList(aArtist)).when(artistRepository).findAll();
        ProgramEventDto aEventDto = buildEventDto(new EventDate(EventDate.START_DATE), Activities.YOGA, aArtistName);

        List<Event> events = eventFactory.build(Collections.singletonList(aEventDto));

        assertEquals(aArtistName, events.get(0).getArtistName());
    }

    @Test
    void build_shouldBuildCorrectArtists_whenThereAreMultipleEvents() {
        String aArtistName = "aArtist";
        String anotherArtistName = "anotherArtist";
        BookingArtist aArtist = new BookingArtist(1, aArtistName, mock(Money.class), 1, "aMusicStyle", 1);
        BookingArtist anotherArtist = new BookingArtist(2, anotherArtistName, mock(Money.class), 1, "aMusicStyle", 2);
        doReturn(Arrays.asList(aArtist, anotherArtist)).when(artistRepository).findAll();
        ProgramEventDto aEventDto = buildEventDto(new EventDate(EventDate.START_DATE), Activities.YOGA, aArtistName);
        ProgramEventDto anotherEventDto = buildEventDto(new EventDate(EventDate.START_DATE.plusDays(1)), Activities.YOGA, anotherArtistName);

        List<Event> events = eventFactory.build(Arrays.asList(aEventDto, anotherEventDto));

        assertTrue(events.stream().anyMatch(event -> event.getArtistName().equals(aArtistName)));
        assertTrue(events.stream().anyMatch(event -> event.getArtistName().equals(anotherArtistName)));
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
        ProgramEventDto aEventDto = new ProgramEventDto(null, Activities.YOGA.toString(), "aArtist");

        assertThrows(InvalidProgramException.class, () -> eventFactory.build(Collections.singletonList(aEventDto)));
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenActivityIsInvalid() {
        String anInvalidActivity = "anInvalidActivity";
        ProgramEventDto aEventDto = new ProgramEventDto(EventDate.START_DATE.toString(), anInvalidActivity, "aArtist");

        assertThrows(InvalidProgramException.class, () -> eventFactory.build(Collections.singletonList(aEventDto)));
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenArtistIsInvalid() {
        String anInvalidArtistName = "anInvalidName";
        BookingArtist aArtist = new BookingArtist(1, "aArtist", mock(Money.class), 1, "aMusicStyle", 1);
        doReturn(Collections.singletonList(aArtist)).when(artistRepository).findAll();
        ProgramEventDto aEventDto = new ProgramEventDto(EventDate.START_DATE.toString(), Activities.YOGA.toString(), anInvalidArtistName);

        assertThrows(InvalidProgramException.class, () -> eventFactory.build(Collections.singletonList(aEventDto)));
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenAmIsAbsent() {
        ProgramEventDto aEventDto = new ProgramEventDto(EventDate.START_DATE.toString(), null, "aArtist");

        assertThrows(InvalidProgramException.class, () -> eventFactory.build(Collections.singletonList(aEventDto)));
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenPmIsAbsent() {
        ProgramEventDto aEventDto = new ProgramEventDto(EventDate.START_DATE.toString(), Activities.YOGA.toString(), null);

        assertThrows(InvalidProgramException.class, () -> eventFactory.build(Collections.singletonList(aEventDto)));
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