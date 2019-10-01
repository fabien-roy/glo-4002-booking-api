package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.builders.shuttles.ShuttleCategoryBuilder;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.entities.ShuttleEntity;

public class ShuttleParser implements EntityParser<Shuttle, ShuttleEntity> {
	
	private ShuttleCategoryBuilder shuttleCategoryBuilder = new ShuttleCategoryBuilder();

	@Override
	public Shuttle parseEntity(ShuttleEntity entity) {
	    // TODO
		return null;
	}

	@Override
	public ShuttleEntity toEntity(Shuttle object) {
		// TODO
		return null;
	}
}
