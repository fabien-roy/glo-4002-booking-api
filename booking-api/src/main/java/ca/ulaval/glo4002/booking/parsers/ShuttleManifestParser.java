package ca.ulaval.glo4002.booking.parsers;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.domainobjects.shuttles.ShuttleManifest;
import ca.ulaval.glo4002.booking.dto.ShuttleManifestDto;
import ca.ulaval.glo4002.booking.dto.ShuttleDto;

public class ShuttleManifestParser implements ToDtoParser<ShuttleManifest, ShuttleManifestDto> {

	private ShuttleParser shuttleParser = new ShuttleParser();

	@Override
	public ShuttleManifestDto toDto(ShuttleManifest shuttleManifest) {
		List<ShuttleDto> arrivalDtos = new ArrayList<>();
		List<ShuttleDto> departureDtos = new ArrayList<>();
		shuttleManifest.getArrivals().forEach(shuttle -> arrivalDtos.add(shuttleParser.toDto(shuttle)));
		shuttleManifest.getDepartures().forEach(shuttle -> departureDtos.add(shuttleParser.toDto(shuttle)));

	    ShuttleManifestDto shuttleManifestDto = new ShuttleManifestDto();
	    shuttleManifestDto.arrivals = arrivalDtos;
	    shuttleManifestDto.departures = departureDtos;
	    
	    return shuttleManifestDto;
	}
}
