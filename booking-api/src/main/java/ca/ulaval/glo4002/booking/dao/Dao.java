package ca.ulaval.glo4002.booking.dao;

import java.util.List;
import java.util.Optional;

import ca.ulaval.glo4002.booking.domain.Id;

// TODO : Remove this link, but it's useful : https://www.baeldung.com/java-dao-pattern
// TODO : Teachers seem to hate interfaces, for some reason. Check if needed before MEP 2.0
// TODO if i'm not wrong, interface should represent something we can do. something-able. 
// (readable, for example). maybe that's what they don't like about our interface ?

public interface Dao<T> {

	Optional<T> get(Id id);

	List<T> getAll();

	void save(T t);
}
