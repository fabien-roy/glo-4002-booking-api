package ca.ulaval.glo4002.booking.constants;

public class RepositoryConstants {

    public static final String PRODUCTION_PERSISTENCE_NAME = "noPersistence";
    public static final String STAGING_PERSISTENCE_NAME = "noPersistenceStaging";
    public static final String PASS_FIND_ALL_QUERY = "SELECT Id FROM Passes Id";
    public static final String ORDER_FIND_ALL_QUERY = "SELECT Id FROM Orders Id";

    private RepositoryConstants(){
        throw new IllegalStateException(ExceptionConstants.UTILITY_CLASS_MESSAGE);
    }
}
