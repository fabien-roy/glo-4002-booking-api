package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.EventDate;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;

public interface TripRepository {

	void addPassenger(ShuttleCategories shuttleCategory, EventDate eventDate);
}
