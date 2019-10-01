package ca.ulaval.glo4002.booking.domainobjects.passes.categories;

import ca.ulaval.glo4002.booking.constants.PassConstants;
import ca.ulaval.glo4002.booking.domainobjects.passes.options.PassOption;
import ca.ulaval.glo4002.booking.domainobjects.qualities.SupergiantQuality;

import java.util.Map;

public class SupergiantPassCategory extends PassCategory {

	public SupergiantPassCategory(Map<PassOption, Double> pricePerOption) {
	    this.id = PassConstants.Categories.SUPERGIANT_ID;
	    this.name = PassConstants.Categories.SUPERGIANT_NAME;
		this.quality = new SupergiantQuality();
		this.pricePerOption = pricePerOption;
    }
}
