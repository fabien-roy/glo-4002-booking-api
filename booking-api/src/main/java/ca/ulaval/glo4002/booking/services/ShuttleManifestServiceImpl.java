package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.shuttles.ShuttleManifest;
import ca.ulaval.glo4002.booking.domainobjects.trips.ArrivalTrip;
import ca.ulaval.glo4002.booking.domainobjects.trips.DepartureTrip;
import ca.ulaval.glo4002.booking.domainobjects.trips.Trip;
import ca.ulaval.glo4002.booking.exceptions.shuttles.ShuttleManifestInvalidDateException;
import ca.ulaval.glo4002.booking.parsers.TripParser;
import ca.ulaval.glo4002.booking.repositories.TripRepository;
import ca.ulaval.glo4002.booking.util.FestivalDateUtil;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShuttleManifestServiceImpl implements ShuttleManifestService {

	@Resource
	private final TripRepository tripRepository;
	private final TripParser tripParser;

	public ShuttleManifestServiceImpl(TripRepository tripRepository) {
		this.tripRepository = tripRepository;
		this.tripParser = new TripParser();
	}

	@Override
	public ShuttleManifest findByDate(LocalDate date) {
	    validateManifestDate(date);

		List<DepartureTrip> departures = new ArrayList<>();
		List<ArrivalTrip> arrivals = new ArrayList<>();

		tripRepository.findAll().forEach(tripEntity -> {
			Trip trip = tripParser.parseEntity(tripEntity);

			if (trip.getDate().equals(date)) {
				if (trip instanceof DepartureTrip) {
					departures.add((DepartureTrip) trip);
				} else if (trip instanceof ArrivalTrip) {
					arrivals.add((ArrivalTrip) trip);
				}
			}
		});

		return new ShuttleManifest(date, departures, arrivals);
	}

	private void validateManifestDate(LocalDate manifestDate){
		if (FestivalDateUtil.isOutsideFestivalDates(manifestDate)){
			throw new ShuttleManifestInvalidDateException();
		}
	}
}
