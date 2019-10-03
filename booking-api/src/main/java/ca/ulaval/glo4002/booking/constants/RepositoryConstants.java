package ca.ulaval.glo4002.booking.constants;

public class RepositoryConstants {

    public static final String PRODUCTION_PERSISTENCE_NAME = "noPersistence";
    public static final String STAGING_PERSISTENCE_NAME = "noPersistenceStaging";
    public static final String PASS_FIND_ALL_QUERY = "SELECT Id FROM Passes Id";
    public static final String ORDER_FIND_ALL_QUERY = "SELECT Id FROM Orders Id";
    public static final String SHUTTLE_FIND_ALL_QUERY = "Select Id FROM Shuttles Id";
    public static final String TRIP_FIND_ALL_QUERY = "Select Id FROM Trips Id";
    public static final String INVENTORY_FIND_ALL_QUERY = "Select Id FROM Inventories Id";
    public static final String OXYGEN_TANK_FIND_ALL_QUERY = "Select Id FROM OxygenTanks Id";

    private RepositoryConstants(){
        throw new IllegalStateException(ExceptionConstants.UTILITY_CLASS_ERROR);
    }
}
