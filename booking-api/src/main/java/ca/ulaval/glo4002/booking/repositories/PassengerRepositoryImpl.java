package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.constants.RepositoryConstants;
import ca.ulaval.glo4002.booking.entities.PassengerEntity;
import ca.ulaval.glo4002.booking.exceptions.UnusedMethodException;
import ca.ulaval.glo4002.booking.exceptions.passengers.PassengerNotFoundException;
import ca.ulaval.glo4002.booking.util.EntityManagerFactoryUtil;

import javax.persistence.EntityManager;
import java.util.Optional;

public class PassengerRepositoryImpl implements PassengerRepository {

	private final EntityManager entityManager;
	
	public PassengerRepositoryImpl() {
		this.entityManager = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
	}
	
	public PassengerRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public <S extends PassengerEntity> S save(S passenger) {
		if (passenger.getId() != null) {
			if (entityManager.find(PassengerEntity.class, passenger.getId()) == null) {
				entityManager.getTransaction().begin();
				entityManager.persist(passenger);
				entityManager.getTransaction().commit();
			}
		} else {
			throw new PassengerNotFoundException();
		}

		return passenger;
	}

	@Override
	public Iterable<PassengerEntity> findAll() {
		return entityManager
				.createQuery(RepositoryConstants.PASSENGER_FIND_ALL_QUERY, PassengerEntity.class)
				.getResultList();
	}

	@Override
	public <S extends PassengerEntity> Iterable<S> saveAll(Iterable<S> passengers) {
		throw new UnusedMethodException();
	}

	@Override
	public Optional<PassengerEntity> findById(Long id) {
		throw new UnusedMethodException();
	}

	@Override
	public boolean existsById(Long id) {
		throw new UnusedMethodException();
	}

	@Override
	public Iterable<PassengerEntity> findAllById(Iterable<Long> ids) {
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
	public void delete(PassengerEntity entity) {
		throw new UnusedMethodException();
		
	}

	@Override
	public void deleteAll(Iterable<? extends PassengerEntity> entities) {
		throw new UnusedMethodException();
		
	}

	@Override
	public void deleteAll() {
		throw new UnusedMethodException();
		
	}

}
