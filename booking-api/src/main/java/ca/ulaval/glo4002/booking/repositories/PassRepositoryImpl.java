package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.util.EntityManagerFactoryUtil;
import ca.ulaval.glo4002.booking.constants.RepositoryConstants;
import ca.ulaval.glo4002.booking.entities.PassEntity;
import ca.ulaval.glo4002.booking.exceptions.UnusedMethodException;
import ca.ulaval.glo4002.booking.exceptions.passes.PassAlreadyCreatedException;
import ca.ulaval.glo4002.booking.exceptions.passes.PassNotFoundException;

import javax.persistence.EntityManager;
import java.util.Optional;

public class PassRepositoryImpl implements PassRepository {

    private final EntityManager entityManager;

    public PassRepositoryImpl() {
        this.entityManager = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
    }

    public PassRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<PassEntity> findById(Long id) {
        PassEntity passEntity = entityManager.find(PassEntity.class, id);

        if (passEntity == null) {
            throw new PassNotFoundException();
        }

        return Optional.of(passEntity);
    }

    @Override
    public Iterable<PassEntity> findAll() {
        return entityManager.createQuery(RepositoryConstants.PASS_FIND_ALL_QUERY, PassEntity.class).getResultList();
    }

    @Override
    public <S extends PassEntity> Iterable<S> saveAll(Iterable<S> passes) {
        passes.forEach(pass -> {
            if (pass.id == null) {
                entityManager.persist(pass);
            } else {
                throw new PassAlreadyCreatedException();
            }
        });

        return passes;
    }

    @Override
    public <S extends PassEntity> S save(S pass) {
        throw new UnusedMethodException();
    }

    @Override
    public boolean existsById(Long id) {
        throw new UnusedMethodException();
    }

    @Override
    public Iterable<PassEntity> findAllById(Iterable<Long> passes) {
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
    public void delete(PassEntity pass) {
        throw new UnusedMethodException();
    }

    @Override
    public void deleteAll(Iterable<? extends PassEntity> passes) {
        throw new UnusedMethodException();
    }

    @Override
    public void deleteAll() {
        throw new UnusedMethodException();
    }
}
