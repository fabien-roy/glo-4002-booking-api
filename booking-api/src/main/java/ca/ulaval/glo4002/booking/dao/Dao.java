package ca.ulaval.glo4002.booking.dao;

import ca.ulaval.glo4002.booking.domain.Id;

import java.util.List;
import java.util.Optional;

// TODO : Remove this, but it's useful : https://www.baeldung.com/java-dao-pattern

public interface Dao<T, U> {

    Optional<T> get(U id);

    List<T> getAll();

    void save(T t);
}
