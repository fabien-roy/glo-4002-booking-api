package ca.ulaval.glo4002.booking.parsers;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.builders.shuttles.ShuttleCategoryBuilder;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.ShuttleManifest;
import ca.ulaval.glo4002.booking.dto.ShuttleManifestDto;
import ca.ulaval.glo4002.booking.dto.TripDto;
import ca.ulaval.glo4002.booking.exceptions.UnusedMethodException;

public class ShuttleManifestParser implements DtoParser<ShuttleManifest, ShuttleManifestDto> {
	
	private ShuttleCategoryBuilder shuttleCategoryBuilder = new ShuttleCategoryBuilder();
	private TripParser tripParser = new TripParser();

	@Override
	public ShuttleManifest parseDto(ShuttleManifestDto dto) {
	    throw new UnusedMethodException();
	}

	@Override
	public ShuttleManifestDto toDto(ShuttleManifest shuttleManifest) {
		List<TripDto> arrivalDtos = tripParser.toDtos(shuttleManifest.getArrivals());
		List<TripDto> departureDtos = tripParser.toDtos(shuttleManifest.getDepartures());
	    String date = shuttleManifest.getDate().toString();		
		
	    ShuttleManifestDto shuttleManifestDto = new ShuttleManifestDto();
	    shuttleManifestDto.arrivals = arrivalDtos;
	    shuttleManifestDto.departures = departureDtos;
	    shuttleManifestDto.date = date;
	    
	    return shuttleManifestDto;
	}
}
