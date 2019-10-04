package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.exceptions.report.InventoryAlreadyCreatedException;
import ca.ulaval.glo4002.booking.exceptions.report.InventoryNotFoundException;
import ca.ulaval.glo4002.booking.util.EntityManagerFactoryUtil;
import ca.ulaval.glo4002.booking.constants.RepositoryConstants;
import ca.ulaval.glo4002.booking.entities.OxygenTankInventoryEntity;
import ca.ulaval.glo4002.booking.exceptions.UnusedMethodException;

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
	public Optional<OxygenTankInventoryEntity> findById(Long id) {
		OxygenTankInventoryEntity oxygenTankInventoryEntity = entityManager.find(OxygenTankInventoryEntity.class, id);

		if(oxygenTankInventoryEntity == null) {
			throw new InventoryNotFoundException(id.toString());
		}

		return Optional.of(oxygenTankInventoryEntity);
	}

	@Override
	public Iterable<OxygenTankInventoryEntity> findAll() {
		return entityManager.createQuery(RepositoryConstants.INVENTORY_FIND_ALL_QUERY, OxygenTankInventoryEntity.class).getResultList();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public <S extends OxygenTankInventoryEntity> Iterable<S> saveAll(Iterable<S> inventories) {
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
	public <S extends OxygenTankInventoryEntity> S save(S inventory) {
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
	public Iterable<OxygenTankInventoryEntity> findAllById(Iterable<Long> inventories) {
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
	public void delete(OxygenTankInventoryEntity inventory) {
		throw new UnusedMethodException();
	}

	@Override
	public void deleteAll(Iterable<? extends OxygenTankInventoryEntity> inventories) {
		throw new UnusedMethodException();
	}

	@Override
	public void deleteAll() {
		throw new UnusedMethodException();
	}
}
