package ca.ulaval.glo4002.booking.entities.vendors;

import ca.ulaval.glo4002.booking.constants.VendorConstants;

public class TeamVendor extends Vendor {

    public TeamVendor() {
        this.id = VendorConstants.TEAM_VENDOR_ID;
        this.code = VendorConstants.TEAM_VENDOR_CODE;
    }
}
