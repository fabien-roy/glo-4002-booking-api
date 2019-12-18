package ca.ulaval.glo4002.booking.shuttles.trips.services;

import ca.ulaval.glo4002.booking.passes.domain.Pass;
import ca.ulaval.glo4002.booking.passes.domain.PassCategories;
import ca.ulaval.glo4002.booking.program.artists.domain.Artist;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.shuttles.domain.Passenger;
import ca.ulaval.glo4002.booking.shuttles.domain.ShuttleCategories;
import ca.ulaval.glo4002.booking.shuttles.trips.domain.TripRepository;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

public class TripService {

	private final TripRepository repository;

	@Inject
	public TripService(TripRepository repository) {
		this.repository = repository;
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
			ShuttleCategories category = getShuttleCategoryForPassCategory(pass.getCategory());
			Passenger passenger = new Passenger(pass.getNumber().getValue());

			repository.addPassengerToArrivals(passenger, category, pass.getArrivalDate());
			repository.addPassengerToDepartures(passenger, category, pass.getDepartureDate());
		});
	}

	private ShuttleCategories getShuttleCategoryForPassCategory(PassCategories passCategory) {
		switch(passCategory) {
			case SUPERNOVA:
				return ShuttleCategories.ET_SPACESHIP;
			case SUPERGIANT:
				return ShuttleCategories.MILLENNIUM_FALCON;
			default:
			case NEBULA:
				return ShuttleCategories.SPACE_X;
		}
	}
}
