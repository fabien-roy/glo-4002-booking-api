package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.builders.oxygen.OxygenCategoryBuilder;
import ca.ulaval.glo4002.booking.domainObjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domainObjects.oxygen.categories.OxygenCategory;
import ca.ulaval.glo4002.booking.entities.OxygenTankEntity;

public class OxygenTankParser implements EntityParser<OxygenTank, OxygenTankEntity> {

	private OxygenCategoryBuilder oxygenCategoryBuilder = new OxygenCategoryBuilder();

	@Override
	public OxygenTank parseEntity(OxygenTankEntity entity) {
		OxygenCategory category = oxygenCategoryBuilder.buildById(entity.categoryId);

		return new OxygenTank(category, entity.requestDate);
	}

	@Override
	public OxygenTankEntity toEntity(OxygenTank tank) {
		return new OxygenTankEntity(tank.getId(), tank.getOxygenTankCategory().getId(), tank.getTimeRequested());
	}
}
