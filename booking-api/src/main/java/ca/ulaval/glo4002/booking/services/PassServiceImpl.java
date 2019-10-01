package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainObjects.passes.Pass;
import ca.ulaval.glo4002.booking.entities.PassEntity;
import ca.ulaval.glo4002.booking.exceptions.passes.PassNotFoundException;
import ca.ulaval.glo4002.booking.parsers.PassParser;
import ca.ulaval.glo4002.booking.repositories.PassRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PassServiceImpl implements PassService {

    @Resource
    private final PassRepository passRepository;
    private final PassParser passParser;

    public PassServiceImpl(PassRepository passRepository) {
        this.passRepository = passRepository;
        this.passParser = new PassParser();
    }

    @Override
    public Pass findById(Long id) {
        PassEntity passEntity = passRepository.findById(id).orElseThrow(PassNotFoundException::new);

        return passParser.parseEntity(passEntity);
    }

    @Override
    public Iterable<Pass> findAll() {
        List<Pass> passes = new ArrayList<>();

        passRepository.findAll().forEach(passEntity -> passes.add(passParser.parseEntity(passEntity)));

        return passes;
    }

    @Override
    public Iterable<Pass> saveAll(Iterable<Pass> passes) {
        List<PassEntity> passEntities = new ArrayList<>();

        passes.forEach(pass -> passEntities.add(passParser.toEntity(pass)));

        passRepository.saveAll(passEntities);

        return passes;
    }
}
