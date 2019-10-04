package ca.ulaval.glo4002.booking.exceptions.report;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.HumanReadableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InventoryAlreadyCreatedException extends HumanReadableException {

    public InventoryAlreadyCreatedException(String inventoryNumber){
        super(ExceptionConstants.Inventory.ALREADY_CREATED_ERROR);

        error = ExceptionConstants.Inventory.ALREADY_CREATED_ERROR;
        description = ExceptionConstants.Inventory.ALREADY_CREATED_DESCRIPTION.replace("{inventoryNumber}", inventoryNumber);
        httpStatus = HttpStatus.BAD_REQUEST;
    }
}
