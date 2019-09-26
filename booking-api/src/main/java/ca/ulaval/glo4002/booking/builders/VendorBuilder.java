package ca.ulaval.glo4002.booking.builders;

import ca.ulaval.glo4002.booking.constants.VendorConstants;
import ca.ulaval.glo4002.booking.entities.vendors.TeamVendor;
import ca.ulaval.glo4002.booking.entities.vendors.Vendor;
import ca.ulaval.glo4002.booking.exceptions.VendorNotFoundException;

public class VendorBuilder {

    public Vendor buildById(Long id){
        if(id.equals(VendorConstants.VendorCode.TEAM_VENDOR_ID)){
            return buildTeamVendor();
        }
        else{
            throw new VendorNotFoundException();
        }
    }

    public Vendor buildByVendorCode(String vendorCode) {
        if(vendorCode.equals(VendorConstants.VendorCode.TEAM_VENDOR_CODE)) {
            return buildTeamVendor();
        }
        else{
            throw new VendorNotFoundException();
        }
    }

    private Vendor buildTeamVendor(){ return new TeamVendor(); }
}
