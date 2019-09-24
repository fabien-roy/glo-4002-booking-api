package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.entities.passes.Pass;
import org.springframework.data.repository.CrudRepository;

public interface PassRepository extends CrudRepository<Pass, Long> {

}
