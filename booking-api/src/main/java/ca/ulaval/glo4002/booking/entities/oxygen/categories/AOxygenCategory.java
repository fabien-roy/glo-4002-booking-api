package ca.ulaval.glo4002.booking.entities.oxygen.categories;

import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.entities.oxygen.productions.OxygenProduction;

public class AOxygenCategory extends OxygenCategory {

    public AOxygenCategory(OxygenProduction production) {
        this.id = OxygenConstants.Categories.A_ID;
        this.name = OxygenConstants.Categories.A_NAME;
        this.production = production;
    }
}
