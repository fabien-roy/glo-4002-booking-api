package ca.ulaval.glo4002.booking.domainobjects.oxygen.categories;

import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.productions.OxygenProduction;
import ca.ulaval.glo4002.booking.domainobjects.qualities.SupernovaQuality;

public class EOxygenCategory extends OxygenCategory {

    public EOxygenCategory(OxygenProduction production) {
        this.id = OxygenConstants.Categories.E_ID;
        this.name = OxygenConstants.Categories.E_NAME;
        this.quality = new SupernovaQuality();
        this.production = production;
    }
}
