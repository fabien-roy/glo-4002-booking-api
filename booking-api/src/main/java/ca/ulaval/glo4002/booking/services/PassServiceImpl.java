package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.orders.OrderItem;
import ca.ulaval.glo4002.booking.domainobjects.passes.Pass;
import ca.ulaval.glo4002.booking.entities.OrderEntity;
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
        PassEntity passEntity = passRepository.findById(id)
                .orElseThrow(() -> new PassNotFoundException(id.toString()));

        return passParser.parseEntity(passEntity);
    }

    @Override
    public Iterable<Pass> findAll() {
        List<Pass> passes = new ArrayList<>();

        passRepository.findAll().forEach(passEntity -> passes.add(passParser.parseEntity(passEntity)));

        return passes;
    }

    // TODO : ACP : Only used by tests, could be deleted
    @Override
    public Iterable<Pass> saveAll(Iterable<Pass> passes) {
        List<PassEntity> passEntities = new ArrayList<>();

        passes.forEach(pass -> passEntities.add(passParser.toEntity(pass)));

        passRepository.saveAll(passEntities);

        return passes;
    }

    // TODO : ACP : Test
    @Override
    public Iterable<Pass> order(OrderEntity order, Iterable<Pass> passes) {
        List<PassEntity> passEntities = new ArrayList<>();

        passes.forEach(pass -> passEntities.add(passParser.toEntity(pass)));
        passEntities.forEach(passEntity -> passEntity.setOrder(order));

        passRepository.saveAll(passEntities);

        order.clearOrderItems();
        order.addOrderItems(passEntities);

        return passes;
    }

    @Override
    public Iterable<Pass> getPasses(Iterable<? extends OrderItem> orderItems) {
        List<Pass> passes = new ArrayList<>();

        orderItems.forEach(orderItem -> {
            if (orderItem instanceof Pass) {
                passes.add((Pass) orderItem);
            }
        });

        return passes;
    }
}
