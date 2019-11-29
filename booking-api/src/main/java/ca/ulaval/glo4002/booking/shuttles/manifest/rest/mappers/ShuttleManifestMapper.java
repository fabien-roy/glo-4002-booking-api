package ca.ulaval.glo4002.booking.shuttles.manifest.rest.mappers;

import ca.ulaval.glo4002.booking.shuttles.manifest.rest.ShuttleManifestResponse;
import ca.ulaval.glo4002.booking.shuttles.trips.domain.Trip;
import ca.ulaval.glo4002.booking.shuttles.trips.rest.TripResponse;
import ca.ulaval.glo4002.booking.shuttles.trips.rest.mappers.TripMapper;

import java.util.List;

import javax.inject.Inject;

public class ShuttleManifestMapper {
	
	private final TripMapper tripMapper;
	
	@Inject
	public ShuttleManifestMapper(TripMapper tripMapper) {
		this.tripMapper = tripMapper;
	}
	
	public ShuttleManifestResponse toResponse(List<Trip> arrivals, List<Trip> departures) {
		List<TripResponse> arrivalDtos = tripMapper.toResponse(arrivals);
		List<TripResponse> departureDtos = tripMapper.toResponse(departures);
		
		return new ShuttleManifestResponse(arrivalDtos, departureDtos);
	}
}
