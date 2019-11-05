package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.events.EventDate;
import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.domain.passes.Pass;
import ca.ulaval.glo4002.booking.enums.PassCategories;
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
    void orderAll_shouldAddPassengerToDeparturesOnce_whenThereIsASinglePass() {
        PassCategories category = PassCategories.SUPERNOVA;
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        Pass aPass = new Pass(mock(Number.class), mock(Money.class), aEventDate);
        List<Pass> somePasses = Collections.singletonList(aPass);

        service.orderAll(category, somePasses);

        verify(repository, times(1)).addPassengerToDepartures(any(), any(), eq(aEventDate));
    }

    @Test
    void orderAll_shouldAddPassengerToArrivalsOnce_whenThereIsASinglePass() {
        PassCategories category = PassCategories.SUPERNOVA;
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        Pass aPass = new Pass(mock(Number.class), mock(Money.class), aEventDate);
        List<Pass> somePasses = Collections.singletonList(aPass);

        service.orderAll(category, somePasses);

        verify(repository, times(1)).addPassengerToArrivals(any(), any(), eq(aEventDate));
    }

    @Test
    void orderAll_shouldAddPassengerToCorrectDeparturesDates_whenThereAreMultiplePasses() {
        PassCategories category = PassCategories.SUPERNOVA;
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        EventDate anotherEventDate = new EventDate(EventDate.END_DATE);
        Pass aPass = new Pass(mock(Number.class), mock(Money.class), aEventDate);
        Pass anotherPass = new Pass(mock(Number.class), mock(Money.class), anotherEventDate);
        List<Pass> somePasses = Arrays.asList(aPass, anotherPass);

        service.orderAll(category, somePasses);

        verify(repository).addPassengerToDepartures(any(), any(), eq(aEventDate));
        verify(repository).addPassengerToDepartures(any(), any(), eq(anotherEventDate));
    }

    @Test
    void orderAll_shouldAddPassengerToCorrectArrivalsDates_whenThereAreMultiplePasses() {
        PassCategories category = PassCategories.SUPERNOVA;
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        EventDate anotherEventDate = new EventDate(EventDate.END_DATE);
        Pass aPass = new Pass(mock(Number.class), mock(Money.class), aEventDate);
        Pass anotherPass = new Pass(mock(Number.class), mock(Money.class), anotherEventDate);
        List<Pass> somePasses = Arrays.asList(aPass, anotherPass);

        service.orderAll(category, somePasses);

        verify(repository).addPassengerToArrivals(any(), any(), eq(aEventDate));
        verify(repository).addPassengerToArrivals(any(), any(), eq(anotherEventDate));
    }

    @Test
    void orderAll_shouldAddPassengerToDeparturesMultipleTimes_whenThereAreMultiplePasses() {
        PassCategories category = PassCategories.SUPERNOVA;
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        EventDate anotherEventDate = new EventDate(EventDate.END_DATE);
        Pass aPass = new Pass(mock(Number.class), mock(Money.class), aEventDate);
        Pass anotherPass = new Pass(mock(Number.class), mock(Money.class), anotherEventDate);
        List<Pass> somePasses = Arrays.asList(aPass, anotherPass);

        service.orderAll(category, somePasses);

        verify(repository, times(2)).addPassengerToDepartures(any(), any(), any());
    }

    @Test
    void orderAll_shouldAddPassengerToArrivalsMultipleTimes_whenThereAreMultiplePasses() {
        PassCategories category = PassCategories.SUPERNOVA;
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        EventDate anotherEventDate = new EventDate(EventDate.END_DATE);
        Pass aPass = new Pass(mock(Number.class), mock(Money.class), aEventDate);
        Pass anotherPass = new Pass(mock(Number.class), mock(Money.class), anotherEventDate);
        List<Pass> somePasses = Arrays.asList(aPass, anotherPass);

        service.orderAll(category, somePasses);

        verify(repository, times(2)).addPassengerToArrivals(any(), any(), any());
    }

    @Test
    void orderAll_shouldAddPassengerToDeparturesOfEndDate_whenPassHasNoEventDate() {
        PassCategories category = PassCategories.SUPERNOVA;
        Pass aPass = new Pass(mock(Number.class), mock(Money.class));
        List<Pass> somePasses = Collections.singletonList(aPass);

        service.orderAll(category, somePasses);

        verify(repository).addPassengerToDepartures(any(), any(), eq(new EventDate(EventDate.END_DATE)));
    }

    @Test
    void orderAll_shouldAddPassengerToArrivalsOfStartDate_whenPassHasNoEventDate() {
        PassCategories category = PassCategories.SUPERNOVA;
        Pass aPass = new Pass(mock(Number.class), mock(Money.class));
        List<Pass> somePasses = Collections.singletonList(aPass);

        service.orderAll(category, somePasses);

        verify(repository).addPassengerToArrivals(any(), any(), eq(new EventDate(EventDate.START_DATE)));
    }
}