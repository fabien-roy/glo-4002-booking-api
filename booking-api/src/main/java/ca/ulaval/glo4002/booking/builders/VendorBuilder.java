package ca.ulaval.glo4002.booking.builders;

import ca.ulaval.glo4002.booking.constants.VendorConstants;
import ca.ulaval.glo4002.booking.entities.vendors.TeamVendor;
import ca.ulaval.glo4002.booking.entities.vendors.Vendor;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;
import ca.ulaval.glo4002.booking.exceptions.VendorNotFoundException;

public class VendorBuilder implements Builder {

    public Vendor buildById(Long id) {
        if(id.equals(VendorConstants.TEAM_VENDOR_ID)){
            return buildTeamVendor();
        } else {
            throw new VendorNotFoundException();
        }
    }

    public Vendor buildByVendorCode(String vendorCode) {
        if(vendorCode.equals(VendorConstants.TEAM_VENDOR_CODE)) {
            return buildTeamVendor();
        } else {
            throw new VendorNotFoundException();
        }
    }

    @Override
    public Object buildByName(String name) {
        // This has to throw something, since it must not be called
        throw new FestivalException();
    }

    private Vendor buildTeamVendor() {
        return new TeamVendor();
    }
}
