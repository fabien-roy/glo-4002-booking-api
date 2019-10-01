package ca.ulaval.glo4002.booking.domainobjects.oxygen.categories;

import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.productions.OxygenProduction;
import ca.ulaval.glo4002.booking.domainobjects.qualities.SupergiantQuality;

public class BOxygenCategory extends OxygenCategory {

    public BOxygenCategory(OxygenProduction production) {
        this.id = OxygenConstants.Categories.B_ID;
        this.name = OxygenConstants.Categories.B_NAME;
        this.quality = new SupergiantQuality();
        this.production = production;
    }
}
