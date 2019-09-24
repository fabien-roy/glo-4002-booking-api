package ca.ulaval.glo4002.booking.exceptions.passes;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;

public class PassOptionNotFoundException extends RuntimeException {

    public PassOptionNotFoundException() {
        super(ExceptionConstants.PASS_OPTION_NOT_FOUND_MESSAGE);
    }
}
