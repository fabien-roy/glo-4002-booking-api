package ca.ulaval.glo4002.booking;

import ca.ulaval.glo4002.booking.controllers.OrderController;
import ca.ulaval.glo4002.booking.domain.NumberGenerator;
import ca.ulaval.glo4002.booking.factories.OrderFactory;
import ca.ulaval.glo4002.booking.factories.OxygenTankFactory;
import ca.ulaval.glo4002.booking.factories.PassFactory;
import ca.ulaval.glo4002.booking.factories.PassListFactory;
import ca.ulaval.glo4002.booking.mappers.OrderMapper;
import ca.ulaval.glo4002.booking.mappers.PassListMapper;
import ca.ulaval.glo4002.booking.repositories.InMemoryOrderRepository;
import ca.ulaval.glo4002.booking.repositories.InMemoryOxygenTankInventoryRepository;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;
import ca.ulaval.glo4002.booking.repositories.OxygenTankInventoryRepository;
import ca.ulaval.glo4002.booking.services.OrderService;
import ca.ulaval.glo4002.booking.services.OxygenTankInventoryService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class BookingBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bindGenerators();
        bindRepositories();
        bindFactories();
        bindServices();
        bindMappers();
        bindControllers();
    }

    private void bindGenerators() {
        bindAsContract(NumberGenerator.class);
    }

    private void bindRepositories() {
        bind(InMemoryOxygenTankInventoryRepository.class).to(OxygenTankInventoryRepository.class);
        bind(InMemoryOrderRepository.class).to(OrderRepository.class);
    }

    private void bindFactories() {
        bindAsContract(PassFactory.class);
        bindAsContract(PassListFactory.class);
        bindAsContract(OxygenTankFactory.class);
        bindAsContract(OrderFactory.class);
    }

    private void bindServices() {
        bindAsContract(OxygenTankInventoryService.class);
        bindAsContract(OrderService.class);
    }

    private void bindMappers() {
        bindAsContract(PassListMapper.class);
        bindAsContract(OrderMapper.class);
    }

    private void bindControllers() {
        bindAsContract(OrderController.class);
    }
}
