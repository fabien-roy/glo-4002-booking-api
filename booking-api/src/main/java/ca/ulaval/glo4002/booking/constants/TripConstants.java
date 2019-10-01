package ca.ulaval.glo4002.booking.constants;

public class TripConstants {
    public static class Types {
        public static final Long DEPARTURE_ID = 0L;
        public static final Long ARRIVAL_ID = 1L;

        public static final String DEPARTURE_NAME = "DepartureTrip";
        public static final String ARRIVAL_NAME = "ArrivalTrip";

        private Types(){
            throw new IllegalStateException(ExceptionConstants.UTILITY_CLASS_MESSAGE);
        }
    }

    private TripConstants(){
        throw new IllegalStateException(ExceptionConstants.UTILITY_CLASS_MESSAGE);
    }
}
