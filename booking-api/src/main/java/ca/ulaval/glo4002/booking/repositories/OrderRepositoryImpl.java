package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.constants.RepositoryConstants;
import ca.ulaval.glo4002.booking.entities.OrderEntity;
import ca.ulaval.glo4002.booking.exceptions.UnusedMethodException;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderAlreadyCreatedException;
import ca.ulaval.glo4002.booking.exceptions.passes.PassNotFoundException;

import javax.persistence.EntityManager;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {

    private EntityManager entityManager;

    public OrderRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<OrderEntity> findById(Long id) {
        OrderEntity orderEntity = entityManager.find(OrderEntity.class, id);

        if (orderEntity == null) {
            throw new PassNotFoundException();
        }

        return Optional.of(orderEntity);
    }

    @Override
    public Iterable<OrderEntity> findAll() {
        return entityManager.createQuery(RepositoryConstants.ORDER_FIND_ALL_QUERY, OrderEntity.class).getResultList();
    }

    @Override
    public <S extends OrderEntity> S save(S order) {
        if (order.id == null) {
            entityManager.persist(order);
        } else {
            throw new OrderAlreadyCreatedException();
        }

        return order;
    }

    @Override
    public <S extends OrderEntity> Iterable<S> saveAll(Iterable<S> orders) {
        throw new UnusedMethodException();
    }

    @Override
    public boolean existsById(Long id) {
        throw new UnusedMethodException();
    }

    @Override
    public Iterable<OrderEntity> findAllById(Iterable<Long> orders) {
        throw new UnusedMethodException();
    }

    @Override
    public long count() {
        throw new UnusedMethodException();
    }

    @Override
    public void deleteById(Long id) {
        throw new UnusedMethodException();
    }

    @Override
    public void delete(OrderEntity order) {
        throw new UnusedMethodException();
    }

    @Override
    public void deleteAll(Iterable<? extends OrderEntity> orders) {
        throw new UnusedMethodException();
    }

    @Override
    public void deleteAll() {
        throw new UnusedMethodException();
    }
}
