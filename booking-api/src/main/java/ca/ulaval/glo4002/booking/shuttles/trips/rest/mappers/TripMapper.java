package ca.ulaval.glo4002.booking.shuttles.trips.rest.mappers;

import ca.ulaval.glo4002.booking.shuttles.trips.domain.Trip;
import ca.ulaval.glo4002.booking.shuttles.trips.rest.TripResponse;
import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class TripMapper {

	public List<TripResponse> toResponse(List<Trip> trips) {
		List<TripResponse> tripResponses = new ArrayList<>();

		for (Trip trip : trips) {
			String date = trip.getTripDate().toString();
			String shuttleName = trip.getShuttleCategory().toString();

			List<Long> passengers = new ArrayList<>(trip.getPassengerNumbers());

			long[] passengersArray = ArrayUtils.toPrimitive(passengers.toArray(new Long[0]));

			TripResponse tripResponse = new TripResponse(date, shuttleName, passengersArray);
			tripResponses.add(tripResponse);
		}

		return tripResponses;
	}
}
