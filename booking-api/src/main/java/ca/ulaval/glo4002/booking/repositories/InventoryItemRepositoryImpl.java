package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.entities.InventoryItemEntity;
import ca.ulaval.glo4002.booking.exceptions.UnusedMethodException;
import ca.ulaval.glo4002.booking.util.EntityManagerFactoryUtil;

import javax.persistence.EntityManager;
import java.util.Optional;

public class InventoryItemRepositoryImpl implements InventoryItemRepository {

	private final EntityManager entityManager;

	public InventoryItemRepositoryImpl() {
		this.entityManager = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
	}

	public InventoryItemRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public <S extends InventoryItemEntity> Iterable<S> saveAll(Iterable<S> inventoryItems) {
		inventoryItems.forEach(inventoryItem -> {
            entityManager.getTransaction().begin();

            if (!entityManager.contains(inventoryItem)) {
                entityManager.persist(inventoryItem);
            }

            entityManager.getTransaction().commit();
		});

		return inventoryItems;
	}

	@Override
	public Optional<InventoryItemEntity> findById(Long id) {
		InventoryItemEntity inventoryItemEntity = entityManager.find(InventoryItemEntity.class, id);

		// TODO : (OXY) implement if null

		return Optional.of(inventoryItemEntity);
	}

	@Override
	public <S extends InventoryItemEntity> S save(S s) {
		throw new UnusedMethodException();
	}

	@Override
	public boolean existsById(Long aLong) {
		throw new UnusedMethodException();
	}

	@Override
	public Iterable<InventoryItemEntity> findAll() {
		throw new UnusedMethodException();
	}

	@Override
	public Iterable<InventoryItemEntity> findAllById(Iterable<Long> inventoryItems) {
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
	public void delete(InventoryItemEntity inventoryItemEntity) {
		throw new UnusedMethodException();
	}

	@Override
	public void deleteAll(Iterable<? extends InventoryItemEntity> iterable) {
		throw new UnusedMethodException();
	}

	@Override
	public void deleteAll() {
		throw new UnusedMethodException();
	}
}
