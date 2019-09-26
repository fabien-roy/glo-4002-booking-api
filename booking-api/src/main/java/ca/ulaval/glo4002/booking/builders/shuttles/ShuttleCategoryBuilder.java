package ca.ulaval.glo4002.booking.builders.shuttles;

import ca.ulaval.glo4002.booking.builders.Builder;
import ca.ulaval.glo4002.booking.constants.ShuttleConstants;
import ca.ulaval.glo4002.booking.entities.shuttles.categories.EtSpaceshipShuttleCategory;
import ca.ulaval.glo4002.booking.entities.shuttles.categories.MillenniumFalconShuttleCategory;
import ca.ulaval.glo4002.booking.entities.shuttles.categories.ShuttleCategory;
import ca.ulaval.glo4002.booking.entities.shuttles.categories.SpaceXShuttleCategory;
import ca.ulaval.glo4002.booking.exceptions.shuttles.ShuttleCategoryNotFoundException;

public class ShuttleCategoryBuilder implements Builder<ShuttleCategory> {

    public ShuttleCategory buildById(Long id) {
        if (id.equals(ShuttleConstants.Categories.ET_SPACESHIP_ID)) {
            return buildEtSpaceshipShuttleCategory();
        } else if (id.equals(ShuttleConstants.Categories.MILLENNIUM_FALCON_ID)) {
            return buildMillenniumFalconShuttleCategory();
        } else if (id.equals(ShuttleConstants.Categories.SPACE_X_ID)) {
            return buildSpaceXShuttleCategory();
        } else {
            throw new ShuttleCategoryNotFoundException();
        }
    }

    public ShuttleCategory buildByName(String name) {
        if (name.equals(ShuttleConstants.Categories.ET_SPACESHIP_NAME)) {
            return buildEtSpaceshipShuttleCategory();
        } else if (name.equals(ShuttleConstants.Categories.MILLENNIUM_FALCON_NAME)) {
            return buildMillenniumFalconShuttleCategory();
        } else if (name.equals(ShuttleConstants.Categories.SPACE_X_NAME)) {
            return buildSpaceXShuttleCategory();
        } else {
            throw new ShuttleCategoryNotFoundException();
        }
    }

    private ShuttleCategory buildEtSpaceshipShuttleCategory() {
        return new EtSpaceshipShuttleCategory();
    }

    private ShuttleCategory buildMillenniumFalconShuttleCategory() {
        return new MillenniumFalconShuttleCategory();
    }

    private ShuttleCategory buildSpaceXShuttleCategory() {
        return new SpaceXShuttleCategory();
    }
}
