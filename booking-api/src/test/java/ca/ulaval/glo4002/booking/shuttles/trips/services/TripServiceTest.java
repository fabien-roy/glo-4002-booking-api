package ca.ulaval.glo4002.booking.shuttles.trips.services;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.passes.domain.Pass;
import ca.ulaval.glo4002.booking.passes.domain.PassCategories;
import ca.ulaval.glo4002.booking.passes.domain.PassNumber;
import ca.ulaval.glo4002.booking.passes.domain.PassOptions;
import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.program.artists.domain.Artist;
import ca.ulaval.glo4002.booking.program.artists.domain.ArtistId;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.shuttles.domain.Passenger;
import ca.ulaval.glo4002.booking.shuttles.domain.ShuttleCategories;
import ca.ulaval.glo4002.booking.shuttles.trips.domain.TripRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class TripServiceTest {

	private TripService service;
	private TripRepository repository;

	@BeforeEach
	void setUpService() {
		repository = mock(TripRepository.class);

		service = new TripService(repository);
	}

	@Test
	void orderForArtist_shouldOrderTripForArtistAtCorrectDate() {
		Integer memberAmount = 1;
		EventDate eventDate = FestivalConfiguration.getDefaultStartEventDate();
		Money money = mock(Money.class);
		ArtistId id = new ArtistId(1);
		Artist artist = new Artist(id, "aArtist", money, memberAmount, 1);

		service.orderForArtist(artist, eventDate);

		verify(repository).addPassengersToNewArrival(any(), any(), eq(eventDate));
		verify(repository).addPassengersToNewDeparture(any(), any(), eq(eventDate));
	}

	@Test
	void orderForArtist_shouldOrderEtSpaceshipTripForArtist_whenThereIsASingleMember() {
	    ShuttleCategories expectedCategory = ShuttleCategories.ET_SPACESHIP;
		Integer memberAmount = 1;
		EventDate eventDate = FestivalConfiguration.getDefaultStartEventDate();
		Money money = mock(Money.class);
		ArtistId id = new ArtistId(1);
		Artist artist = new Artist(id, "aArtist", money, memberAmount, 1);

		service.orderForArtist(artist, eventDate);

		verify(repository).addPassengersToNewArrival(any(), eq(expectedCategory), any());
		verify(repository).addPassengersToNewDeparture(any(), eq(expectedCategory), any());
	}

	@Test
	void orderForArtist_shouldOrderMillenniumFalconTripForArtist_whenThereAreMultipleMembers() {
		ShuttleCategories expectedCategory = ShuttleCategories.MILLENNIUM_FALCON;
		Integer memberAmount = 2;
		EventDate eventDate = FestivalConfiguration.getDefaultStartEventDate();
		Money money = mock(Money.class);
		ArtistId id = new ArtistId(1);
		Artist artist = new Artist(id, "aArtist", money, memberAmount, 1);

		service.orderForArtist(artist, eventDate);

		verify(repository).addPassengersToNewArrival(any(), eq(expectedCategory), any());
		verify(repository).addPassengersToNewDeparture(any(), eq(expectedCategory), any());
	}

	@Test
	void orderForArtist_shouldOrderTripForArtistWithPassengerNumberAsId_whenThereIsASingleMember() {
		int expectedPassengerNumber = 1;
		Integer memberAmount = 1;
		EventDate eventDate = FestivalConfiguration.getDefaultStartEventDate();
		Money money = mock(Money.class);
		ArtistId id = new ArtistId(expectedPassengerNumber);
		Artist artist = new Artist(id, "aArtist", money, memberAmount, 1);

		service.orderForArtist(artist, eventDate);

		verify(repository).addPassengersToNewDeparture(argThat((List<Passenger> passengers) -> passengers.stream()
				.allMatch(passenger -> expectedPassengerNumber == passenger.getNumber())), any(), any());
		verify(repository).addPassengersToNewArrival(argThat((List<Passenger> passengers) -> passengers.stream()
				.allMatch(passenger -> expectedPassengerNumber == passenger.getNumber())), any(), any());
	}

	@Test
	void orderForArtist_shouldOrderTripForArtistWithPassengerNumbersAsIds_whenThereAreMultipleMembers() {
		int expectedPassengerNumber = 1;
		Integer memberAmount = 2;
		EventDate eventDate = FestivalConfiguration.getDefaultStartEventDate();
		Money money = mock(Money.class);
		ArtistId id = new ArtistId(expectedPassengerNumber);
		Artist artist = new Artist(id, "aArtist", money, memberAmount, 1);

		service.orderForArtist(artist, eventDate);

		verify(repository).addPassengersToNewDeparture(argThat((List<Passenger> passengers) -> passengers.stream()
				.allMatch(passenger -> expectedPassengerNumber == passenger.getNumber())), any(), any());
		verify(repository).addPassengersToNewArrival(argThat((List<Passenger> passengers) -> passengers.stream()
				.allMatch(passenger -> expectedPassengerNumber == passenger.getNumber())), any(), any());
	}

	@Test
	void orderForPasses_shouldAddPassengerToDeparturesOnce_whenThereIsASinglePass() {
		EventDate eventDate = FestivalConfiguration.getDefaultStartEventDate();
		Pass pass = new Pass(Collections.singletonList(eventDate), PassCategories.SUPERNOVA, PassOptions.SINGLE_PASS, mock(Money.class));
		pass.setNumber(new PassNumber(1L));
		List<Pass> passes = Collections.singletonList(pass);

		service.orderForPasses(passes);

		verify(repository, times(1)).addPassengerToDepartures(any(), any(), eq(eventDate));
	}

	@Test
	void orderForPasses_shouldAddPassengerToArrivalsOnce_whenThereIsASinglePass() {
		EventDate eventDate = FestivalConfiguration.getDefaultStartEventDate();
		Pass pass = new Pass(Collections.singletonList(eventDate), PassCategories.SUPERNOVA, PassOptions.SINGLE_PASS, mock(Money.class));
		pass.setNumber(new PassNumber(1L));
		List<Pass> passes = Collections.singletonList(pass);

		service.orderForPasses(passes);

		verify(repository, times(1)).addPassengerToArrivals(any(), any(), eq(eventDate));
	}

	@Test
	void orderForPasses_shouldAddPassengerToCorrectDeparturesDates_whenThereAreMultiplePasses() {
		int passQuantity = 2;
		EventDate eventDate = FestivalConfiguration.getDefaultStartEventDate();
		Pass pass = new Pass(Collections.singletonList(eventDate), PassCategories.SUPERNOVA, PassOptions.SINGLE_PASS, mock(Money.class));
		pass.setNumber(new PassNumber(1L));
		List<Pass> passes = Collections.nCopies(passQuantity, pass);

		service.orderForPasses(passes);

		verify(repository, times(passQuantity)).addPassengerToArrivals(any(), any(), eq(eventDate));
	}

	@Test
	void orderForPasses_shouldAddPassengerToDeparturesMultipleTimes_whenThereAreMultiplePasses() {
		int passQuantity = 2;
		EventDate eventDate = FestivalConfiguration.getDefaultStartEventDate();
		Pass pass = new Pass(Collections.singletonList(eventDate), PassCategories.SUPERNOVA, PassOptions.SINGLE_PASS, mock(Money.class));
		pass.setNumber(new PassNumber(1L));
		List<Pass> passes = Collections.nCopies(passQuantity, pass);

		service.orderForPasses(passes);

		verify(repository, times(passQuantity)).addPassengerToDepartures(any(), any(), eq(eventDate));
	}

	@Test
	void orderForPasses_shouldAddPassengerWithEtSpaceshipCategory_whenPassCategoryIsSupernova() {
		ShuttleCategories expectedShuttleCategory = ShuttleCategories.ET_SPACESHIP;
		PassCategories passCategory = PassCategories.SUPERNOVA;
		EventDate eventDate = FestivalConfiguration.getDefaultStartEventDate();
		Pass pass = new Pass(Collections.singletonList(eventDate), passCategory, PassOptions.SINGLE_PASS, mock(Money.class));
		pass.setNumber(new PassNumber(1L));
		List<Pass> passes = Collections.singletonList(pass);

		service.orderForPasses(passes);

		verify(repository).addPassengerToArrivals(any(), eq(expectedShuttleCategory), any());
		verify(repository).addPassengerToDepartures(any(), eq(expectedShuttleCategory), any());
	}

	@Test
	void orderForPasses_shouldAddPassengerWithMillenniumFalconCategory_whenPassCategoryIsSupergiant() {
		ShuttleCategories expectedShuttleCategory = ShuttleCategories.MILLENNIUM_FALCON;
		PassCategories passCategory = PassCategories.SUPERGIANT;
		EventDate eventDate = FestivalConfiguration.getDefaultStartEventDate();
		Pass pass = new Pass(Collections.singletonList(eventDate), passCategory, PassOptions.SINGLE_PASS, mock(Money.class));
		pass.setNumber(new PassNumber(1L));
		List<Pass> passes = Collections.singletonList(pass);

		service.orderForPasses(passes);

		verify(repository).addPassengerToArrivals(any(), eq(expectedShuttleCategory), any());
		verify(repository).addPassengerToDepartures(any(), eq(expectedShuttleCategory), any());
	}

	@Test
	void orderForPasses_shouldAddPassengerWithSpaceXCategory_whenPassCategoryIsNebula() {
		ShuttleCategories expectedShuttleCategory = ShuttleCategories.SPACE_X;
		PassCategories passCategory = PassCategories.NEBULA;
		EventDate eventDate = FestivalConfiguration.getDefaultStartEventDate();
		Pass pass = new Pass(Collections.singletonList(eventDate), passCategory, PassOptions.SINGLE_PASS, mock(Money.class));
		pass.setNumber(new PassNumber(1L));
		List<Pass> passes = Collections.singletonList(pass);

		service.orderForPasses(passes);

		verify(repository).addPassengerToArrivals(any(), eq(expectedShuttleCategory), any());
		verify(repository).addPassengerToDepartures(any(), eq(expectedShuttleCategory), any());
	}
}