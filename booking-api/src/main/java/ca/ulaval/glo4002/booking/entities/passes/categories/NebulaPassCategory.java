package ca.ulaval.glo4002.booking.entities.passes.categories;

import ca.ulaval.glo4002.booking.constants.PassConstants;
import ca.ulaval.glo4002.booking.entities.oxygen.categories.OxygenCategory;
import ca.ulaval.glo4002.booking.entities.passes.options.PassOption;
import ca.ulaval.glo4002.booking.entities.shuttles.ShuttleCategory;

import java.util.HashMap;

public class NebulaPassCategory extends PassCategory {

	public NebulaPassCategory(HashMap<PassOption, Double> pricePerOption, ShuttleCategory shuttleCategory, OxygenCategory oxygenCategory) {
	    this.id = PassConstants.Categories.NEBULA_ID;
	    this.name = PassConstants.Categories.NEBULA_NAME;
		this.pricePerOption = pricePerOption;
		this.shuttleCategory = shuttleCategory;
		this.oxygenCategory = oxygenCategory;
    }
}
