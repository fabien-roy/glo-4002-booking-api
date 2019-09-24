package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.entities.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
