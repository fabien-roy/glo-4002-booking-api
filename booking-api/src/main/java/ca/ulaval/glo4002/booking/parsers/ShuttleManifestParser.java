package ca.ulaval.glo4002.booking.parsers;

import java.util.List;

import ca.ulaval.glo4002.booking.domainobjects.shuttles.ShuttleManifest;
import ca.ulaval.glo4002.booking.dto.ShuttleManifestDto;
import ca.ulaval.glo4002.booking.dto.TripDto;
import ca.ulaval.glo4002.booking.exceptions.UnusedMethodException;

public class ShuttleManifestParser implements DtoParser<ShuttleManifest, ShuttleManifestDto> {

	private TripParser tripParser = new TripParser();

	@Override
	public ShuttleManifest parseDto(ShuttleManifestDto dto) {
	    throw new UnusedMethodException();
	}

	@Override
	public ShuttleManifestDto toDto(ShuttleManifest shuttleManifest) {
		List<TripDto> arrivalDtos = tripParser.toDtos(shuttleManifest.getArrivals());
		List<TripDto> departureDtos = tripParser.toDtos(shuttleManifest.getDepartures());	
		
	    ShuttleManifestDto shuttleManifestDto = new ShuttleManifestDto();
	    shuttleManifestDto.arrivals = arrivalDtos;
	    shuttleManifestDto.departures = departureDtos;
	    
	    return shuttleManifestDto;
	}
}
