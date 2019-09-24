package ca.ulaval.glo4002.booking.entities.shuttles.types;

import ca.ulaval.glo4002.booking.constants.ShuttleConstants;

public class DepartureShuttleType extends ShuttleType {

    public DepartureShuttleType() {
        this.id = ShuttleConstants.Types.DEPARTURE_ID;
        this.name = ShuttleConstants.Types.DEPARTURE_NAME;
    }
}
