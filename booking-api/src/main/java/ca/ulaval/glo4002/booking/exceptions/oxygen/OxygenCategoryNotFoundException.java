package ca.ulaval.glo4002.booking.exceptions.oxygen;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OxygenCategoryNotFoundException extends FestivalException {

    public OxygenCategoryNotFoundException() {
        super(ExceptionConstants.OXYGEN_CATEGORY_NOT_FOUND_MESSAGE);
    }
}
