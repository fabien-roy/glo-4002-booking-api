package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.builders.oxygen.OxygenCategoryBuilder;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.categories.OxygenCategory;
import ca.ulaval.glo4002.booking.entities.OxygenTankEntity;

public class OxygenTankParser implements EntityParser<OxygenTank, OxygenTankEntity> {

	private OxygenCategoryBuilder oxygenCategoryBuilder = new OxygenCategoryBuilder();

	@Override
	public OxygenTank parseEntity(OxygenTankEntity entity) {
		OxygenCategory category = oxygenCategoryBuilder.buildById(entity.getCategoryId());

		return new OxygenTank(entity.getId(), category, entity.getRequestDate());
	}

	@Override
	public OxygenTankEntity toEntity(OxygenTank tank) {
		return new OxygenTankEntity(tank.getId(), tank.getCategory().getId(), tank.getRequestDate());
	}
}
