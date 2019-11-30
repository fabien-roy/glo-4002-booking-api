package ca.ulaval.glo4002.booking.shuttles.trips.rest.mappers;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.numbers.Number;
import ca.ulaval.glo4002.booking.shuttles.trips.domain.Trip;
import ca.ulaval.glo4002.booking.shuttles.trips.rest.TripResponse;
import org.apache.commons.lang.ArrayUtils;

public class TripMapper {

	public List<TripResponse> toResponse(List<Trip> trips) {
		List<TripResponse> tripResponses = new ArrayList<>();

		for (Trip trip : trips) {
			String date = trip.getTripDate().toString();
			String shuttleName = trip.getShuttleCategory().toString();
			List<Long> passengers = new ArrayList<>();

			trip.getPassengersPassNumbers().stream().map(Number::getValue).forEach(passengers::add);

			long[] passengersArray = ArrayUtils.toPrimitive(passengers.toArray(new Long[0]));

			TripResponse tripResponse = new TripResponse(date, shuttleName, passengersArray);
			tripResponses.add(tripResponse);
		}

		return tripResponses;
	}
}
