package ca.ulaval.glo4002.booking.builders.shuttles;

import ca.ulaval.glo4002.booking.builders.Builder;
import ca.ulaval.glo4002.booking.constants.ShuttleConstants;
import ca.ulaval.glo4002.booking.entities.shuttles.types.ArrivalShuttleType;
import ca.ulaval.glo4002.booking.entities.shuttles.types.DepartureShuttleType;
import ca.ulaval.glo4002.booking.entities.shuttles.types.ShuttleType;
import ca.ulaval.glo4002.booking.exceptions.shuttles.ShuttleTypeNotFoundException;

public class ShuttleTypeBuilder implements Builder<ShuttleType> {

    public ShuttleType buildById(Long id) {
        if (id.equals(ShuttleConstants.Types.DEPARTURE_ID)) {
            return buildDepartureShuttleType();
        } else if (id.equals(ShuttleConstants.Types.ARRIVAL_ID)) {
            return buildArrivalShuttleType();
        } else {
            throw new ShuttleTypeNotFoundException();
        }
    }

    public ShuttleType buildByName(String name) {
        if (name.equals(ShuttleConstants.Types.DEPARTURE_NAME)) {
            return buildDepartureShuttleType();
        } else if (name.equals(ShuttleConstants.Types.ARRIVAL_NAME)) {
            return buildArrivalShuttleType();
        } else {
            throw new ShuttleTypeNotFoundException();
        }
    }

    private ShuttleType buildDepartureShuttleType() {
        return new DepartureShuttleType();
    }

    private ShuttleType buildArrivalShuttleType() {
        return new ArrivalShuttleType();
    }
}
