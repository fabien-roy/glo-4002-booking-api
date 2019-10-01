package ca.ulaval.glo4002.booking.domainobjects.passes.options;

import ca.ulaval.glo4002.booking.constants.PassConstants;

public class PackagePassOption extends PassOption {

	public PackagePassOption() {
		this.id = PassConstants.Options.PACKAGE_ID;
		this.name = PassConstants.Options.PACKAGE_NAME;
	}
}
