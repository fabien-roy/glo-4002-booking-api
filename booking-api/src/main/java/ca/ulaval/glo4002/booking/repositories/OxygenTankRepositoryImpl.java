package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.constants.RepositoryConstants;
import ca.ulaval.glo4002.booking.entities.OxygenTankEntity;
import ca.ulaval.glo4002.booking.exceptions.UnusedMethodException;
import ca.ulaval.glo4002.booking.exceptions.oxygen.OxygenTankAlreadyCreatedException;
import ca.ulaval.glo4002.booking.exceptions.oxygen.OxygenTankNotFoundException;
import ca.ulaval.glo4002.booking.util.EntityManagerFactoryUtil;

import javax.persistence.EntityManager;
import java.util.Optional;

public class OxygenTankRepositoryImpl implements OxygenTankRepository {

	private final EntityManager entityManager;

	public OxygenTankRepositoryImpl() {
		this.entityManager = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
	}

	public OxygenTankRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Iterable<OxygenTankEntity> findAll() {
		return entityManager.createQuery(RepositoryConstants.OXYGEN_TANK_FIND_ALL_QUERY, OxygenTankEntity.class).getResultList();
	}

	// TODO : OXY : Useless?
	@Override
	public <S extends OxygenTankEntity> Iterable<S> saveAll(Iterable<S> oxygenTanks) {
		oxygenTanks.forEach(oxygenTank -> {
			if (oxygenTank.getId() == null) {
				entityManager.getTransaction().begin();

				if (!entityManager.contains(oxygenTank)) {
					entityManager.persist(oxygenTank);
				}

				entityManager.getTransaction().commit();
			} else {
				throw new OxygenTankAlreadyCreatedException();
			}
		});

		return oxygenTanks;
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
	public <S extends OxygenTankEntity> S save(S oxygenTank) {
        entityManager.getTransaction().begin();

        if (!entityManager.contains(oxygenTank)) {
            entityManager.persist(oxygenTank);
        }

        entityManager.getTransaction().commit();

        return oxygenTank;
	}

	@Override
	public boolean existsById(Long id) {
		throw new UnusedMethodException();
	}

	@Override
	public Iterable<OxygenTankEntity> findAllById(Iterable<Long> ids) {
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
	public void delete(OxygenTankEntity entity) {
		throw new UnusedMethodException();
	}

	@Override
	public void deleteAll(Iterable<? extends OxygenTankEntity> entities) {
		throw new UnusedMethodException();
	}

	@Override
	public void deleteAll() {
		throw new UnusedMethodException();
	}
}
