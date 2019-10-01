package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.constants.RepositoryConstants;
import ca.ulaval.glo4002.booking.entities.InventoryEntity;
import ca.ulaval.glo4002.booking.exceptions.UnusedMethodException;
import ca.ulaval.glo4002.booking.util.EntityManagerFactoryUtil;

import javax.persistence.EntityManager;
import java.util.Optional;

public class InventoryRepositoryImpl implements InventoryRepository {

	private final EntityManager entityManager;

	public InventoryRepositoryImpl() {
		this.entityManager = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
	}

	public InventoryRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	// TODO : Test
	@Override
	public <S extends InventoryEntity> S save(S inventory) {
        entityManager.persist(inventory);

		return inventory;
	}

	// TODO : Test
	@Override
	public Iterable<InventoryEntity> findAll() {
		return entityManager
				.createQuery(RepositoryConstants.INVENTORY_FIND_ALL_QUERY, InventoryEntity.class)
				.getResultList();
	}

	@Override
	public <S extends InventoryEntity> Iterable<S> saveAll(Iterable<S> inventories) {
		throw new UnusedMethodException();
	}

	@Override
	public Optional<InventoryEntity> findById(Long id) {
		throw new UnusedMethodException();
	}

	@Override
	public boolean existsById(Long id) {
		throw new UnusedMethodException();
	}

	@Override
	public Iterable<InventoryEntity> findAllById(Iterable<Long> inventories) {
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
	public void delete(InventoryEntity inventory) {
		throw new UnusedMethodException();
	}

	@Override
	public void deleteAll(Iterable<? extends InventoryEntity> inventories) {
		throw new UnusedMethodException();
	}

	@Override
	public void deleteAll() {
		throw new UnusedMethodException();
	}
}
