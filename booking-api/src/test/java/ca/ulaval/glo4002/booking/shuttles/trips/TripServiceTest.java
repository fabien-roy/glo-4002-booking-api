package ca.ulaval.glo4002.booking.shuttles.trips;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ca.ulaval.glo4002.booking.shuttles.*;
import ca.ulaval.glo4002.booking.shuttles.trips.TripRepository;
import ca.ulaval.glo4002.booking.shuttles.trips.TripService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.numbers.Number;
import ca.ulaval.glo4002.booking.artists.BookingArtist;
import ca.ulaval.glo4002.booking.events.EventDate;
import ca.ulaval.glo4002.booking.profits.Money;
import ca.ulaval.glo4002.booking.passes.Pass;
import ca.ulaval.glo4002.booking.passes.PassCategories;

class TripServiceTest {

	private TripService service;
	private TripRepository repository;

	@BeforeEach
	void setUpService() {
		repository = mock(TripRepository.class);
		ShuttleFactory shuttleFactory = new ShuttleFactory();

		service = new TripService(repository, shuttleFactory);
	}

	@Test
	void orderForArtist_shouldOrderTripForArtistAtCorrectDate_whenThereIsASingleMember() {
		Integer memberAmount = 1;
		EventDate anEventDate = new EventDate(EventDate.START_DATE);
		Money money = mock(Money.class);
		BookingArtist aArtist = new BookingArtist(1, "aArtist", money, memberAmount, "aMusicStyle", 1,
				new ArrayList<>());

		service.orderForArtist(aArtist, anEventDate);

		verify(repository).addPassengersToNewArrival(any(), any(), eq(anEventDate));
		verify(repository).addPassengersToNewDeparture(any(), any(), eq(anEventDate));
	}

	@Test
	void orderForArtist_shouldOrderEtSpaceshipTripForArtist_whenThereIsASingleMember() {
		Integer memberAmount = 1;
		EventDate anEventDate = new EventDate(EventDate.START_DATE);
		Money money = mock(Money.class);
		BookingArtist aArtist = new BookingArtist(1, "aArtist", money, memberAmount, "aMusicStyle", 1,
				new ArrayList<>());

		service.orderForArtist(aArtist, anEventDate);

		verify(repository).addPassengersToNewArrival(any(), eq(ShuttleCategories.ET_SPACESHIP), any());
		verify(repository).addPassengersToNewDeparture(any(), eq(ShuttleCategories.ET_SPACESHIP), any());
	}

	@Test
	void orderForArtist_shouldOrderMillenniumFalconTripForArtist_whenThereAreMultipleMembers() {
		Integer memberAmount = 2;
		EventDate anEventDate = new EventDate(EventDate.START_DATE);
		Money money = mock(Money.class);
		BookingArtist aArtist = new BookingArtist(1, "aArtist", money, memberAmount, "aMusicStyle", 1,
				new ArrayList<>());

		service.orderForArtist(aArtist, anEventDate);

		verify(repository).addPassengersToNewArrival(any(), eq(ShuttleCategories.MILLENNIUM_FALCON), any());
		verify(repository).addPassengersToNewDeparture(any(), eq(ShuttleCategories.MILLENNIUM_FALCON), any());
	}

	@Test
	void orderForArtist_shouldOrderTripForArtistWithPassengerNumberAsId_whenThereIsASingleMember() {
		Number expectedId = new Number(1L);
		Integer memberAmount = 1;
		EventDate anEventDate = new EventDate(EventDate.START_DATE);
		Money money = mock(Money.class);
		BookingArtist aArtist = new BookingArtist(1, "aArtist", money, memberAmount, "aMusicStyle", 1,
				new ArrayList<>());

		service.orderForArtist(aArtist, anEventDate);

		verify(repository).addPassengersToNewDeparture(argThat((List<Passenger> passengers) -> passengers.stream()
				.allMatch(passenger -> expectedId.equals(passenger.getPassNumber()))), any(), any());
		verify(repository).addPassengersToNewArrival(argThat((List<Passenger> passengers) -> passengers.stream()
				.allMatch(passenger -> expectedId.equals(passenger.getPassNumber()))), any(), any());
	}

	@Test
	void orderForArtist_shouldOrderTripForArtistWithPassengerNumbersAsIds_whenThereAreMultipleMembers() {
		Number expectedId = new Number(1L);
		Integer memberAmount = 2;
		EventDate anEventDate = new EventDate(EventDate.START_DATE);
		Money money = mock(Money.class);
		BookingArtist aArtist = new BookingArtist(1, "aArtist", money, memberAmount, "aMusicStyle", 1,
				new ArrayList<>());

		service.orderForArtist(aArtist, anEventDate);

		verify(repository).addPassengersToNewDeparture(argThat((List<Passenger> passengers) -> passengers.stream()
				.allMatch(passenger -> expectedId.equals(passenger.getPassNumber()))), any(), any());
		verify(repository).addPassengersToNewArrival(argThat((List<Passenger> passengers) -> passengers.stream()
				.allMatch(passenger -> expectedId.equals(passenger.getPassNumber()))), any(), any());
	}

