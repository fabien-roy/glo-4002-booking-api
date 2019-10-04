package ca.ulaval.glo4002.booking.exceptions.report;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InventoryItemNotFoundException extends FestivalException {

    public InventoryItemNotFoundException() {
        super(ExceptionConstants.InventoryItem.NOT_FOUND_DESCRIPTION);

        error = ExceptionConstants.InventoryItem.NOT_FOUND_ERROR;
        description = ExceptionConstants.InventoryItem.NOT_FOUND_DESCRIPTION;
        httpStatus = HttpStatus.NOT_FOUND;
    }
}
