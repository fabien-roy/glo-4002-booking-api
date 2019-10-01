package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.entities.PassEntity;
import org.springframework.data.repository.CrudRepository;

public interface PassRepository extends CrudRepository<PassEntity, Long> {

}
