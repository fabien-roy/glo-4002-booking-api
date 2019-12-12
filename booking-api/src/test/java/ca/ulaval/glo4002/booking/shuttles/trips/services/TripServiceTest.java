package ca.ulaval.glo4002.booking.shuttles.trips.services;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.passes.domain.Pass;
import ca.ulaval.glo4002.booking.passes.domain.PassCategories;
import ca.ulaval.glo4002.booking.passes.domain.PassNumber;
import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.program.artists.domain.Artist;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.shuttles.domain.Passenger;
import ca.ulaval.glo4002.booking.shuttles.domain.ShuttleCategories;
import ca.ulaval.glo4002.booking.shuttles.domain.ShuttleFactory;
import ca.ulaval.glo4002.booking.shuttles.trips.infrastructure.TripRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class TripServiceTest {

	private TripService service;
	private FestivalConfiguration festivalConfiguration;
	private TripRepository repository;

	@BeforeEach
	void setUpService() {
		repository = mock(TripRepository.class);
		ShuttleFactory shuttleFactory = new ShuttleFactory();

		service = new TripService(festivalConfiguration, repository, shuttleFactory);
	}

	@BeforeEach
	void setUpConfiguration() {
		festivalConfiguration = mock(FestivalConfiguration.class);

		when(festivalConfiguration.getStartEventDate()).thenReturn(FestivalConfiguration.getDefaultStartEventDate());
		when(festivalConfiguration.getEndEventDate()).thenReturn(FestivalConfiguration.getDefaultEndEventDate());
	}

	@Test
	void orderForArtist_shouldOrderTripForArtistAtCorrectDate_whenThereIsASingleMember() {
		Integer memberAmount = 1;
		EventDate anEventDate = FestivalConfiguration.getDefaultStartEventDate();
		Money money = mock(Money.class);
		Artist aArtist = new Artist(1, "aArtist", money, memberAmount, "aMusicStyle", 1,
				new ArrayList<>());

		service.orderForArtist(aArtist, anEventDate);

		verify(repository).addPassengersToNewArrival(any(), any(), eq(anEventDate));
		verify(repository).addPassengersToNewDeparture(any(), any(), eq(anEventDate));
	}

	@Test
	void orderForArtist_shouldOrderEtSpaceshipTripForArtist_whenThereIsASingleMember() {
		Integer memberAmount = 1;
		EventDate anEventDate = FestivalConfiguration.getDefaultStartEventDate();
		Money money = mock(Money.class);
		Artist aArtist = new Artist(1, "aArtist", money, memberAmount, "aMusicStyle", 1,
				new ArrayList<>());

		service.orderForArtist(aArtist, anEventDate);

		verify(repository).addPassengersToNewArrival(any(), eq(ShuttleCategories.ET_SPACESHIP), any());
		verify(repository).addPassengersToNewDeparture(any(), eq(ShuttleCategories.ET_SPACESHIP), any());
	}

	@Test
	void orderForArtist_shouldOrderMillenniumFalconTripForArtist_whenThereAreMultipleMembers() {
		Integer memberAmount = 2;
		EventDate anEventDate = FestivalConfiguration.getDefaultStartEventDate();
		Money money = mock(Money.class);
		Artist aArtist = new Artist(1, "aArtist", money, memberAmount, "aMusicStyle", 1,
				new ArrayList<>());

		service.orderForArtist(aArtist, anEventDate);

		verify(repository).addPassengersToNewArrival(any(), eq(ShuttleCategories.MILLENNIUM_FALCON), any());
		verify(repository).addPassengersToNewDeparture(any(), eq(ShuttleCategories.MILLENNIUM_FALCON), any());
	}

	@Test
	void orderForArtist_shouldOrderTripForArtistWithPassengerNumberAsId_whenThereIsASingleMember() {
		PassNumber expectedPassNumber = new PassNumber(1L);
		Integer memberAmount = 1;
		EventDate anEventDate = FestivalConfiguration.getDefaultStartEventDate();
		Money money = mock(Money.class);
		Artist aArtist = new Artist(1, "aArtist", money, memberAmount, "aMusicStyle", 1,
				new ArrayList<>());

		service.orderForArtist(aArtist, anEventDate);

		// TODO : Simplify those assertions
		verify(repository).addPassengersToNewDeparture(argThat((List<Passenger> passengers) -> passengers.stream()
				.allMatch(passenger -> expectedPassNumber.equals(passenger.getPassNumber()))), any(), any());
		verify(repository).addPassengersToNewArrival(argThat((List<Passenger> passengers) -> passengers.stream()
				.allMatch(passenger -> expectedPassNumber.equals(passenger.getPassNumber()))), any(), any());
	}

	@Test
	void orderForArtist_shouldOrderTripForArtistWithPassengerNumbersAsIds_whenThereAreMultipleMembers() {
		PassNumber expectedPassNumber = new PassNumber(1L);
		Integer memberAmount = 2;
		EventDate anEventDate = FestivalConfiguration.getDefaultStartEventDate();
		Money money = mock(Money.class);
		Artist aArtist = new Artist(1, "aArtist", money, memberAmount, "aMusicStyle", 1,
				new ArrayList<>());

		service.orderForArtist(aArtist, anEventDate);

		// TODO : Simplify those assertions
		verify(repository).addPassengersToNewDeparture(argThat((List<Passenger> passengers) -> passengers.stream()
				.allMatch(passenger -> expectedPassNumber.equals(passenger.getPassNumber()))), any(), any());
		verify(repository).addPassengersToNewArrival(argThat((List<Passenger> passengers) -> passengers.stream()
				.allMatch(passenger -> expectedPassNumber.equals(passenger.getPassNumber()))), any(), any());
	}

	@Test
	void orderForPasses_shouldAddPassengerToDeparturesOnce_whenThereIsASinglePass() {
		PassCategories category = PassCategories.SUPERNOVA;
		EventDate anEventDate = FestivalConfiguration.getDefaultStartEventDate();
		Money money = mock(Money.class);
		Pass aPass = new Pass(mock(PassNumber.class), money, anEventDate);
		List<Pass> somePasses = Collections.singletonList(aPass);

		service.orderForPasses(category, somePasses);

		verify(repository, times(1)).addPassengerToDepartures(any(), any(), eq(anEventDate));
	}

	@Test
	void orderForPasses_shouldAddPassengerToArrivalsOnce_whenThereIsASinglePass() {
		PassCategories category = PassCategories.SUPERNOVA;
		EventDate anEventDate = FestivalConfiguration.getDefaultStartEventDate();
		Money money = mock(Money.class);
		Pass aPass = new Pass(mock(PassNumber.class), money, anEventDate);
		List<Pass> somePasses = Collections.singletonList(aPass);

		service.orderForPasses(category, somePasses);

		verify(repository, times(1)).addPassengerToArrivals(any(), any(), eq(anEventDate));
	}

	@Test
	void orderForPasses_shouldAddPassengerToCorrectDeparturesDates_whenThereAreMultiplePasses() {
		PassCategories category = PassCategories.SUPERNOVA;
		EventDate anEventDate = FestivalConfiguration.getDefaultStartEventDate();
		EventDate anotherEventDate = FestivalConfiguration.getDefaultEndEventDate();
		Money money = mock(Money.class);
		PassNumber number = mock(PassNumber.class);
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
		EventDate anEventDate = FestivalConfiguration.getDefaultStartEventDate();
		EventDate anotherEventDate = FestivalConfiguration.getDefaultEndEventDate();
		Money money = mock(Money.class);
		PassNumber number = mock(PassNumber.class);
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
		EventDate anEventDate = FestivalConfiguration.getDefaultStartEventDate();
		EventDate anotherEventDate = FestivalConfiguration.getDefaultEndEventDate();
		Money money = mock(Money.class);
		PassNumber number = mock(PassNumber.class);
		Pass aPass = new Pass(number, money, anEventDate);
		Pass anotherPass = new Pass(number, money, anotherEventDate);
		List<Pass> somePasses = Arrays.asList(aPass, anotherPass);

		service.orderForPasses(category, somePasses);

		verify(repository, times(2)).addPassengerToDepartures(any(), any(), any());
	}

	@Test
	void orderForPasses_shouldAddPassengerToArrivalsMultipleTimes_whenThereAreMultiplePasses() {
		PassCategories category = PassCategories.SUPERNOVA;
		EventDate anEventDate = FestivalConfiguration.getDefaultStartEventDate();
		EventDate anotherEventDate = FestivalConfiguration.getDefaultEndEventDate();
		Money money = mock(Money.class);
		PassNumber number = mock(PassNumber.class);
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
		PassNumber number = mock(PassNumber.class);
		Pass aPass = new Pass(number, money);
		List<Pass> somePasses = Collections.singletonList(aPass);

		service.orderForPasses(category, somePasses);

		verify(repository).addPassengerToDepartures(any(), any(), eq(FestivalConfiguration.getDefaultEndEventDate()));
	}

	@Test
	void orderForPasses_shouldAddPassengerToArrivalsOfStartDate_whenPassHasNoEventDate() {
		PassCategories category = PassCategories.SUPERNOVA;
		Money money = mock(Money.class);
		PassNumber number = mock(PassNumber.class);
		Pass aPass = new Pass(number, money);
		List<Pass> somePasses = Collections.singletonList(aPass);

		service.orderForPasses(category, somePasses);

		verify(repository).addPassengerToArrivals(any(), any(), eq(FestivalConfiguration.getDefaultStartEventDate()));
	}
}