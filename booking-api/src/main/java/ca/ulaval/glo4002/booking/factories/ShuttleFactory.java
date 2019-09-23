package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.constants.ShuttleConstants;
import ca.ulaval.glo4002.booking.entities.shuttles.ShuttleCategory;
import ca.ulaval.glo4002.booking.entities.shuttles.ShuttleType;

public class ShuttleFactory {

    public ShuttleCategory getShuttleCategoryById(Long categoryId) {
        // TODO : I wanted to do a switch-case, but apparently static final isn't const?
        if (categoryId.equals(ShuttleConstants.Categories.ET_SPACESHIP_ID)) {
            return buildShuttleCategoryEtSpaceship();
        } else if (categoryId.equals(ShuttleConstants.Categories.MILLENNIUM_FALCON_ID)) {
            return buildShuttleCategoryMillenniumFalcon();
        } else if (categoryId.equals(ShuttleConstants.Categories.SPACE_X_ID)) {
            return buildShuttleCategorySpaceX();
        }

        return null; // TODO : Throw exception?
    }

    public ShuttleType getShuttleTypeById(Long typeId) {
        // TODO : I wanted to do a switch-case, but apparently static final isn't const?
        if (typeId.equals(ShuttleConstants.Types.DEPARTURE_ID)) {
            return buildShuttleTypeDeparture();
        } else if (typeId.equals(ShuttleConstants.Types.ARRIVAL_ID)) {
            return buildShuttleTypeArrival();
        }

        return null; // TODO : Throw exception?
    }

    private ShuttleCategory buildShuttleCategoryEtSpaceship() {
        return new ShuttleCategory(
                ShuttleConstants.Categories.ET_SPACESHIP_ID,
                ShuttleConstants.Categories.ET_SPACESHIP_NAME,
                ShuttleConstants.Categories.ET_SPACESHIP_MAX_CAPACITY,
                ShuttleConstants.Categories.ET_SPACESHIP_PRICE);
    }

    private ShuttleCategory buildShuttleCategoryMillenniumFalcon() {
        return new ShuttleCategory(
                ShuttleConstants.Categories.MILLENNIUM_FALCON_ID,
                ShuttleConstants.Categories.MILLENNIUM_FALCON_NAME,
                ShuttleConstants.Categories.MILLENNIUM_FALCON_MAX_CAPACITY,
                ShuttleConstants.Categories.MILLENNIUM_FALCON_PRICE);
    }

    private ShuttleCategory buildShuttleCategorySpaceX() {
        return new ShuttleCategory(
                ShuttleConstants.Categories.SPACE_X_ID,
                ShuttleConstants.Categories.SPACE_X_NAME,
                ShuttleConstants.Categories.SPACE_X_MAX_CAPACITY,
                ShuttleConstants.Categories.SPACE_X_PRICE);
    }

    private ShuttleType buildShuttleTypeDeparture() {
        return new ShuttleType(
                ShuttleConstants.Types.DEPARTURE_ID,
                ShuttleConstants.Types.DEPARTURE_NAME);
    }

    private ShuttleType buildShuttleTypeArrival() {
        return new ShuttleType(
                ShuttleConstants.Types.ARRIVAL_ID,
                ShuttleConstants.Types.ARRIVAL_NAME);
    }
}
