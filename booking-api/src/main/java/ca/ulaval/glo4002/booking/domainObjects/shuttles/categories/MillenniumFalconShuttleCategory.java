package ca.ulaval.glo4002.booking.domainObjects.shuttles.categories;

import ca.ulaval.glo4002.booking.constants.ShuttleConstants;
import ca.ulaval.glo4002.booking.domainObjects.qualities.SupergiantQuality;

public class MillenniumFalconShuttleCategory extends ShuttleCategory{

    public MillenniumFalconShuttleCategory() {
        this.id = ShuttleConstants.Categories.MILLENNIUM_FALCON_ID;
        this.name = ShuttleConstants.Categories.MILLENNIUM_FALCON_NAME;
        this.quality = new SupergiantQuality();
        this.maxCapacity = ShuttleConstants.Categories.MILLENNIUM_FALCON_MAX_CAPACITY;
        this.price = ShuttleConstants.Categories.MILLENNIUM_FALCON_PRICE;
    }
}
