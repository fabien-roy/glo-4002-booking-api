package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainObjects.passes.Pass;
import ca.ulaval.glo4002.booking.entities.PassEntity;
import ca.ulaval.glo4002.booking.exceptions.passes.PassNotFoundException;
import ca.ulaval.glo4002.booking.parsers.PassParser;
import ca.ulaval.glo4002.booking.repositories.PassRepository;

import java.util.*;

public class PassService implements Service<Pass, Long> {

    private PassRepository passRepository;
    private PassParser passParser;

    public PassService(PassRepository passRepository) {
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
        Iterable<PassEntity> passEntities = passRepository.findAll();

        for (PassEntity passEntity : passEntities) {
            passes.add(passParser.parseEntity(passEntity).get(0));
        }

        return passes;
    }

    @Override
    public Iterable<Pass> saveAll(Iterable<Pass> passes) {
        List<PassEntity> passEntities = new ArrayList<>();

        for (Pass pass : passes) {
            passEntities.add(passParser.toEntity(new ArrayList<>(Collections.singletonList(pass))));
        }

        passRepository.saveAll(passEntities);

        return passes;
    }
}
