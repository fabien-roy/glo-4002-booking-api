package ca.ulaval.glo4002.booking.entities.shuttles.categories;

import ca.ulaval.glo4002.booking.constants.ShuttleConstants;

public class EtSpaceshipShuttleCategory extends ShuttleCategory{

    public EtSpaceshipShuttleCategory() {
        this.id = ShuttleConstants.Categories.ET_SPACESHIP_ID;
        this.name = ShuttleConstants.Categories.ET_SPACESHIP_NAME;
        this.maxCapacity = ShuttleConstants.Categories.ET_SPACESHIP_MAX_CAPACITY;
        this.price = ShuttleConstants.Categories.ET_SPACESHIP_PRICE;
    }
}
