package ca.ulaval.glo4002.booking.parsers;

import java.util.ArrayList;

import ca.ulaval.glo4002.booking.builders.shuttles.ShuttleCategoryBuilder;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.entities.ShuttleEntity;

public class ShuttleParser implements EntityParser<Shuttle, ShuttleEntity> {
	
	private ShuttleCategoryBuilder shuttleCategoryBuilder = new ShuttleCategoryBuilder();
	

	@Override
	public Shuttle parseEntity(ShuttleEntity entity) {
		
		
		return new Shuttle(entity.getId(), entity.getPrice()
				, shuttleCategoryBuilder.buildById(entity.getShuttleCategoryId())
				, new ArrayList<>());
	}

	@Override
	public ShuttleEntity toEntity(Shuttle object) {
		return new ShuttleEntity(object.getId(), object.getShuttleCategory().getId()
				, object.getPrice());
	}
}
