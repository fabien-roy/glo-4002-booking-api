package ca.ulaval.glo4002.booking.shuttles.manifest;

import ca.ulaval.glo4002.booking.shuttles.trips.Trip;
import ca.ulaval.glo4002.booking.shuttles.trips.TripDto;
import ca.ulaval.glo4002.booking.shuttles.trips.TripMapper;

import java.util.List;

import javax.inject.Inject;

public class ShuttleManifestMapper {
	
	private final TripMapper tripMapper;
	
	@Inject
	public ShuttleManifestMapper(TripMapper tripMapper) {
		this.tripMapper = tripMapper;
	}
	
	public ShuttleManifestDto toDto(List<Trip> arrivals, List<Trip> departures) {
		List<TripDto> arrivalDtos = tripMapper.toDto(arrivals);
		List<TripDto> departureDtos = tripMapper.toDto(departures);
		
		return new ShuttleManifestDto(arrivalDtos, departureDtos);
	}
}
