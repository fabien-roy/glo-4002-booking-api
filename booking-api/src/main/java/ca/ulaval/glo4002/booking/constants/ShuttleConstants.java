package ca.ulaval.glo4002.booking.constants;

public class ShuttleConstants {

    public static class Categories {

        public static final Long ET_SPACESHIP_ID = 0L;
        public static final Long MILLENNIUM_FALCON_ID = 1L;
        public static final Long SPACE_X_ID = 2L;

        public static final String ET_SPACESHIP_NAME = "ET Spaceship";
        public static final String MILLENNIUM_FALCON_NAME = "Millennium Falcon";
        public static final String SPACE_X_NAME = "SpaceX";

        public static final Integer ET_SPACESHIP_MAX_CAPACITY = 1;
        public static final Integer MILLENNIUM_FALCON_MAX_CAPACITY = 20;
        public static final Integer SPACE_X_MAX_CAPACITY = 30;

        public static final Double ET_SPACESHIP_PRICE = 100000.0;
        public static final Double MILLENNIUM_FALCON_PRICE = 65000.0;
        public static final Double SPACE_X_PRICE = 30000.0;

        private Categories(){
            throw new IllegalStateException(ExceptionConstants.UTILITY_CLASS_ERROR);
        }
    }

    private ShuttleConstants(){
        throw new IllegalStateException(ExceptionConstants.UTILITY_CLASS_ERROR);
    }
}
