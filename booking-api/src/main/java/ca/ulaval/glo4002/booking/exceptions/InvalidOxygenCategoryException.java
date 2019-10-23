package ca.ulaval.glo4002.booking.exceptions;

//TODO : maybe extends another class ? We begin to have Exception bloating like in the first project maybe a better ways to do it
public class InvalidOxygenCategoryException extends genericException {

    public InvalidOxygenCategoryException(String exception) {
        super(exception);
    }
}
