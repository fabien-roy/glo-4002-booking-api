package ca.ulaval.glo4002.booking.parsers;

import java.util.List;

import ca.ulaval.glo4002.booking.builders.shuttles.ShuttleCategoryBuilder;
import ca.ulaval.glo4002.booking.domainObjects.shuttles.ShuttleManifest;
import ca.ulaval.glo4002.booking.dto.ShuttleManifestDto;
import ca.ulaval.glo4002.booking.entities.ShuttleEntity;

public class ShuttleManifestParser implements Parser<List<ShuttleManifest>, ShuttleManifestDto, ShuttleEntity> {
	
	private ShuttleCategoryBuilder shuttleCategoryBuilder = new ShuttleCategoryBuilder();

	@Override
	public List<ShuttleManifest> parseDto(ShuttleManifestDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShuttleManifest> parseEntity(ShuttleEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShuttleManifestDto toDto(List<ShuttleManifest> object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShuttleEntity toEntity(List<ShuttleManifest> object) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
