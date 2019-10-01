package ca.ulaval.glo4002.booking.domainobjects.shuttles.categories;

import ca.ulaval.glo4002.booking.constants.ShuttleConstants;
import ca.ulaval.glo4002.booking.domainobjects.qualities.NebulaQuality;

public class SpaceXShuttleCategory extends ShuttleCategory{

    public SpaceXShuttleCategory() {
        this.id = ShuttleConstants.Categories.SPACE_X_ID;
        this.name = ShuttleConstants.Categories.SPACE_X_NAME;
        this.quality = new NebulaQuality();
        this.maxCapacity = ShuttleConstants.Categories.SPACE_X_MAX_CAPACITY;
        this.price = ShuttleConstants.Categories.SPACE_X_PRICE;
    }
}
