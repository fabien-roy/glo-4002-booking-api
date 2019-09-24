package ca.ulaval.glo4002.booking.entities.passes.categories;

import ca.ulaval.glo4002.booking.constants.PassConstants;
import ca.ulaval.glo4002.booking.entities.oxygen.categories.OxygenCategory;
import ca.ulaval.glo4002.booking.entities.passes.options.PassOption;
import ca.ulaval.glo4002.booking.entities.shuttles.categories.ShuttleCategory;

import java.util.HashMap;

public class SupernovaPassCategory extends PassCategory {

	public SupernovaPassCategory(HashMap<PassOption, Double> pricePerOption, ShuttleCategory shuttleCategory, OxygenCategory oxygenCategory) {
	    this.id = PassConstants.Categories.SUPERNOVA_ID;
	    this.name = PassConstants.Categories.SUPERNOVA_NAME;
		this.pricePerOption = pricePerOption;
		this.shuttleCategory = shuttleCategory;
		this.oxygenCategory = oxygenCategory;
    }
}
