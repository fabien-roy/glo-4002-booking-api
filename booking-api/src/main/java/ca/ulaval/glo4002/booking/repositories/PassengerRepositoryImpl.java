package ca.ulaval.glo4002.booking.repositories;

import java.util.Optional;

import javax.persistence.EntityManager;

import ca.ulaval.glo4002.booking.constants.RepositoryConstants;
import ca.ulaval.glo4002.booking.entities.PassengerEntity;
import ca.ulaval.glo4002.booking.exceptions.UnusedMethodException;
import ca.ulaval.glo4002.booking.exceptions.passengers.PassengerAlreadyCreatedException;
import ca.ulaval.glo4002.booking.exceptions.passengers.PassengerNotFoundException;
import ca.ulaval.glo4002.booking.util.EntityManagerFactoryUtil;

public class PassengerRepositoryImpl implements PassengerRepository {

	private final EntityManager entityManager;
	
	public PassengerRepositoryImpl() {
		this.entityManager = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
	}
	
	public PassengerRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public <S extends PassengerEntity> S save(S entity) {
		throw new UnusedMethodException();
	}

	@Override
	public <S extends PassengerEntity> Iterable<S> saveAll(Iterable<S> passengers) {
		passengers.forEach(passenger -> {
			if (passenger.getId() == null) {
				entityManager.persist(passenger);
			} else throw new PassengerAlreadyCreatedException();
		});
		return passengers;
	}

	@Override
	public Optional<PassengerEntity> findById(Long id) {
		PassengerEntity passengerEntity = entityManager.find(PassengerEntity.class, id);
		
		if (passengerEntity == null) {
			throw new PassengerNotFoundException();
		}
		
		return Optional.of(passengerEntity);
	}

	@Override
	public boolean existsById(Long id) {
		throw new UnusedMethodException();
	}

	@Override
	public Iterable<PassengerEntity> findAll() {
		return entityManager
				.createQuery(RepositoryConstants.PASSENGER_FIND_ALL_QUERY, PassengerEntity.class)
				.getResultList();
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
