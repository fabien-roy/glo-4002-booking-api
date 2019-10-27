package ca.ulaval.glo4002.booking.mappers;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.trip.Trip;
import ca.ulaval.glo4002.booking.dto.TripDto;

public class TripMapper {
	
	public List<TripDto> toDto(List<Trip> trips) {
		
		List<TripDto> tripDtos = new ArrayList<>();
		for(Trip trip : trips) {
			String date = trip.getTripDate().toString();
			String shuttleName = trip.getShuttleName();
			List<Long> passengers = new ArrayList<>();
			
			trip.getPassengersPassNumbers()
			.stream()
			.map(Number::getValue)
			.forEach(passengers::add);
			
			tripDtos.add(new TripDto(date, shuttleName, passengers));
		}
		
		return tripDtos;
	}

}
