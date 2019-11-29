package ca.ulaval.glo4002.booking.shuttles.trips.rest.mappers;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.numbers.Number;
import ca.ulaval.glo4002.booking.shuttles.trips.domain.Trip;
import ca.ulaval.glo4002.booking.shuttles.trips.rest.TripDto;

public class TripMapper {

	public List<TripDto> toDto(List<Trip> trips) {
		List<TripDto> tripDtos = new ArrayList<>();

		for (Trip trip : trips) {
			String date = trip.getTripDate().toString();
			String shuttleName = trip.getShuttleCategory().toString();
			List<Long> passengers = new ArrayList<>();

			trip.getPassengersPassNumbers().stream().map(Number::getValue).forEach(passengers::add);

			TripDto tripDto = new TripDto(date, shuttleName, passengers);
			tripDtos.add(tripDto);
		}

		return tripDtos;
	}
}
