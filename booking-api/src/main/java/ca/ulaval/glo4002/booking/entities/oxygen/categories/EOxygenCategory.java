package ca.ulaval.glo4002.booking.entities.oxygen.categories;

import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.entities.oxygen.productions.OxygenProduction;
import ca.ulaval.glo4002.booking.entities.qualities.SupernovaQuality;

public class EOxygenCategory extends OxygenCategory {

    public EOxygenCategory(OxygenProduction production) {
        this.id = OxygenConstants.Categories.E_ID;
        this.name = OxygenConstants.Categories.E_NAME;
        this.quality = new SupernovaQuality();
        this.production = production;
    }
}
