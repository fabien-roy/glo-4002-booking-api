package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domainObjects.orders.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
