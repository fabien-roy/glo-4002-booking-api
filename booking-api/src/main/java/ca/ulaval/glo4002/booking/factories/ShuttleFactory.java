package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.constants.ShuttleConstants;
import ca.ulaval.glo4002.booking.entities.shuttles.categories.EtSpaceshipShuttleCategory;
import ca.ulaval.glo4002.booking.entities.shuttles.categories.MillenniumFalconShuttleCategory;
import ca.ulaval.glo4002.booking.entities.shuttles.categories.ShuttleCategory;
import ca.ulaval.glo4002.booking.entities.shuttles.categories.SpaceXShuttleCategory;
import ca.ulaval.glo4002.booking.entities.shuttles.types.ArrivalShuttleType;
import ca.ulaval.glo4002.booking.entities.shuttles.types.DepartureShuttleType;
import ca.ulaval.glo4002.booking.entities.shuttles.types.ShuttleType;

public class ShuttleFactory {

    public ShuttleCategory getShuttleCategoryById(Long categoryId) {
        // TODO : I wanted to do a switch-case, but apparently static final isn't const?
        if (categoryId.equals(ShuttleConstants.Categories.ET_SPACESHIP_ID)) {
            return buildEtSpaceshipShuttleCategory();
        } else if (categoryId.equals(ShuttleConstants.Categories.MILLENNIUM_FALCON_ID)) {
            return buildMillenniumFalconShuttleCategory();
        } else if (categoryId.equals(ShuttleConstants.Categories.SPACE_X_ID)) {
            return buildSpaceXShuttleCategory();
        }

        return null; // TODO : Throw exception?
    }

    public ShuttleType getShuttleTypeById(Long typeId) {
        // TODO : I wanted to do a switch-case, but apparently static final isn't const?
        if (typeId.equals(ShuttleConstants.Types.DEPARTURE_ID)) {
            return buildDepartureShuttleType();
        } else if (typeId.equals(ShuttleConstants.Types.ARRIVAL_ID)) {
            return buildArrivalShuttleType();
        }

        return null; // TODO : Throw exception?
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

    private ShuttleType buildDepartureShuttleType() {
        return new DepartureShuttleType();
    }

    private ShuttleType buildArrivalShuttleType() {
        return new ArrivalShuttleType();
    }
}
