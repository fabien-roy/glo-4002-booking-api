package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.BookingArtist;
import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.events.Event;
import ca.ulaval.glo4002.booking.domain.events.EventDate;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.dto.events.ProgramDto;
import ca.ulaval.glo4002.booking.enums.Activities;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;
import ca.ulaval.glo4002.booking.factories.EventFactory;
import ca.ulaval.glo4002.booking.repositories.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProgramServiceTest {

    private ProgramService programService;
    private EventRepository eventRepository;
    private EventFactory eventFactory;
    private TripService tripService;

    @BeforeEach
    void setUpService() {
        eventRepository = mock(EventRepository.class);
        eventFactory = mock(EventFactory.class);
        tripService = mock(TripService.class);
        OxygenTankInventoryService oxygenTankInventoryService = mock(OxygenTankInventoryService.class);

        programService = new ProgramService(eventRepository, eventFactory, tripService, oxygenTankInventoryService);
    }

    @Test
    void add_shouldAddProgram() {
        ProgramDto aProgramDto = mock(ProgramDto.class);

        programService.add(aProgramDto);

        verify(eventRepository).addAll(any());
    }

    @Test
    void add_shouldOrderTripForArtistAtCorrectDate_whenThereIsASingleMember() {
        Integer memberAmount = 1;
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        BookingArtist aArtist = new BookingArtist(new Number(1L), "aArtist", mock(Money.class), memberAmount, "aMusicStyle", 1);
        Event aEvent = new Event(aEventDate, Activities.YOGA, aArtist);
        doReturn(Collections.singletonList(aEvent)).when(eventFactory).build(any());

        programService.add(mock(ProgramDto.class));

        verify(tripService).order(any(), eq(aEventDate), any());
    }

    @Test
    void add_shouldOrderEtSpaceshipTripForArtist_whenThereIsASingleMember() {
        Integer memberAmount = 1;
        BookingArtist aArtist = new BookingArtist(new Number(1L), "aArtist", mock(Money.class), memberAmount, "aMusicStyle", 1);
        Event aEvent = new Event(new EventDate(EventDate.START_DATE), Activities.YOGA, aArtist);
        doReturn(Collections.singletonList(aEvent)).when(eventFactory).build(any());

        programService.add(mock(ProgramDto.class));

        verify(tripService).order(eq(ShuttleCategories.ET_SPACESHIP), any(), any());
    }

    @Test
    void add_shouldOrderMillenniumFalconTripForArtist_whenThereAreMultipleMembers() {
        Integer memberAmount = 2;
        BookingArtist aArtist = new BookingArtist(new Number(1L), "aArtist", mock(Money.class), memberAmount, "aMusicStyle", 1);
        Event aEvent = new Event(new EventDate(EventDate.START_DATE), Activities.YOGA, aArtist);
        doReturn(Collections.singletonList(aEvent)).when(eventFactory).build(any());

        programService.add(mock(ProgramDto.class));

        verify(tripService).order(eq(ShuttleCategories.MILLENNIUM_FALCON), any(), any());
    }

    @Test
    void add_shouldOrderTripForArtistWithPassengerNumberAsId_whenThereIsASingleMember() {
        Number artistId = new Number(1L);
        Integer memberAmount = 1;
        BookingArtist aArtist = new BookingArtist(artistId, "aArtist", mock(Money.class), memberAmount, "aMusicStyle", 1);
        Event aEvent = new Event(new EventDate(EventDate.START_DATE), Activities.YOGA, aArtist);
        doReturn(Collections.singletonList(aEvent)).when(eventFactory).build(any());

        programService.add(mock(ProgramDto.class));

        verify(tripService).order(any(), any(), eq(Collections.singletonList(artistId)));
    }

    @Test
    void add_shouldOrderTripForArtistWithPassengerNumbersAsIds_whenThereAreMultipleMembers() {
        Number artistId = new Number(1L);
        Integer memberAmount = 2;
        BookingArtist aArtist = new BookingArtist(artistId, "aArtist", mock(Money.class), memberAmount, "aMusicStyle", 1);
        Event aEvent = new Event(new EventDate(EventDate.START_DATE), Activities.YOGA, aArtist);
        doReturn(Collections.singletonList(aEvent)).when(eventFactory).build(any());

        programService.add(mock(ProgramDto.class));

        verify(tripService).order(any(), any(), eq(Collections.nCopies(memberAmount, artistId)));
    }

    // TODO : Oxygen tests for artists

    // TODO : Oxygen tests for activities
}