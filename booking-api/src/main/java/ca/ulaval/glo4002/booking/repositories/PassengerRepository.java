package ca.ulaval.glo4002.booking.repositories;

import org.springframework.data.repository.CrudRepository;

import ca.ulaval.glo4002.booking.entities.PassengerEntity;

public interface PassengerRepository extends CrudRepository<PassengerEntity, Long>{

}
