package ca.ulaval.glo4002.booking.mappers;

import java.util.List;

import javax.inject.Inject;

import ca.ulaval.glo4002.booking.domain.shuttles.Trip;
import ca.ulaval.glo4002.booking.dto.shuttles.ShuttleManifestDto;
import ca.ulaval.glo4002.booking.dto.shuttles.TripDto;

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
