package ca.ulaval.glo4002.booking.constants;

public class RepositoryConstants {

    public static final String PASS_FIND_ALL_QUERY = "SELECT Id FROM PASS Id";

    private RepositoryConstants(){
        throw new IllegalStateException(ExceptionConstants.UTILITY_CLASS_MESSAGE);
    }
}
