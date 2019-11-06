package ca.ulaval.glo4002.booking.exceptions.oxygen;

import ca.ulaval.glo4002.booking.enums.OxygenCategories;
import ca.ulaval.glo4002.booking.exceptions.BookingException;

public class InvalidOxygenCategoryException extends BookingException {

    public InvalidOxygenCategoryException(OxygenCategories category) {
        super("INVALID_OXYGEN_CATEGORY");

        description = category + " is not a valid oxygen category!";
    }
    
    public InvalidOxygenCategoryException(String category) {
    	super("INVALID_OXYGEN_CATEGORY");
    	
    	description = category + " is not a valid oxygen category!";
    }
}
