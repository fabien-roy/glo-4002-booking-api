package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.entities.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
}
