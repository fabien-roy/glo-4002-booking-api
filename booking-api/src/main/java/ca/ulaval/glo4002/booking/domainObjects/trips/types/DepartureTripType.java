package ca.ulaval.glo4002.booking.domainObjects.trips.types;

import ca.ulaval.glo4002.booking.constants.TripConstants;

public class DepartureTripType extends TripType {

    public DepartureTripType() {
        this.id = TripConstants.Types.DEPARTURE_ID;
        this.name = TripConstants.Types.DEPARTURE_NAME;
    }
}
