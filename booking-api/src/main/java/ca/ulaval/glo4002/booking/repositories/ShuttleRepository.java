package ca.ulaval.glo4002.booking.repositories;

import org.springframework.data.repository.CrudRepository;

import ca.ulaval.glo4002.booking.data.transport.Shuttle;

public interface ShuttleRepository extends CrudRepository<Shuttle, Long>{

}
