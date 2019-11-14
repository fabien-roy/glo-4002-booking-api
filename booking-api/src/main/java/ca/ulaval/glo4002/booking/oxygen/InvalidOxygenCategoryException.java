package ca.ulaval.glo4002.booking.oxygen;

import ca.ulaval.glo4002.booking.exceptions.BookingException;

// TODO : Remove, should use InvalidFormatException
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
