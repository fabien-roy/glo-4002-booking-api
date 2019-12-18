package ca.ulaval.glo4002.booking.shuttles.trips.services;

import ca.ulaval.glo4002.booking.passes.domain.Pass;
import ca.ulaval.glo4002.booking.program.artists.domain.Artist;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.shuttles.domain.Passenger;
import ca.ulaval.glo4002.booking.shuttles.domain.ShuttleCategories;
import ca.ulaval.glo4002.booking.shuttles.domain.ShuttleFactory;
import ca.ulaval.glo4002.booking.shuttles.trips.domain.TripRepository;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

public class TripService {

	private final TripRepository repository;
	private final ShuttleFactory factory;

	@Inject
	public TripService(TripRepository repository, ShuttleFactory factory) {
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

		long passengerNumber = artist.getId().longValue();
		List<Passenger> passengers = Collections.nCopies(artist.getNumberOfPeople(), new Passenger(passengerNumber));

		repository.addPassengersToNewArrival(passengers, shuttleCategory, tripDate);
		repository.addPassengersToNewDeparture(passengers, shuttleCategory, tripDate);
	}

	public void orderForPasses(List<Pass> passes) {
		passes.forEach(pass -> {
			ShuttleCategories category = factory.createCategory(pass.getCategory()); // TODO : Use a mapper
			Passenger passenger = new Passenger(pass.getNumber());

			repository.addPassengerToArrivals(passenger, category, pass.getArrivalDate());
			repository.addPassengerToDepartures(passenger, category, pass.getDepartureDate());
		});
	}
}
