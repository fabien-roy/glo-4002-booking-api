package ca.ulaval.glo4002.booking.domainobjects.passes.options;

import ca.ulaval.glo4002.booking.constants.PassConstants;

public class SinglePassOption extends PassOption {

	public SinglePassOption() {
		this.id = PassConstants.Options.SINGLE_ID;
		this.name = PassConstants.Options.SINGLE_NAME;
	}
}
