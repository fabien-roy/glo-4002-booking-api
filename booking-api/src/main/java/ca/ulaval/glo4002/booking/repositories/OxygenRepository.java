package ca.ulaval.glo4002.booking.repositories;

import org.springframework.data.repository.CrudRepository;

import ca.ulaval.glo4002.booking.entities.OxygenTankEntity;

public interface OxygenRepository extends CrudRepository<OxygenTankEntity, Long> {

}
