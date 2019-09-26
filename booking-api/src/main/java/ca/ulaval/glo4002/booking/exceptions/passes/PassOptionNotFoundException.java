package ca.ulaval.glo4002.booking.exceptions.passes;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;

public class PassOptionNotFoundException extends FestivalException {

    public PassOptionNotFoundException() {
        super(ExceptionConstants.PASS_OPTION_NOT_FOUND_MESSAGE);
    }
}
