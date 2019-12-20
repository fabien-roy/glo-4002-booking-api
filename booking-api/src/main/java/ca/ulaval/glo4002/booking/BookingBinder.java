package ca.ulaval.glo4002.booking;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.festival.rest.ConfigurationResource;
import ca.ulaval.glo4002.booking.festival.services.FestivalService;
import ca.ulaval.glo4002.booking.orders.domain.OrderFactory;
import ca.ulaval.glo4002.booking.orders.domain.OrderIdentifierGenerator;
import ca.ulaval.glo4002.booking.orders.domain.OrderRepository;
import ca.ulaval.glo4002.booking.orders.infrastructure.InMemoryOrderRepository;
import ca.ulaval.glo4002.booking.orders.rest.OrderResource;
import ca.ulaval.glo4002.booking.orders.rest.mappers.OrderDateMapper;
import ca.ulaval.glo4002.booking.orders.rest.mappers.OrderMapper;
import ca.ulaval.glo4002.booking.orders.services.OrderService;
import ca.ulaval.glo4002.booking.oxygen.domain.OxygenFactory;
import ca.ulaval.glo4002.booking.oxygen.domain.OxygenTankProducer;
import ca.ulaval.glo4002.booking.oxygen.history.domain.OxygenHistoryRepository;
import ca.ulaval.glo4002.booking.oxygen.history.infrastructure.InMemoryOxygenHistoryRepository;
import ca.ulaval.glo4002.booking.oxygen.history.rest.mappers.OxygenHistoryMapper;
import ca.ulaval.glo4002.booking.oxygen.inventory.domain.OxygenInventoryRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.infrastructure.InMemoryOxygenInventoryRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.rest.mappers.OxygenInventoryMapper;
import ca.ulaval.glo4002.booking.oxygen.inventory.services.OxygenInventoryService;
import ca.ulaval.glo4002.booking.oxygen.report.rest.OxygenReportResource;
import ca.ulaval.glo4002.booking.oxygen.report.rest.mappers.OxygenReportMapper;
import ca.ulaval.glo4002.booking.oxygen.report.services.OxygenReportService;
import ca.ulaval.glo4002.booking.passes.domain.PassNumberGenerator;
import ca.ulaval.glo4002.booking.passes.rest.mappers.PassMapper;
import ca.ulaval.glo4002.booking.profits.rest.ProfitReportResource;
import ca.ulaval.glo4002.booking.profits.rest.mappers.ProfitMapper;
import ca.ulaval.glo4002.booking.profits.services.ProfitService;
import ca.ulaval.glo4002.booking.program.artists.domain.ArtistRepository;
import ca.ulaval.glo4002.booking.program.artists.infrastructure.ExternalArtistClient;
import ca.ulaval.glo4002.booking.program.artists.infrastructure.ExternalArtistConverter;
import ca.ulaval.glo4002.booking.program.artists.infrastructure.ExternalArtistRepository;
import ca.ulaval.glo4002.booking.program.artists.services.ArtistService;
import ca.ulaval.glo4002.booking.program.events.domain.EventRepository;
import ca.ulaval.glo4002.booking.program.events.infrastructure.InMemoryEventRepository;
import ca.ulaval.glo4002.booking.program.events.rest.mappers.EventDateMapper;
import ca.ulaval.glo4002.booking.program.events.rest.mappers.EventMapper;
import ca.ulaval.glo4002.booking.program.rest.ProgramResource;
import ca.ulaval.glo4002.booking.program.services.ProgramService;
import ca.ulaval.glo4002.booking.shuttles.domain.ShuttleFactory;
import ca.ulaval.glo4002.booking.shuttles.rest.ShuttleManifestResource;
import ca.ulaval.glo4002.booking.shuttles.rest.mappers.ShuttleManifestMapper;
import ca.ulaval.glo4002.booking.shuttles.services.ShuttleManifestService;
import ca.ulaval.glo4002.booking.shuttles.trips.domain.TripRepository;
import ca.ulaval.glo4002.booking.shuttles.trips.infrastructure.InMemoryTripRepository;
import ca.ulaval.glo4002.booking.shuttles.trips.rest.mappers.TripMapper;
import ca.ulaval.glo4002.booking.shuttles.trips.services.TripService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

public class BookingBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bindConfiguration();
        bindGenerators();
        bindFactories();
        bindRepositories();
        bindProducers();
        bindServices();
        bindMappers();
        bindResources();
        bindConverters();
        bindClients();
    }

    private void bindConfiguration() {
        bindAsContract(FestivalConfiguration.class).in(Singleton.class);
    }

    private void bindGenerators() {
        bindAsContract(OrderIdentifierGenerator.class);
        bindAsContract(PassNumberGenerator.class);
    }
    
    private void bindConverters() {
    	bindAsContract(ExternalArtistConverter.class);
    }
    
    private void bindClients() {
    	bindAsContract(ExternalArtistClient.class);
    }

    private void bindFactories() {
        bindAsContract(OxygenFactory.class);
        bindAsContract(ShuttleFactory.class);
        bindAsContract(OrderFactory.class);
        bindAsContract(OrderDateMapper.class);
    }

    private void bindRepositories() {
        bind(InMemoryOxygenInventoryRepository.class).to(OxygenInventoryRepository.class).in(Singleton.class);
        bind(InMemoryOxygenHistoryRepository.class).to(OxygenHistoryRepository.class).in(Singleton.class);
        bind(InMemoryTripRepository.class).to(TripRepository.class).in(Singleton.class);
        bind(InMemoryOrderRepository.class).to(OrderRepository.class).in(Singleton.class);
        bind(ExternalArtistRepository.class).to(ArtistRepository.class).in(Singleton.class);
        bind(InMemoryEventRepository.class).to(EventRepository.class).in(Singleton.class);
    }

    private void bindProducers() {
        bindAsContract(OxygenTankProducer.class);
    }

    private void bindServices() {
        bindAsContract(OrderService.class);
        bindAsContract(OxygenInventoryService.class);
        bindAsContract(TripService.class);
        bindAsContract(ShuttleManifestService.class);
        bindAsContract(ArtistService.class);
        bindAsContract(ProfitService.class);
        bindAsContract(ProgramService.class);
        bindAsContract(OxygenReportService.class);
        bindAsContract(FestivalService.class);
    }

    private void bindMappers() {
        bindAsContract(OrderMapper.class);
        bindAsContract(PassMapper.class);
        bindAsContract(TripMapper.class);
        bindAsContract(ShuttleManifestMapper.class);
        bindAsContract(OxygenInventoryMapper.class);
        bindAsContract(OxygenHistoryMapper.class);
        bindAsContract(OxygenReportMapper.class);
        bindAsContract(EventMapper.class);
        bindAsContract(EventDateMapper.class);
        bindAsContract(ProfitMapper.class);
    }

    private void bindResources() {
        bindAsContract(ProgramResource.class).in(Singleton.class);
        bindAsContract(OrderResource.class).in(Singleton.class);
        bindAsContract(ShuttleManifestResource.class).in(Singleton.class);
        bindAsContract(OxygenReportResource.class).in(Singleton.class);
        bindAsContract(ProfitReportResource.class).in(Singleton.class);
        bindAsContract(ConfigurationResource.class).in(Singleton.class);
    }
}
