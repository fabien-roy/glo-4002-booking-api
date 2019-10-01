package ca.ulaval.glo4002.booking.domainObjects.trips.types;

import ca.ulaval.glo4002.booking.constants.TripConstants;

public class ArrivalTripType extends TripType {

    public ArrivalTripType() {
        this.id = TripConstants.Types.ARRIVAL_ID;
        this.name = TripConstants.Types.ARRIVAL_NAME;
    }
}
