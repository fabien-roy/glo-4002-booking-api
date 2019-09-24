package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.entities.shuttles.Shuttle;
import org.springframework.data.repository.CrudRepository;

public interface ShuttleRepository extends CrudRepository<Shuttle, Long>{

}
