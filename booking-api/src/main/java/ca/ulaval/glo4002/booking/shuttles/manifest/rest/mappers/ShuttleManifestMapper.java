package ca.ulaval.glo4002.booking.shuttles.manifest.rest.mappers;

import ca.ulaval.glo4002.booking.shuttles.manifest.rest.ShuttleManifestDto;
import ca.ulaval.glo4002.booking.shuttles.trips.domain.Trip;
import ca.ulaval.glo4002.booking.shuttles.trips.rest.TripDto;
import ca.ulaval.glo4002.booking.shuttles.trips.rest.mappers.TripMapper;

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
