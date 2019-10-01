package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.entities.TripEntity;
import org.springframework.data.repository.CrudRepository;

public interface TripRepository extends CrudRepository<TripEntity, Long> {

}