	@Test
	void orderForPasses_shouldAddPassengerToDeparturesOnce_whenThereIsASinglePass() {
		PassCategories category = PassCategories.SUPERNOVA;
		EventDate anEventDate = new EventDate(EventDate.START_DATE);
		Money money = mock(Money.class);
		Pass aPass = new Pass(mock(Number.class), money, anEventDate);
		List<Pass> somePasses = Collections.singletonList(aPass);

		service.orderForPasses(category, somePasses);

		verify(repository, times(1)).addPassengerToDepartures(any(), any(), eq(anEventDate));
	}

	@Test
	void orderForPasses_shouldAddPassengerToArrivalsOnce_whenThereIsASinglePass() {
		PassCategories category = PassCategories.SUPERNOVA;
		EventDate anEventDate = new EventDate(EventDate.START_DATE);
		Money money = mock(Money.class);
		Pass aPass = new Pass(mock(Number.class), money, anEventDate);
		List<Pass> somePasses = Collections.singletonList(aPass);

		service.orderForPasses(category, somePasses);

		verify(repository, times(1)).addPassengerToArrivals(any(), any(), eq(anEventDate));
	}

	@Test
	void orderForPasses_shouldAddPassengerToCorrectDeparturesDates_whenThereAreMultiplePasses() {
		PassCategories category = PassCategories.SUPERNOVA;
		EventDate anEventDate = new EventDate(EventDate.START_DATE);
		EventDate anotherEventDate = new EventDate(EventDate.END_DATE);
		Money money = mock(Money.class);
		Number number = mock(Number.class);
		Pass aPass = new Pass(number, money, anEventDate);
		Pass anotherPass = new Pass(number, money, anotherEventDate);
		List<Pass> somePasses = Arrays.asList(aPass, anotherPass);

		service.orderForPasses(category, somePasses);

		verify(repository).addPassengerToDepartures(any(), any(), eq(anEventDate));
		verify(repository).addPassengerToDepartures(any(), any(), eq(anotherEventDate));
	}

	@Test
	void orderForPasses_shouldAddPassengerToCorrectArrivalsDates_whenThereAreMultiplePasses() {
		PassCategories category = PassCategories.SUPERNOVA;
		EventDate anEventDate = new EventDate(EventDate.START_DATE);
		EventDate anotherEventDate = new EventDate(EventDate.END_DATE);
		Money money = mock(Money.class);
		Number number = mock(Number.class);
		Pass aPass = new Pass(number, money, anEventDate);
		Pass anotherPass = new Pass(number, money, anotherEventDate);
		List<Pass> somePasses = Arrays.asList(aPass, anotherPass);

		service.orderForPasses(category, somePasses);

		verify(repository).addPassengerToArrivals(any(), any(), eq(anEventDate));
		verify(repository).addPassengerToArrivals(any(), any(), eq(anotherEventDate));
	}

	@Test
	void orderForPasses_shouldAddPassengerToDeparturesMultipleTimes_whenThereAreMultiplePasses() {
		PassCategories category = PassCategories.SUPERNOVA;
		EventDate anEventDate = new EventDate(EventDate.START_DATE);
		EventDate anotherEventDate = new EventDate(EventDate.END_DATE);
		Money money = mock(Money.class);
		Number number = mock(Number.class);
		Pass aPass = new Pass(number, money, anEventDate);
		Pass anotherPass = new Pass(number, money, anotherEventDate);
		List<Pass> somePasses = Arrays.asList(aPass, anotherPass);

		service.orderForPasses(category, somePasses);

		verify(repository, times(2)).addPassengerToDepartures(any(), any(), any());
	}

	@Test
	void orderForPasses_shouldAddPassengerToArrivalsMultipleTimes_whenThereAreMultiplePasses() {
		PassCategories category = PassCategories.SUPERNOVA;
		EventDate anEventDate = new EventDate(EventDate.START_DATE);
		EventDate anotherEventDate = new EventDate(EventDate.END_DATE);
		Money money = mock(Money.class);
		Number number = mock(Number.class);
		Pass aPass = new Pass(number, money, anEventDate);
		Pass anotherPass = new Pass(number, money, anotherEventDate);
		List<Pass> somePasses = Arrays.asList(aPass, anotherPass);

		service.orderForPasses(category, somePasses);

		verify(repository, times(2)).addPassengerToArrivals(any(), any(), any());
	}

	@Test
	void orderForPasses_shouldAddPassengerToDeparturesOfEndDate_whenPassHasNoEventDate() {
		PassCategories category = PassCategories.SUPERNOVA;
		Money money = mock(Money.class);
		Number number = mock(Number.class);
		Pass aPass = new Pass(number, money);
		List<Pass> somePasses = Collections.singletonList(aPass);

		service.orderForPasses(category, somePasses);

		verify(repository).addPassengerToDepartures(any(), any(), eq(new EventDate(EventDate.END_DATE)));
	}

	@Test
	void orderForPasses_shouldAddPassengerToArrivalsOfStartDate_whenPassHasNoEventDate() {
		PassCategories category = PassCategories.SUPERNOVA;
		Money money = mock(Money.class);
		Number number = mock(Number.class);
		Pass aPass = new Pass(number, money);
		List<Pass> somePasses = Collections.singletonList(aPass);

		service.orderForPasses(category, somePasses);

		verify(repository).addPassengerToArrivals(any(), any(), eq(new EventDate(EventDate.START_DATE)));
	}
}