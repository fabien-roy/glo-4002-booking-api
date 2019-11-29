package ca.ulaval.glo4002.booking.shuttles.trips;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import ca.ulaval.glo4002.booking.festival.domain.Festival;
import ca.ulaval.glo4002.booking.numbers.Number;
import ca.ulaval.glo4002.booking.program.artists.domain.BookingArtist;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.passes.domain.Pass;
import ca.ulaval.glo4002.booking.passes.domain.PassCategories;
import ca.ulaval.glo4002.booking.shuttles.Passenger;
import ca.ulaval.glo4002.booking.shuttles.ShuttleCategories;
import ca.ulaval.glo4002.booking.shuttles.ShuttleFactory;

public class TripService {

	private final Festival festival;
	private final TripRepository repository;
	private final ShuttleFactory factory;

	@Inject
	public TripService(Festival festival, TripRepository repository, ShuttleFactory factory) {
		this.festival = festival;
		this.repository = repository;
		this.factory = factory;
	}

	public void orderForArtist(BookingArtist artist, EventDate tripDate) {
		ShuttleCategories shuttleCategory;

		if (artist.getNumberOfPeople() > 1) {
			shuttleCategory = ShuttleCategories.MILLENNIUM_FALCON;
		} else {
			shuttleCategory = ShuttleCategories.ET_SPACESHIP;
		}

		Number passengerNumber = new Number(artist.getId().longValue());
		List<Passenger> passengers = Collections.nCopies(artist.getNumberOfPeople(), new Passenger(passengerNumber));

		repository.addPassengersToNewArrival(passengers, shuttleCategory, tripDate);
		repository.addPassengersToNewDeparture(passengers, shuttleCategory, tripDate);
	}

	public void orderForPasses(PassCategories passCategory, List<Pass> passes) {
		ShuttleCategories shuttleCategory = factory.buildCategory(passCategory);

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
		EventDate startDate = festival.getStartEventDate();
		EventDate endDate = festival.getEndEventDate();

		repository.addPassengerToArrivals(passenger, category, startDate);
		repository.addPassengerToDepartures(passenger, category, endDate);
	}
}
