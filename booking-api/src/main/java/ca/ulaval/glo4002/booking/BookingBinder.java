package ca.ulaval.glo4002.booking;

import javax.inject.Singleton;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import ca.ulaval.glo4002.booking.clients.ArtistClient;
import ca.ulaval.glo4002.booking.controllers.OrderController;
import ca.ulaval.glo4002.booking.controllers.ProgramController;
import ca.ulaval.glo4002.booking.controllers.ReportController;
import ca.ulaval.glo4002.booking.controllers.ShuttleManifestController;
import ca.ulaval.glo4002.booking.converters.ArtistConverter;
import ca.ulaval.glo4002.booking.domain.NumberGenerator;
import ca.ulaval.glo4002.booking.factories.EventFactory;
import ca.ulaval.glo4002.booking.factories.OrderFactory;
import ca.ulaval.glo4002.booking.factories.OxygenTankFactory;
import ca.ulaval.glo4002.booking.factories.PassBundleFactory;
import ca.ulaval.glo4002.booking.factories.PassFactory;
import ca.ulaval.glo4002.booking.factories.ShuttleFactory;
import ca.ulaval.glo4002.booking.mappers.OrderMapper;
import ca.ulaval.glo4002.booking.mappers.PassBundleMapper;
import ca.ulaval.glo4002.booking.mappers.ShuttleManifestMapper;
import ca.ulaval.glo4002.booking.mappers.TripMapper;
import ca.ulaval.glo4002.booking.repositories.ArtistRepository;
import ca.ulaval.glo4002.booking.repositories.EventRepository;
import ca.ulaval.glo4002.booking.repositories.InMemoryArtistRepository;
import ca.ulaval.glo4002.booking.repositories.InMemoryEventRepository;
import ca.ulaval.glo4002.booking.repositories.InMemoryOrderRepository;
import ca.ulaval.glo4002.booking.repositories.InMemoryOxygenTankInventoryRepository;
import ca.ulaval.glo4002.booking.repositories.InMemoryTripRepository;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;
import ca.ulaval.glo4002.booking.repositories.OxygenTankInventoryRepository;
import ca.ulaval.glo4002.booking.repositories.TripRepository;
import ca.ulaval.glo4002.booking.services.ArtistService;
import ca.ulaval.glo4002.booking.services.OrderService;
import ca.ulaval.glo4002.booking.services.OxygenTankInventoryService;
import ca.ulaval.glo4002.booking.services.ProfitService;
import ca.ulaval.glo4002.booking.services.ProgramService;
import ca.ulaval.glo4002.booking.services.ShuttleManifestService;
import ca.ulaval.glo4002.booking.services.TripService;

public class BookingBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bindGenerators();
        bindFactories();
        bindRepositories();
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
        bindAsContract(OxygenTankFactory.class);
        bindAsContract(ShuttleFactory.class);
        bindAsContract(OrderFactory.class);
        bindAsContract(EventFactory.class);
    }

    private void bindRepositories() {
        bind(InMemoryOxygenTankInventoryRepository.class).to(OxygenTankInventoryRepository.class).in(Singleton.class);
        bind(InMemoryTripRepository.class).to(TripRepository.class).in(Singleton.class);
        bind(InMemoryOrderRepository.class).to(OrderRepository.class).in(Singleton.class);
        bind(InMemoryEventRepository.class).to(EventRepository.class).in(Singleton.class);
        bind(InMemoryArtistRepository.class).to(ArtistRepository.class).in(Singleton.class);
    }

    private void bindServices() {
        bindAsContract(OxygenTankInventoryService.class);
        bindAsContract(TripService.class);
        bindAsContract(OrderService.class);
        bindAsContract(ShuttleManifestService.class);
        bindAsContract(ProgramService.class);
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
