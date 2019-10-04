package ca.ulaval.glo4002.booking.exceptions;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VendorNotFoundException extends HumanReadableException {

    public VendorNotFoundException(String vendorCode){
        super(ExceptionConstants.Vendor.NOT_FOUND_ERROR);

        error = ExceptionConstants.Vendor.NOT_FOUND_ERROR;
        description = ExceptionConstants.Vendor.NOT_FOUND_DESCRIPTION.replace("{vendorCode}", vendorCode);
        httpStatus = HttpStatus.NOT_FOUND;
    }
}
