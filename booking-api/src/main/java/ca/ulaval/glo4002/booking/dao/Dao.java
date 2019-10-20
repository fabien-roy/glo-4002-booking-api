package ca.ulaval.glo4002.booking.dao;

import ca.ulaval.glo4002.booking.domain.Id;

import java.util.List;
import java.util.Optional;

// TODO : Remove this link, but it's useful : https://www.baeldung.com/java-dao-pattern
// TODO : Teachers seem to hate interfaces, for some reason. Check if needed before MEP 2.0

public interface Dao<T> {

    Optional<T> get(Id id);

    List<T> getAll();

    void save(T t);
}
