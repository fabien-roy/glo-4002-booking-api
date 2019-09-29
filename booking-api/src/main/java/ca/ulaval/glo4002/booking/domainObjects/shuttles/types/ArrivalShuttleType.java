package ca.ulaval.glo4002.booking.domainObjects.shuttles.types;

import ca.ulaval.glo4002.booking.constants.ShuttleConstants;

public class ArrivalShuttleType extends ShuttleType {

    public ArrivalShuttleType() {
        this.id = ShuttleConstants.Types.ARRIVAL_ID;
        this.name = ShuttleConstants.Types.ARRIVAL_NAME;
    }
}
