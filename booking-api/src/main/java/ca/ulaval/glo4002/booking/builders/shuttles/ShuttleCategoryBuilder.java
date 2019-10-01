package ca.ulaval.glo4002.booking.builders.shuttles;

import ca.ulaval.glo4002.booking.builders.Builder;
import ca.ulaval.glo4002.booking.constants.ShuttleConstants;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.categories.EtSpaceshipShuttleCategory;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.categories.MillenniumFalconShuttleCategory;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.categories.ShuttleCategory;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.categories.SpaceXShuttleCategory;
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
        switch (name) {
            case ShuttleConstants.Categories.ET_SPACESHIP_NAME:
                return buildEtSpaceshipShuttleCategory();
            case ShuttleConstants.Categories.MILLENNIUM_FALCON_NAME:
                return buildMillenniumFalconShuttleCategory();
            case ShuttleConstants.Categories.SPACE_X_NAME:
                return buildSpaceXShuttleCategory();
            default:
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
