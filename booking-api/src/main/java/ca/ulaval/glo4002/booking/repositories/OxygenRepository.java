package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domainObjects.oxygen.OxygenTank;
import org.springframework.data.repository.CrudRepository;

public interface OxygenRepository extends CrudRepository<OxygenTank, Long> {

}
