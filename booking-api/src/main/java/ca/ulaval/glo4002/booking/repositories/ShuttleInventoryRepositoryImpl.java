package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.constants.RepositoryConstants;
import ca.ulaval.glo4002.booking.entities.ShuttleInventoryEntity;
import ca.ulaval.glo4002.booking.exceptions.UnusedMethodException;
import ca.ulaval.glo4002.booking.util.EntityManagerFactoryUtil;

import javax.persistence.EntityManager;
import java.util.Optional;

public class ShuttleInventoryRepositoryImpl implements ShuttleInventoryRepository {

	private final EntityManager entityManager;

	public ShuttleInventoryRepositoryImpl() {
		this.entityManager = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
	}

	public ShuttleInventoryRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	// TODO : TRANS : ShuttleInventoryRepository.findAll tests
	@Override
	public Iterable<ShuttleInventoryEntity> findAll() {
		return entityManager.createQuery(RepositoryConstants.SHUTTLE_INVENTORY_FIND_ALL_QUERY, ShuttleInventoryEntity.class).getResultList();
	}

	@Override
	public ShuttleInventoryEntity update(ShuttleInventoryEntity inventory) {
		entityManager.persist(inventory);

		return inventory;
	}

	@Override
	public <S extends ShuttleInventoryEntity> S save(S s) {
		throw new UnusedMethodException();
	}

	@Override
	public <S extends ShuttleInventoryEntity> Iterable<S> saveAll(Iterable<S> iterable) {
		throw new UnusedMethodException();
	}

	@Override
	public Optional<ShuttleInventoryEntity> findById(Long aLong) {
		throw new UnusedMethodException();
	}

	@Override
	public boolean existsById(Long aLong) {
		throw new UnusedMethodException();
	}

	@Override
	public Iterable<ShuttleInventoryEntity> findAllById(Iterable<Long> iterable) {
		throw new UnusedMethodException();
	}

	@Override
	public long count() {
		throw new UnusedMethodException();
	}

	@Override
	public void deleteById(Long aLong) {
		throw new UnusedMethodException();
	}

	@Override
	public void delete(ShuttleInventoryEntity shuttleInventoryEntity) {
		throw new UnusedMethodException();
	}

	@Override
	public void deleteAll(Iterable<? extends ShuttleInventoryEntity> iterable) {
		throw new UnusedMethodException();
	}

	@Override
	public void deleteAll() {
		throw new UnusedMethodException();
	}
}
