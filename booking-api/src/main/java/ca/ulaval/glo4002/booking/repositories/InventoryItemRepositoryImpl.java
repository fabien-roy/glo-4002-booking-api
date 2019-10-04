package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.constants.RepositoryConstants;
import ca.ulaval.glo4002.booking.entities.InventoryItemEntity;
import ca.ulaval.glo4002.booking.exceptions.UnusedMethodException;
import ca.ulaval.glo4002.booking.exceptions.report.InventoryItemAlreadyCreatedException;
import ca.ulaval.glo4002.booking.exceptions.report.InventoryItemNotFoundException;
import ca.ulaval.glo4002.booking.util.EntityManagerFactoryUtil;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public class InventoryItemRepositoryImpl implements InventoryItemRepository {

	@PersistenceContext
	private final EntityManager entityManager;

	public InventoryItemRepositoryImpl() {
		this.entityManager = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
	}

	public InventoryItemRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	// TODO : (OXY) implement if null
	@Override
	public Optional<InventoryItemEntity> findById(Long id) {
		InventoryItemEntity inventoryItemEntity = entityManager.find(InventoryItemEntity.class, id);

		if(inventoryItemEntity == null){
			throw  new InventoryItemNotFoundException();
		}

		return Optional.of(inventoryItemEntity);
	}

	@Override
	public Iterable<InventoryItemEntity> findAll() {
		return entityManager.createQuery((RepositoryConstants.INVENTORYITEM_FIND_ALL_QUERY), InventoryItemEntity.class).getResultList();
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
	@Transactional(propagation = Propagation.REQUIRED)
	public <S extends InventoryItemEntity> S save(S inventoryItem) {
		if(inventoryItem.getId() == null) {
			entityManager.getTransaction().begin();

			if(!entityManager.contains(inventoryItem)) {
				entityManager.persist(inventoryItem);
			}

			entityManager.getTransaction().commit();
		} else {
			throw new InventoryItemAlreadyCreatedException();
		}

		return inventoryItem;
	}

	@Override
	public <S extends InventoryItemEntity> S update(S inventoryItem) {
		entityManager.persist(inventoryItem);

		return inventoryItem;
	}

	@Override
	public boolean existsById(Long aLong) {
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
