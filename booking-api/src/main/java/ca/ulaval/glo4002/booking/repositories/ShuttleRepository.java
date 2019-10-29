package ca.ulaval.glo4002.booking.repositories;

import java.util.Optional;

import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.shuttles.Shuttle;

public interface ShuttleRepository {

	Optional<Shuttle> findByShuttleNumber(Number shuttleNumber);
	
	void addShuttle(Shuttle shuttle);
}
