package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.events.Event;
import ca.ulaval.glo4002.booking.domain.events.EventDate;
import ca.ulaval.glo4002.booking.dto.events.ProgramEventDto;
import ca.ulaval.glo4002.booking.enums.Activities;
import ca.ulaval.glo4002.booking.exceptions.InvalidProgramException;
import ca.ulaval.glo4002.organisation.domain.Artist;
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
    private Artist aArtist;
    private Artist anotherArtist;

    @BeforeEach
    void setUpFactory() {
        artistRepository = mock(ArtistRepository.class);
        ArtistFactory artistFactory = new ArtistFactory();

        this.eventFactory = new EventFactory(artistRepository, artistFactory);
    }

    @Test
    void build_shouldBuildCorrectAmountOfEvents_whenThereIsOneEvent() {
        setUpSingleArtist();
        ProgramEventDto aEventDto = buildEventDto(new EventDate(EventDate.START_DATE), Activities.YOGA, aArtist.getName());

        List<Event> events = eventFactory.build(Collections.singletonList(aEventDto));

        assertEquals(1, events.size());
    }

    @Test
    void build_shouldBuildCorrectAmountOfEvents_whenThereAreMultipleEvents() {
        setUpMultipleArtists();
        ProgramEventDto aEventDto = buildEventDto(new EventDate(EventDate.START_DATE), Activities.YOGA, aArtist.getName());
        ProgramEventDto anotherEventDto = buildEventDto(new EventDate(EventDate.START_DATE.plusDays(1)), Activities.YOGA, anotherArtist.getName());

        List<Event> events = eventFactory.build(Arrays.asList(aEventDto, anotherEventDto));

        assertEquals(2, events.size());
    }

    @Test
    void build_shouldBuildCorrectEventDate() {
        setUpSingleArtist();
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        ProgramEventDto aEventDto = buildEventDto(aEventDate, Activities.YOGA, aArtist.getName());

        List<Event> events = eventFactory.build(Collections.singletonList(aEventDto));

        assertEquals(aEventDate, events.get(0).getEventDate());
    }

    @Test
    void build_shouldBuildCorrectEventDates_whenThereAreMultipleEvents() {
        setUpMultipleArtists();
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        EventDate anotherEventDate = new EventDate(EventDate.START_DATE.plusDays(1));
        ProgramEventDto aEventDto = buildEventDto(aEventDate, Activities.YOGA, aArtist.getName());
        ProgramEventDto anotherEventDto = buildEventDto(anotherEventDate, Activities.YOGA, anotherArtist.getName());

        List<Event> events = eventFactory.build(Arrays.asList(aEventDto, anotherEventDto));

        assertTrue(events.stream().anyMatch(event -> event.getEventDate().equals(aEventDate)));
        assertTrue(events.stream().anyMatch(event -> event.getEventDate().equals(anotherEventDate)));
    }

    @ParameterizedTest
    @EnumSource(Activities.class)
    void build_shouldBuildCorrectActivity(Activities activity) {
        setUpSingleArtist();
        ProgramEventDto aEventDto = buildEventDto(new EventDate(EventDate.START_DATE), activity, aArtist.getName());

        List<Event> events = eventFactory.build(Collections.singletonList(aEventDto));

        assertEquals(activity, events.get(0).getActivity());
    }

    @Test
    void build_shouldBuildCorrectActivities_whenThereAreMultipleEvents() {
        setUpMultipleArtists();
        Activities aActivity = Activities.YOGA;
        Activities anotherActivity = Activities.CARDIO;
        ProgramEventDto aEventDto = buildEventDto(new EventDate(EventDate.START_DATE), aActivity, aArtist.getName());
        ProgramEventDto anotherEventDto = buildEventDto(new EventDate(EventDate.START_DATE.plusDays(1)), anotherActivity, anotherArtist.getName());

        List<Event> events = eventFactory.build(Arrays.asList(aEventDto, anotherEventDto));

        assertTrue(events.stream().anyMatch(event -> event.getActivity().equals(aActivity)));
        assertTrue(events.stream().anyMatch(event -> event.getActivity().equals(anotherActivity)));
    }

    @Test
    void build_shouldBuildCorrectArtist() {
        setUpSingleArtist();
        ProgramEventDto aEventDto = buildEventDto(new EventDate(EventDate.START_DATE), Activities.YOGA, aArtist.getName());

        List<Event> events = eventFactory.build(Collections.singletonList(aEventDto));

        assertEquals(aArtist.getName(), events.get(0).getArtist().getName());
    }

    @Test
    void build_shouldBuildCorrectArtists_whenThereAreMultipleEvents() {
        setUpMultipleArtists();
        ProgramEventDto aEventDto = buildEventDto(new EventDate(EventDate.START_DATE), Activities.YOGA, aArtist.getName());
        ProgramEventDto anotherEventDto = buildEventDto(new EventDate(EventDate.START_DATE.plusDays(1)), Activities.YOGA, anotherArtist.getName());

        List<Event> events = eventFactory.build(Arrays.asList(aEventDto, anotherEventDto));

        assertTrue(events.stream().anyMatch(event -> event.getArtist().getName().equals(aArtist.getName())));
        assertTrue(events.stream().anyMatch(event -> event.getArtist().getName().equals(anotherArtist.getName())));
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenEventDateIsInvalid() {
        setUpSingleArtist();
        String anInvalidDate = "anInvalidDate";
        ProgramEventDto aEventDto = new ProgramEventDto(anInvalidDate, Activities.YOGA.toString(), aArtist.getName());

        assertThrows(InvalidProgramException.class, () -> eventFactory.build(Collections.singletonList(aEventDto)));
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenEventDateIsUnderBounds() {
        setUpSingleArtist();
        String anUnderBoundsEventDate = EventDate.START_DATE.minusDays(1).toString();
        ProgramEventDto aEventDto = new ProgramEventDto(anUnderBoundsEventDate, Activities.YOGA.toString(), aArtist.getName());

        assertThrows(InvalidProgramException.class, () -> eventFactory.build(Collections.singletonList(aEventDto)));
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenEventDateIsOverBounds() {
        setUpSingleArtist();
        String anOverBoundsEventDate = EventDate.END_DATE.plusDays(1).toString();
        ProgramEventDto aEventDto = new ProgramEventDto(anOverBoundsEventDate, Activities.YOGA.toString(), aArtist.getName());

        assertThrows(InvalidProgramException.class, () -> eventFactory.build(Collections.singletonList(aEventDto)));
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenEventDateIsDuplicate() {
        setUpMultipleArtists();
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        ProgramEventDto aEventDto = buildEventDto(aEventDate, Activities.YOGA, aArtist.getName());
        ProgramEventDto anotherEventDto = buildEventDto(aEventDate, Activities.YOGA, anotherArtist.getName());

        assertThrows(InvalidProgramException.class, () -> eventFactory.build(Arrays.asList(aEventDto, anotherEventDto)));
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenEventDateIsAbsent() {
        setUpSingleArtist();
        ProgramEventDto aEventDto = new ProgramEventDto(null, Activities.YOGA.toString(), aArtist.getName());

        assertThrows(InvalidProgramException.class, () -> eventFactory.build(Collections.singletonList(aEventDto)));
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenActivityIsInvalid() {
        setUpSingleArtist();
        String anInvalidActivity = "anInvalidActivity";
        ProgramEventDto aEventDto = new ProgramEventDto(EventDate.START_DATE.toString(), anInvalidActivity, aArtist.getName());

        assertThrows(InvalidProgramException.class, () -> eventFactory.build(Collections.singletonList(aEventDto)));
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenArtistIsInvalid() {
        setUpSingleArtist();
        String anInvalidArtistName = "anInvalidName";
        ProgramEventDto aEventDto = new ProgramEventDto(EventDate.START_DATE.toString(), Activities.YOGA.toString(), anInvalidArtistName);

        assertThrows(InvalidProgramException.class, () -> eventFactory.build(Collections.singletonList(aEventDto)));
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenAmIsAbsent() {
        setUpSingleArtist();
        ProgramEventDto aEventDto = new ProgramEventDto(EventDate.START_DATE.toString(), null, aArtist.getName());

        assertThrows(InvalidProgramException.class, () -> eventFactory.build(Collections.singletonList(aEventDto)));
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenPmIsAbsent() {
        setUpSingleArtist();
        ProgramEventDto aEventDto = new ProgramEventDto(EventDate.START_DATE.toString(), Activities.YOGA.toString(), null);

        assertThrows(InvalidProgramException.class, () -> eventFactory.build(Collections.singletonList(aEventDto)));
    }

    @Test
    void build_shouldThrowInvalidProgramException_whenArtistIsDuplicate() {
        setUpSingleArtist();
        ProgramEventDto aEventDto = buildEventDto(new EventDate(EventDate.START_DATE), Activities.YOGA, aArtist.getName());
        ProgramEventDto anotherEventDto = buildEventDto(new EventDate(EventDate.START_DATE), Activities.YOGA, aArtist.getName());

        assertThrows(InvalidProgramException.class, () -> eventFactory.build(Arrays.asList(aEventDto, anotherEventDto)));
    }

    private ProgramEventDto buildEventDto(EventDate eventDate, Activities activity, String artist) {
        return new ProgramEventDto(eventDate.toString(), activity.toString(), artist);
    }

    private void setUpSingleArtist() {
        aArtist = mock(Artist.class);
        when(aArtist.getName()).thenReturn("aArtist");
        doReturn(Collections.singletonList(aArtist)).when(artistRepository).findAll();
    }

    private void setUpMultipleArtists() {
        aArtist = mock(Artist.class);
        when(aArtist.getName()).thenReturn("aArtist");
        anotherArtist = mock(Artist.class);
        when(anotherArtist.getName()).thenReturn("anotherArtist");
        doReturn(Arrays.asList(aArtist, anotherArtist)).when(artistRepository).findAll();
    }
}