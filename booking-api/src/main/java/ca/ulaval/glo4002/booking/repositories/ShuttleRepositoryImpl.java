package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.constants.RepositoryConstants;
import ca.ulaval.glo4002.booking.entities.ShuttleEntity;
import ca.ulaval.glo4002.booking.exceptions.UnusedMethodException;
import ca.ulaval.glo4002.booking.exceptions.shuttles.ShuttleAlreadyCreatedException;
import ca.ulaval.glo4002.booking.exceptions.shuttles.ShuttleNotFoundException;
import ca.ulaval.glo4002.booking.util.EntityManagerFactoryUtil;

import javax.persistence.EntityManager;
import java.util.Optional;

public class ShuttleRepositoryImpl implements ShuttleRepository {
	
	private final EntityManager entityManager;
	
	public ShuttleRepositoryImpl() {
		this.entityManager = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
	}
	
	public ShuttleRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public Optional<ShuttleEntity> findById(Long id) {
		ShuttleEntity shuttleEntity = entityManager.find(ShuttleEntity.class, id);

		if(shuttleEntity == null) {
			throw new ShuttleNotFoundException();
		}

		return Optional.of(shuttleEntity);
	}
	
	@Override
	public Iterable<ShuttleEntity> findAll() {
		return entityManager
				.createQuery(RepositoryConstants.SHUTTLE_FIND_ALL_QUERY, ShuttleEntity.class)
				.getResultList();
	}
	
	@Override
	public <S extends ShuttleEntity> Iterable<S> saveAll(Iterable<S> shuttles) {
		shuttles.forEach(shuttle -> {
			if (shuttle.getId() == null) {
				entityManager.persist(shuttle);
			} else throw new ShuttleAlreadyCreatedException();
		});
		return shuttles;
	}
	
	@Override
	public <S extends ShuttleEntity> S save (S shuttle) {
		throw new UnusedMethodException();
	}
	
    @Override
    public boolean existsById(Long id) {
        throw new UnusedMethodException();
    }

    @Override
    public Iterable<ShuttleEntity> findAllById(Iterable<Long> shuttles) {
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
    public void delete(ShuttleEntity shuttles) {
        throw new UnusedMethodException();
    }

    @Override
    public void deleteAll(Iterable<? extends ShuttleEntity> shuttles) {
        throw new UnusedMethodException();
    }

    @Override
    public void deleteAll() {
        throw new UnusedMethodException();
    }
}
