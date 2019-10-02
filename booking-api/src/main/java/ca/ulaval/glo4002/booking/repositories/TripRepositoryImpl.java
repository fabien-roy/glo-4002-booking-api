package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.constants.RepositoryConstants;
import ca.ulaval.glo4002.booking.entities.TripEntity;
import ca.ulaval.glo4002.booking.exceptions.UnusedMethodException;
import ca.ulaval.glo4002.booking.util.EntityManagerFactoryUtil;

import javax.persistence.EntityManager;
import java.util.Optional;

public class TripRepositoryImpl implements TripRepository {

	private final EntityManager entityManager;

	public TripRepositoryImpl() {
		this.entityManager = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
	}

	public TripRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Iterable<TripEntity> findAll() {
        return entityManager
        		.createQuery(RepositoryConstants.TRIP_FIND_ALL_QUERY, TripEntity.class)
        		.getResultList();
	}

	@Override
	public <S extends TripEntity> S save(S trip) {
	    throw new UnusedMethodException();
	}

	@Override
	public <S extends TripEntity> Iterable<S> saveAll(Iterable<S> trips) {
		throw new UnusedMethodException();
	}

	@Override
	public Optional<TripEntity> findById(Long id) {
		throw new UnusedMethodException();
	}

	@Override
	public boolean existsById(Long id) {
		throw new UnusedMethodException();
	}

	@Override
	public Iterable<TripEntity> findAllById(Iterable<Long> ids) {
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
	public void delete(TripEntity trip) {
		throw new UnusedMethodException();
	}

	@Override
	public void deleteAll(Iterable<? extends TripEntity> trips) {
		throw new UnusedMethodException();
	}

	@Override
	public void deleteAll() {
		throw new UnusedMethodException();
	}
}
