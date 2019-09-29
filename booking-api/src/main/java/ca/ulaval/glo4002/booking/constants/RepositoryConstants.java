package ca.ulaval.glo4002.booking.constants;

public class RepositoryConstants {

    public static final String PASS_FIND_ALL_QUERY = "SELECT Id FROM Pass Id";
    public static final String ORDER_FIND_ALL_QUERY = "SELECT Id FROM Order Id";

    private RepositoryConstants(){
        throw new IllegalStateException(ExceptionConstants.UTILITY_CLASS_MESSAGE);
    }
}
