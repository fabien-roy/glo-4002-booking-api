package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.dao.OrderDao;
import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.orders.Order;

import java.util.Optional;

// TODO : Have an interface for each repository : OrderRepository
// TODO : Rename InMemoryOrderRepository
public class OrderRepository {

    // TODO : Remove Daos, use list directly in repository
    private OrderDao dao;
    private PassRepository passRepository;

    public OrderRepository(OrderDao dao, PassRepository passRepository) {
        this.dao = dao;
        this.passRepository = passRepository;
    }

    public Optional<Order> getById(Number id) {
        return dao.get(id);
    }

    public void addOrder(Order order) {
        order.getPasses().forEach(pass -> passRepository.addPass(pass));

        dao.save(order);
    }
}
