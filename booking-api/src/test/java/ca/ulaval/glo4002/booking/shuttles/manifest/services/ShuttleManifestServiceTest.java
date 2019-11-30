package ca.ulaval.glo4002.booking.shuttles.manifest.services;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.program.events.domain.EventDateFactory;
import ca.ulaval.glo4002.booking.shuttles.manifest.rest.mappers.ShuttleManifestMapper;
import ca.ulaval.glo4002.booking.shuttles.trips.infrastructure.TripRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ShuttleManifestServiceTest {

	private ShuttleManifestService service;
	private TripRepository tripRepository;
	private ShuttleManifestMapper mapper;

	@BeforeEach
	void setUpService() {
		tripRepository = mock(TripRepository.class);
		FestivalConfiguration festivalConfiguration = new FestivalConfiguration();
		EventDateFactory eventDateFactory = new EventDateFactory(festivalConfiguration);

		mapper = mock(ShuttleManifestMapper.class);

		this.service = new ShuttleManifestService(tripRepository, mapper, eventDateFactory);
	}

	@Test
	void getWithDate_shouldCallMapper() {
		String aDate = FestivalConfiguration.getDefaultStartEventDate().toString();

		service.getTripsForDate(aDate);

		verify(mapper).toResponse(any(), any());
	}

	@Test
	void getWithDate_shouldCallRepositoryForArrivals() {
		EventDate aDate = FestivalConfiguration.getDefaultStartEventDate();

		service.getTripsForDate(aDate.toString());

		verify(tripRepository).getArrivalsForDate(aDate);
	}

	@Test
	void getWithDate_shouldCallRepositoryForDepartures() {
		EventDate aDate = FestivalConfiguration.getDefaultStartEventDate();

		service.getTripsForDate(aDate.toString());

		verify(tripRepository).getDeparturesForDate(aDate);
	}

	@Test
	void getWithoutDate_shouldCallMapper() {
		service.getTrips();

		verify(mapper).toResponse(any(), any());
	}

	@Test
	void getWithoutDate_shouldCallRepositoryForArrivals() {
		service.getTrips();

		verify(tripRepository).getArrivals();
	}

	@Test
	void getWithoutDate_shouldCallRepositoryForDepartures() {
		service.getTrips();

		verify(tripRepository).getDepartures();
	}
}
