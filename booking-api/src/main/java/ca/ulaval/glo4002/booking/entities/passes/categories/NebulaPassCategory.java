package ca.ulaval.glo4002.booking.entities.passes.categories;

import ca.ulaval.glo4002.booking.constants.PassConstants;
import ca.ulaval.glo4002.booking.entities.passes.options.PassOption;
import ca.ulaval.glo4002.booking.entities.qualities.NebulaQuality;

import java.util.Map;

public class NebulaPassCategory extends PassCategory {

	public NebulaPassCategory(Map<PassOption, Double> pricePerOption) {
	    this.id = PassConstants.Categories.NEBULA_ID;
	    this.name = PassConstants.Categories.NEBULA_NAME;
	    this.quality = new NebulaQuality();
		this.pricePerOption = pricePerOption;
    }
}
