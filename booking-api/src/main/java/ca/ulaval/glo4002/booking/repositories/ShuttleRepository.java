package ca.ulaval.glo4002.booking.repositories;

import org.springframework.data.repository.CrudRepository;

import ca.ulaval.glo4002.booking.entities.ShuttleEntity;

public interface ShuttleRepository extends CrudRepository<ShuttleEntity, Long> {

    ShuttleEntity update(ShuttleEntity savedShuttle);
}
