package ca.ulaval.glo4002.booking.domainobjects.oxygen.categories;

import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.productions.OxygenProduction;
import ca.ulaval.glo4002.booking.domainobjects.qualities.NebulaQuality;

public class AOxygenCategory extends OxygenCategory {

    public AOxygenCategory(OxygenProduction production) {
        this.id = OxygenConstants.Categories.A_ID;
        this.name = OxygenConstants.Categories.A_NAME;
        this.quality = new NebulaQuality();
        this.production = production;
    }
}
