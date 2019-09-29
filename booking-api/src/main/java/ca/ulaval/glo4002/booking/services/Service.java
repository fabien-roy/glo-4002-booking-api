package ca.ulaval.glo4002.booking.services;

public interface Service<T, ID> {

    T findById(ID id);

    Iterable<T> findAll();

    T save(T object);

    Iterable<T> saveAll(Iterable<T> objects);
}
