package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.builders.shuttles.ShuttleCategoryBuilder;
import ca.ulaval.glo4002.booking.domainObjects.shuttles.ShuttleManifest;
import ca.ulaval.glo4002.booking.dto.ShuttleManifestDto;

public class ShuttleManifestParser implements DtoParser<ShuttleManifest, ShuttleManifestDto> {
	
	private ShuttleCategoryBuilder shuttleCategoryBuilder = new ShuttleCategoryBuilder();

	@Override
	public ShuttleManifest parseDto(ShuttleManifestDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShuttleManifestDto toDto(ShuttleManifest shuttleManifest) {
		// TODO Auto-generated method stub
		return null;
	}
}
