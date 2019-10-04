package ca.ulaval.glo4002.booking.exceptions.report;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.HumanReadableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InventoryNotFoundException extends HumanReadableException {

    public InventoryNotFoundException(String inventoryNumber) {
        super(ExceptionConstants.Inventory.NOT_FOUND_ERROR);

        error = ExceptionConstants.Inventory.NOT_FOUND_ERROR;
        description = ExceptionConstants.Inventory.NOT_FOUND_DESCRIPTION.replace("{inventoryNumber", inventoryNumber);
        httpStatus = HttpStatus.NOT_FOUND;
    }
}
