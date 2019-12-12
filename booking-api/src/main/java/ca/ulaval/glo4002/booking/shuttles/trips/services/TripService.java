package ca.ulaval.glo4002.booking.shuttles.trips.services;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.passes.domain.Pass;
import ca.ulaval.glo4002.booking.passes.domain.PassCategories;
import ca.ulaval.glo4002.booking.passes.domain.PassNumber;
import ca.ulaval.glo4002.booking.program.artists.domain.Artist;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.shuttles.domain.Passenger;
import ca.ulaval.glo4002.booking.shuttles.domain.ShuttleCategories;
import ca.ulaval.glo4002.booking.shuttles.domain.ShuttleFactory;
import ca.ulaval.glo4002.booking.shuttles.trips.infrastructure.TripRepository;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

public class TripService {

	private final FestivalConfiguration festivalConfiguration;
	private final TripRepository repository;
	private final ShuttleFactory factory;

	@Inject
	public TripService(FestivalConfiguration festivalConfiguration, TripRepository repository, ShuttleFactory factory) {
		this.festivalConfiguration = festivalConfiguration;
		this.repository = repository;
		this.factory = factory;
	}

	public void orderForArtist(Artist artist, EventDate tripDate) {
		ShuttleCategories shuttleCategory;

		if (artist.getNumberOfPeople() > 1) {
			shuttleCategory = ShuttleCategories.MILLENNIUM_FALCON;
		} else {
			shuttleCategory = ShuttleCategories.ET_SPACESHIP;
		}

		PassNumber passengerNumber = new PassNumber(artist.getId().longValue());
		List<Passenger> passengers = Collections.nCopies(artist.getNumberOfPeople(), new Passenger(passengerNumber));

		repository.addPassengersToNewArrival(passengers, shuttleCategory, tripDate);
		repository.addPassengersToNewDeparture(passengers, shuttleCategory, tripDate);
	}

	public void orderForPasses(PassCategories passCategory, List<Pass> passes) {
		ShuttleCategories shuttleCategory = factory.createCategory(passCategory);

		passes.forEach(pass -> {
			Passenger passenger = new Passenger(pass.getPassNumber());

			if (pass.getEventDate() == null) {
				orderForFullFestival(passenger, shuttleCategory);
			} else {
				orderForEventDate(passenger, shuttleCategory, pass.getEventDate());
			}
		});
	}

	private void orderForEventDate(Passenger passenger, ShuttleCategories category, EventDate tripDate) {
		repository.addPassengerToArrivals(passenger, category, tripDate);
		repository.addPassengerToDepartures(passenger, category, tripDate);
	}

	private void orderForFullFestival(Passenger passenger, ShuttleCategories category) {
		EventDate startDate = festivalConfiguration.getStartEventDate();
		EventDate endDate = festivalConfiguration.getEndEventDate();

		repository.addPassengerToArrivals(passenger, category, startDate);
		repository.addPassengerToDepartures(passenger, category, endDate);
	}
}
