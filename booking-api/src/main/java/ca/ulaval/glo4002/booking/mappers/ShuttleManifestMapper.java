package ca.ulaval.glo4002.booking.mappers;

import java.util.List;

import javax.inject.Inject;

import ca.ulaval.glo4002.booking.domain.shuttles.ShuttleManifest;
import ca.ulaval.glo4002.booking.dto.ShuttleManifestDto;
import ca.ulaval.glo4002.booking.dto.TripDto;

public class ShuttleManifestMapper {
	
	private final TripMapper tripMapper;
	
	@Inject
	public ShuttleManifestMapper(TripMapper tripMapper) {
		this.tripMapper = tripMapper;
	}
	
	public ShuttleManifestDto toDto(ShuttleManifest shuttleManifest) {
		List<TripDto> arrivals = tripMapper.toDto(shuttleManifest.getArrivals());
		List<TripDto> departures = tripMapper.toDto(shuttleManifest.getDepartures());
		
		return new ShuttleManifestDto(arrivals, departures);
	}

}
