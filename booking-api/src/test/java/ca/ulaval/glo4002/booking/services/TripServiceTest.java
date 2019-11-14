package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.BookingArtist;
import ca.ulaval.glo4002.booking.domain.events.EventDate;
import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.domain.passes.Pass;
import ca.ulaval.glo4002.booking.domain.shuttles.Passenger;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;
import ca.ulaval.glo4002.booking.factories.ShuttleFactory;
import ca.ulaval.glo4002.booking.repositories.TripRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TripServiceTest {

    private TripService service;
    private TripRepository repository;

    @BeforeEach
    void setUpService() {
        repository = mock(TripRepository.class);
        ShuttleFactory factory = new ShuttleFactory();

        service = new TripService(repository, factory);
    }

    @Test
    void orderForArtist_shouldOrderTripForArtistAtCorrectDate_whenThereIsASingleMember() {
        Integer memberAmount = 1;
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        BookingArtist aArtist = new BookingArtist(new Number(1L), "aArtist", mock(Money.class), memberAmount, "aMusicStyle", 1);

        service.orderForArtist(aArtist, aEventDate);

        verify(repository).addPassengersToNewArrival(any(), any(), eq(aEventDate));
        verify(repository).addPassengersToNewDeparture(any(), any(), eq(aEventDate));
    }

    @Test
    void orderForArtist_shouldOrderEtSpaceshipTripForArtist_whenThereIsASingleMember() {
        Integer memberAmount = 1;
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        BookingArtist aArtist = new BookingArtist(new Number(1L), "aArtist", mock(Money.class), memberAmount, "aMusicStyle", 1);

        service.orderForArtist(aArtist, aEventDate);

        verify(repository).addPassengersToNewArrival(any(), eq(ShuttleCategories.ET_SPACESHIP), any());
        verify(repository).addPassengersToNewDeparture(any(), eq(ShuttleCategories.ET_SPACESHIP), any());
    }

    @Test
    void orderForArtist_shouldOrderMillenniumFalconTripForArtist_whenThereAreMultipleMembers() {
        Integer memberAmount = 2;
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        BookingArtist aArtist = new BookingArtist(new Number(1L), "aArtist", mock(Money.class), memberAmount, "aMusicStyle", 1);

        service.orderForArtist(aArtist, aEventDate);

        verify(repository).addPassengersToNewArrival(any(), eq(ShuttleCategories.MILLENNIUM_FALCON), any());
        verify(repository).addPassengersToNewDeparture(any(), eq(ShuttleCategories.MILLENNIUM_FALCON), any());
    }

    @Test
    void orderForArtist_shouldOrderTripForArtistWithPassengerNumberAsId_whenThereIsASingleMember() {
        Number expectedId = new Number(1L);
        Integer memberAmount = 1;
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        BookingArtist aArtist = new BookingArtist(new Number(1L), "aArtist", mock(Money.class), memberAmount, "aMusicStyle", 1);

        service.orderForArtist(aArtist, aEventDate);

        verify(repository).addPassengersToNewDeparture(argThat((List<Passenger> passengers) ->
                passengers.stream().allMatch(passenger -> expectedId.equals(passenger.getPassNumber()))), any(), any());
        verify(repository).addPassengersToNewArrival(argThat((List<Passenger> passengers) ->
                passengers.stream().allMatch(passenger -> expectedId.equals(passenger.getPassNumber()))), any(), any());
    }

    @Test
    void orderForArtist_shouldOrderTripForArtistWithPassengerNumbersAsIds_whenThereAreMultipleMembers() {
        Number expectedId = new Number(1L);
        Integer memberAmount = 2;
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        BookingArtist aArtist = new BookingArtist(new Number(1L), "aArtist", mock(Money.class), memberAmount, "aMusicStyle", 1);

        service.orderForArtist(aArtist, aEventDate);

        verify(repository).addPassengersToNewDeparture(argThat((List<Passenger> passengers) ->
                passengers.stream().allMatch(passenger -> expectedId.equals(passenger.getPassNumber()))), any(), any());
        verify(repository).addPassengersToNewArrival(argThat((List<Passenger> passengers) ->
                passengers.stream().allMatch(passenger -> expectedId.equals(passenger.getPassNumber()))), any(), any());
    }

    @Test
    void orderForPasses_shouldAddPassengerToDeparturesOnce_whenThereIsASinglePass() {
        PassCategories category = PassCategories.SUPERNOVA;
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        Pass aPass = new Pass(mock(Number.class), mock(Money.class), aEventDate);
        List<Pass> somePasses = Collections.singletonList(aPass);

        service.orderForPasses(category, somePasses);

        verify(repository, times(1)).addPassengerToDepartures(any(), any(), eq(aEventDate));
    }

    @Test
    void orderForPasses_shouldAddPassengerToArrivalsOnce_whenThereIsASinglePass() {
        PassCategories category = PassCategories.SUPERNOVA;
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        Pass aPass = new Pass(mock(Number.class), mock(Money.class), aEventDate);
        List<Pass> somePasses = Collections.singletonList(aPass);

        service.orderForPasses(category, somePasses);

        verify(repository, times(1)).addPassengerToArrivals(any(), any(), eq(aEventDate));
    }

    @Test
    void orderForPasses_shouldAddPassengerToCorrectDeparturesDates_whenThereAreMultiplePasses() {
        PassCategories category = PassCategories.SUPERNOVA;
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        EventDate anotherEventDate = new EventDate(EventDate.END_DATE);
        Pass aPass = new Pass(mock(Number.class), mock(Money.class), aEventDate);
        Pass anotherPass = new Pass(mock(Number.class), mock(Money.class), anotherEventDate);
        List<Pass> somePasses = Arrays.asList(aPass, anotherPass);

        service.orderForPasses(category, somePasses);

        verify(repository).addPassengerToDepartures(any(), any(), eq(aEventDate));
        verify(repository).addPassengerToDepartures(any(), any(), eq(anotherEventDate));
    }

    @Test
    void orderForPasses_shouldAddPassengerToCorrectArrivalsDates_whenThereAreMultiplePasses() {
        PassCategories category = PassCategories.SUPERNOVA;
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        EventDate anotherEventDate = new EventDate(EventDate.END_DATE);
        Pass aPass = new Pass(mock(Number.class), mock(Money.class), aEventDate);
        Pass anotherPass = new Pass(mock(Number.class), mock(Money.class), anotherEventDate);
        List<Pass> somePasses = Arrays.asList(aPass, anotherPass);

        service.orderForPasses(category, somePasses);

        verify(repository).addPassengerToArrivals(any(), any(), eq(aEventDate));
        verify(repository).addPassengerToArrivals(any(), any(), eq(anotherEventDate));
    }

    @Test
    void orderForPasses_shouldAddPassengerToDeparturesMultipleTimes_whenThereAreMultiplePasses() {
        PassCategories category = PassCategories.SUPERNOVA;
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        EventDate anotherEventDate = new EventDate(EventDate.END_DATE);
        Pass aPass = new Pass(mock(Number.class), mock(Money.class), aEventDate);
        Pass anotherPass = new Pass(mock(Number.class), mock(Money.class), anotherEventDate);
        List<Pass> somePasses = Arrays.asList(aPass, anotherPass);

        service.orderForPasses(category, somePasses);

        verify(repository, times(2)).addPassengerToDepartures(any(), any(), any());
    }

    @Test
    void orderForPasses_shouldAddPassengerToArrivalsMultipleTimes_whenThereAreMultiplePasses() {
        PassCategories category = PassCategories.SUPERNOVA;
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        EventDate anotherEventDate = new EventDate(EventDate.END_DATE);
        Pass aPass = new Pass(mock(Number.class), mock(Money.class), aEventDate);
        Pass anotherPass = new Pass(mock(Number.class), mock(Money.class), anotherEventDate);
        List<Pass> somePasses = Arrays.asList(aPass, anotherPass);

        service.orderForPasses(category, somePasses);

        verify(repository, times(2)).addPassengerToArrivals(any(), any(), any());
    }

    @Test
    void orderForPasses_shouldAddPassengerToDeparturesOfEndDate_whenPassHasNoEventDate() {
        PassCategories category = PassCategories.SUPERNOVA;
        Pass aPass = new Pass(mock(Number.class), mock(Money.class));
        List<Pass> somePasses = Collections.singletonList(aPass);

        service.orderForPasses(category, somePasses);

        verify(repository).addPassengerToDepartures(any(), any(), eq(new EventDate(EventDate.END_DATE)));
    }

    @Test
    void orderForPasses_shouldAddPassengerToArrivalsOfStartDate_whenPassHasNoEventDate() {
        PassCategories category = PassCategories.SUPERNOVA;
        Pass aPass = new Pass(mock(Number.class), mock(Money.class));
        List<Pass> somePasses = Collections.singletonList(aPass);

        service.orderForPasses(category, somePasses);

        verify(repository).addPassengerToArrivals(any(), any(), eq(new EventDate(EventDate.START_DATE)));
    }
}