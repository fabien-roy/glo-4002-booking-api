package ca.ulaval.glo4002.booking.entities.passes.options;

import ca.ulaval.glo4002.booking.constants.PassConstants;

public class SinglePassPassOption extends PassOption {

	public SinglePassPassOption() {
		this.id = PassConstants.Options.SINGLE_PASS_ID;
		this.name = PassConstants.Options.SINGLE_PASS_NAME;
	}
}
