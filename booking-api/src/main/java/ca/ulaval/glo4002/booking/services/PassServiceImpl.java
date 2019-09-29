package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainObjects.passes.Pass;
import ca.ulaval.glo4002.booking.entities.PassEntity;
import ca.ulaval.glo4002.booking.exceptions.UnusedMethodException;
import ca.ulaval.glo4002.booking.exceptions.passes.PassNotFoundException;
import ca.ulaval.glo4002.booking.parsers.PassParser;
import ca.ulaval.glo4002.booking.repositories.PassRepository;

import java.util.*;

public class PassServiceImpl implements PassService {

    private PassRepository passRepository;
    private PassParser passParser;

    public PassServiceImpl(PassRepository passRepository) {
        this.passRepository = passRepository;
        this.passParser = new PassParser();
    }

    @Override
    public Pass findById(Long id) {
        Optional<PassEntity> passEntity = passRepository.findById(id);

        if (passEntity.isPresent()) {
            return passParser.parseEntity(passEntity.get()).get(0);
        } else {
            throw new PassNotFoundException();
        }
    }

    @Override
    public Iterable<Pass> findAll() {
        List<Pass> passes = new ArrayList<>();

        passRepository.findAll().forEach(passEntity -> passes.add(passParser.parseEntity(passEntity).get(0)));

        return passes;
    }

    @Override
    public Iterable<Pass> saveAll(Iterable<Pass> passes) {
        List<PassEntity> passEntities = new ArrayList<>();

        passes.forEach(pass -> passEntities.add(passParser.toEntity(new ArrayList<>(Collections.singletonList(pass)))));

        passRepository.saveAll(passEntities);

        return passes;
    }

    @Override
    public Pass save(Pass object) {
        throw new UnusedMethodException();
    }
}
