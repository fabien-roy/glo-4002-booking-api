package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.constants.TripConstants;
import ca.ulaval.glo4002.booking.domainObjects.trips.ArrivalTrip;
import ca.ulaval.glo4002.booking.domainObjects.trips.DepartureTrip;
import ca.ulaval.glo4002.booking.domainObjects.trips.Trip;
import ca.ulaval.glo4002.booking.entities.TripEntity;

import java.util.ArrayList;

public class TripParser implements EntityParser<Trip, TripEntity> {

    // TODO : Add passengers
	@Override
	public Trip parseEntity(TripEntity entity) {
	    if (entity.getTypeId().equals(TripConstants.Types.DEPARTURE_ID)) {
	        return new DepartureTrip(
	                entity.getId(),
                    entity.getDate(),
                    new ArrayList<>()
            );
        } else if (entity.getTypeId().equals(TripConstants.Types.ARRIVAL_ID)) {
            return new ArrivalTrip(
                    entity.getId(),
                    entity.getDate(),
                    new ArrayList<>()
            );
        } else {
            // TODO : Should we throw?
        }

        return null;
	}

	@Override
	public TripEntity toEntity(Trip trip) {
		return new TripEntity(
		        trip.getId(),
                trip.getDate(),
                trip.getType().getId()
        );
	}
}
