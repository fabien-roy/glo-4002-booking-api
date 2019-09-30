package ca.ulaval.glo4002.booking.repositories;

import java.util.Optional;

import javax.persistence.EntityManager;

import ca.ulaval.glo4002.booking.constants.RepositoryConstants;
import ca.ulaval.glo4002.booking.entities.OxygenTankEntity;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderAlreadyCreatedException;
import ca.ulaval.glo4002.booking.exceptions.oxygen.OxygenTankNotFoundException;

public class OxygenRepositoryImpl implements OxygenRepository {

	private final EntityManager entityManager;

	public OxygenRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Iterable<OxygenTankEntity> findAll() {
		return entityManager.createQuery(RepositoryConstants.ORDER_FIND_ALL_QUERY, OxygenTankEntity.class)
				.getResultList();
	}

	@Override
	public <S extends OxygenTankEntity> S save(S oxygenTank) {
		if (oxygenTank.id == null) {
			entityManager.persist(oxygenTank);
		} else {
			throw new OrderAlreadyCreatedException();
		}

		return oxygenTank;
	}

	@Override
	public <S extends OxygenTankEntity> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Write this code
		return null;
	}

	@Override
	public Optional<OxygenTankEntity> findById(Long id) {
		OxygenTankEntity oxygenTankEntity = entityManager.find(OxygenTankEntity.class, id);

		if (oxygenTankEntity == null) {
			throw new OxygenTankNotFoundException();
		}

		return Optional.of(oxygenTankEntity);
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Write this code
		return false;
	}

	@Override
	public Iterable<OxygenTankEntity> findAllById(Iterable<Long> ids) {
		// TODO Write this code
		return null;
	}

	@Override
	public long count() {
		// TODO Write this code
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Write this code

	}

	@Override
	public void delete(OxygenTankEntity entity) {
		// TODO Write this code

	}

	@Override
	public void deleteAll(Iterable<? extends OxygenTankEntity> entities) {
		// TODO Write this code

	}

	@Override
	public void deleteAll() {
		// TODO Write this code

	}
}
