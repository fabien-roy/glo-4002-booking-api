package ca.ulaval.glo4002.booking.entities.oxygen.categories;

import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.entities.oxygen.productions.OxygenProduction;
import ca.ulaval.glo4002.booking.entities.qualities.SupergiantQuality;

public class BOxygenCategory extends OxygenCategory {

    public BOxygenCategory(OxygenProduction production) {
        this.id = OxygenConstants.Categories.B_ID;
        this.name = OxygenConstants.Categories.B_NAME;
        this.quality = new SupergiantQuality();
        this.production = production;
    }
}
