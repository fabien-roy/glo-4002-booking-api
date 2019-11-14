package ca.ulaval.glo4002.booking;

import javax.inject.Singleton;

import ca.ulaval.glo4002.booking.factories.*;
import ca.ulaval.glo4002.booking.producers.OxygenTankProducer;
import ca.ulaval.glo4002.booking.repositories.*;
import ca.ulaval.glo4002.booking.services.*;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import ca.ulaval.glo4002.booking.clients.ArtistClient;
import ca.ulaval.glo4002.booking.controllers.OrderController;
import ca.ulaval.glo4002.booking.controllers.ProgramController;
import ca.ulaval.glo4002.booking.controllers.ReportController;
import ca.ulaval.glo4002.booking.controllers.ShuttleManifestController;
import ca.ulaval.glo4002.booking.converters.ArtistConverter;
import ca.ulaval.glo4002.booking.domain.NumberGenerator;
import ca.ulaval.glo4002.booking.mappers.OrderMapper;
import ca.ulaval.glo4002.booking.mappers.PassBundleMapper;
import ca.ulaval.glo4002.booking.mappers.ShuttleManifestMapper;
import ca.ulaval.glo4002.booking.mappers.TripMapper;

public class BookingBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bindGenerators();
        bindFactories();
        bindRepositories();
        bindProducers();
        bindServices();
        bindMappers();
        bindControllers();
        bindConverters();
        bindClients();
    }

    private void bindGenerators() {
        bindAsContract(NumberGenerator.class);
    }
    
    private void bindConverters() {
    	bindAsContract(ArtistConverter.class);
    }
    
    private void bindClients() {
    	bindAsContract(ArtistClient.class);
    }

    private void bindFactories() {
        bindAsContract(PassFactory.class);
        bindAsContract(PassBundleFactory.class);
        bindAsContract(OxygenFactory.class);
        bindAsContract(ShuttleFactory.class);
        bindAsContract(OrderFactory.class);
    }

    private void bindRepositories() {
        bind(InMemoryOxygenInventoryRepository.class).to(OxygenInventoryRepository.class).in(Singleton.class);
        bind(InMemoryTripRepository.class).to(TripRepository.class).in(Singleton.class);
        bind(InMemoryOrderRepository.class).to(OrderRepository.class).in(Singleton.class);
        bind(InMemoryArtistRepository.class).to(ArtistRepository.class).in(Singleton.class);
    }

    private void bindProducers() {
        bindAsContract(OxygenTankProducer.class);
    }

    private void bindServices() {
        bindAsContract(OxygenInventoryService.class);
        bindAsContract(TripService.class);
        bindAsContract(OrderService.class);
        bindAsContract(ShuttleManifestService.class);
        bindAsContract(ArtistService.class);
        bindAsContract(ProfitService.class);
    }

    private void bindMappers() {
        bindAsContract(PassBundleMapper.class);
        bindAsContract(OrderMapper.class);
        bindAsContract(TripMapper.class);
        bindAsContract(ShuttleManifestMapper.class);
    }

    private void bindControllers() {
        bindAsContract(ProgramController.class).in(Singleton.class);
        bindAsContract(OrderController.class).in(Singleton.class);
        bindAsContract(ShuttleManifestController.class).in(Singleton.class);
        bindAsContract(ReportController.class).in(Singleton.class);
    }
}
