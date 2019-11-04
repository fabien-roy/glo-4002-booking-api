package ca.ulaval.glo4002.booking;

import ca.ulaval.glo4002.booking.controllers.OrderController;
import ca.ulaval.glo4002.booking.controllers.ShuttleManifestController;
import ca.ulaval.glo4002.booking.domain.NumberGenerator;
import ca.ulaval.glo4002.booking.factories.OrderFactory;
import ca.ulaval.glo4002.booking.factories.PassFactory;
import ca.ulaval.glo4002.booking.factories.PassBundleFactory;
import ca.ulaval.glo4002.booking.factories.ShuttleFactory;
import ca.ulaval.glo4002.booking.mappers.OrderMapper;
import ca.ulaval.glo4002.booking.mappers.PassBundleMapper;
import ca.ulaval.glo4002.booking.mappers.ShuttleManifestMapper;
import ca.ulaval.glo4002.booking.mappers.TripMapper;
import ca.ulaval.glo4002.booking.repositories.InMemoryOrderRepository;
import ca.ulaval.glo4002.booking.repositories.InMemoryTripRepository;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;
import ca.ulaval.glo4002.booking.repositories.TripRepository;
import ca.ulaval.glo4002.booking.services.OrderService;
import ca.ulaval.glo4002.booking.services.ShuttleManifestService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

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
        bind(InMemoryTripRepository.class).to(TripRepository.class);
        bind(InMemoryOrderRepository.class).to(OrderRepository.class);
    }

    private void bindFactories() {
        bindAsContract(PassFactory.class);
        bindAsContract(PassBundleFactory.class);
        bindAsContract(ShuttleFactory.class);
        bindAsContract(OrderFactory.class);
    }

    private void bindServices() {
        bindAsContract(OrderService.class);
        bindAsContract(ShuttleManifestService.class);
    }

    private void bindMappers() {
        bindAsContract(PassBundleMapper.class);
        bindAsContract(TripMapper.class);
        bindAsContract(ShuttleManifestMapper.class);
        bindAsContract(OrderMapper.class);
    }

    private void bindControllers() {
        bindAsContract(ShuttleManifestController.class).in(Singleton.class);
        bindAsContract(OrderController.class).in(Singleton.class);
    }
}
