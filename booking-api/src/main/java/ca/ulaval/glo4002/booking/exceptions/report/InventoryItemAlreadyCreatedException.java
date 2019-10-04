package ca.ulaval.glo4002.booking.exceptions.report;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InventoryItemAlreadyCreatedException extends FestivalException {

    public InventoryItemAlreadyCreatedException() {
        super((ExceptionConstants.InventoryItem.ALREADY_CREATED_ERROR));

        error = ExceptionConstants.InventoryItem.ALREADY_CREATED_ERROR;
        description = ExceptionConstants.InventoryItem.ALREADY_CREATED_DESCRIPTION;
        httpStatus = HttpStatus.BAD_REQUEST;
    }
}
