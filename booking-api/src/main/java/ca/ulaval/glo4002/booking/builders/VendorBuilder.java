package ca.ulaval.glo4002.booking.builders;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.constants.VendorConstants;
import ca.ulaval.glo4002.booking.domainObjects.vendors.TeamVendor;
import ca.ulaval.glo4002.booking.domainObjects.vendors.Vendor;
import ca.ulaval.glo4002.booking.exceptions.VendorNotFoundException;

public class VendorBuilder implements Builder<Vendor> {

    public Vendor buildById(Long id) {
        if(id.equals(VendorConstants.TEAM_VENDOR_ID)){
            return buildTeamVendor();
        } else {
            throw new VendorNotFoundException();
        }
    }

    public Vendor buildByCode(String vendorCode) {
        if(vendorCode.equals(VendorConstants.TEAM_VENDOR_CODE)) {
            return buildTeamVendor();
        } else {
            throw new VendorNotFoundException();
        }
    }

    @Override
    public Vendor buildByName(String name) {
        throw new IllegalStateException(ExceptionConstants.UNUSED_METHOD_MESSAGE);
    }

    private Vendor buildTeamVendor() {
        return new TeamVendor();
    }
}
