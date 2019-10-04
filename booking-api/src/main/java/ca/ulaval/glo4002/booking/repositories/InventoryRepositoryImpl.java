package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.report.InventoryAlreadyCreatedException;
import ca.ulaval.glo4002.booking.exceptions.report.InventoryItemAlreadyCreatedException;
import ca.ulaval.glo4002.booking.exceptions.report.InventoryNotFoundException;
import ca.ulaval.glo4002.booking.util.EntityManagerFactoryUtil;
import ca.ulaval.glo4002.booking.constants.RepositoryConstants;
import ca.ulaval.glo4002.booking.entities.InventoryEntity;
import ca.ulaval.glo4002.booking.exceptions.UnusedMethodException;
import ca.ulaval.glo4002.booking.util.EntityManagerFactoryUtil;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public class InventoryRepositoryImpl implements InventoryRepository {

	@PersistenceContext
	private final EntityManager entityManager;

	public InventoryRepositoryImpl() {
		this.entityManager = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
	}

	public InventoryRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Optional<InventoryEntity> findById(Long id) {
		InventoryEntity inventoryEntity = entityManager.find(InventoryEntity.class, id);

		if(inventoryEntity == null) {
			throw new InventoryNotFoundException(id.toString());
		}

		return Optional.of(inventoryEntity);
	}

	@Override
	public Iterable<InventoryEntity> findAll() {
		return entityManager.createQuery(RepositoryConstants.INVENTORY_FIND_ALL_QUERY, InventoryEntity.class).getResultList();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public <S extends InventoryEntity> Iterable<S> saveAll(Iterable<S> inventories) {
		inventories.forEach(inventory -> {
			entityManager.getTransaction().begin();

			if(!entityManager.contains(inventory)) {
				entityManager.persist(inventory);
			}

			entityManager.getTransaction().commit();
		});

		return inventories;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public <S extends InventoryEntity> S save(S inventory) {
		if(inventory.getId() == null) {
			entityManager.getTransaction().begin();

			if(!entityManager.contains(inventory)){
				entityManager.persist(inventory);
			}

			entityManager.getTransaction().commit();
		} else {
			throw new InventoryAlreadyCreatedException(inventory.getId().toString());
		}

		return inventory;
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
