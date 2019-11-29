package ca.ulaval.glo4002.booking.integration;

import ca.ulaval.glo4002.booking.festival.domain.Festival;
import ca.ulaval.glo4002.booking.orders.domain.OrderFactory;
import ca.ulaval.glo4002.booking.orders.infrastructure.InMemoryOrderRepository;
import ca.ulaval.glo4002.booking.orders.infrastructure.OrderRepository;
import ca.ulaval.glo4002.booking.orders.rest.OrderResource;
import ca.ulaval.glo4002.booking.orders.rest.OrderRequest;
import ca.ulaval.glo4002.booking.orders.rest.mappers.OrderMapper;
import ca.ulaval.glo4002.booking.orders.services.OrderService;
import ca.ulaval.glo4002.booking.oxygen.domain.OxygenFactory;
import ca.ulaval.glo4002.booking.oxygen.domain.OxygenTankProducer;
import ca.ulaval.glo4002.booking.passes.domain.PassCategories;
import ca.ulaval.glo4002.booking.passes.domain.PassFactory;
import ca.ulaval.glo4002.booking.passes.domain.PassOptions;
import ca.ulaval.glo4002.booking.program.events.domain.EventDateFactory;
import ca.ulaval.glo4002.booking.oxygen.history.infrastructure.InMemoryOxygenHistoryRepository;
import ca.ulaval.glo4002.booking.oxygen.history.infrastructure.OxygenHistoryRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.infrastructure.InMemoryOxygenInventoryRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.infrastructure.OxygenInventoryRepository;
import ca.ulaval.glo4002.booking.passes.rest.PassBundleRequest;
import ca.ulaval.glo4002.booking.passes.domain.PassBundleFactory;
import ca.ulaval.glo4002.booking.passes.rest.mappers.PassBundleMapper;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.numbers.NumberGenerator;
import ca.ulaval.glo4002.booking.oxygen.inventory.services.OxygenInventoryService;
import ca.ulaval.glo4002.booking.shuttles.domain.Shuttle;
import ca.ulaval.glo4002.booking.shuttles.domain.ShuttleCategories;
import ca.ulaval.glo4002.booking.shuttles.domain.ShuttleFactory;
import ca.ulaval.glo4002.booking.shuttles.manifest.rest.ShuttleManifestResource;
import ca.ulaval.glo4002.booking.shuttles.manifest.rest.ShuttleManifestResponse;
import ca.ulaval.glo4002.booking.shuttles.manifest.rest.mappers.ShuttleManifestMapper;
import ca.ulaval.glo4002.booking.shuttles.manifest.services.ShuttleManifestService;
import ca.ulaval.glo4002.booking.shuttles.trips.infrastructure.InMemoryTripRepository;
import ca.ulaval.glo4002.booking.shuttles.trips.rest.mappers.TripMapper;
import ca.ulaval.glo4002.booking.shuttles.trips.infrastructure.TripRepository;
import ca.ulaval.glo4002.booking.shuttles.trips.services.TripService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import javax.ws.rs.core.Response;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TripIntegrationTest {

    private OrderResource orderResource;
    private Festival festival;
    private ShuttleManifestResource shuttleManifestResource;
    private ShuttleFactory shuttleFactory;

    @BeforeEach
    void setUpResource() {
        festival = new Festival();

        NumberGenerator numberGenerator = new NumberGenerator();

        EventDateFactory eventDateFactory = new EventDateFactory(festival);
        PassFactory passFactory = new PassFactory(numberGenerator, eventDateFactory);
        PassBundleFactory passBundleFactory = new PassBundleFactory(passFactory);
        shuttleFactory = new ShuttleFactory();
        OxygenFactory oxygenFactory = new OxygenFactory(festival);
        OrderFactory orderFactory = new OrderFactory(festival, numberGenerator, passBundleFactory);

        TripRepository tripRepository = new InMemoryTripRepository(shuttleFactory);
        OxygenInventoryRepository oxygenInventoryRepository = new InMemoryOxygenInventoryRepository();
        OxygenHistoryRepository oxygenHistoryRepository = new InMemoryOxygenHistoryRepository();
        OrderRepository orderRepository = new InMemoryOrderRepository();

        OxygenTankProducer oxygenTankProducer = new OxygenTankProducer(oxygenInventoryRepository, oxygenHistoryRepository, oxygenFactory);

        PassBundleMapper passBundleMapper = new PassBundleMapper();
        TripMapper tripMapper = new TripMapper();
        ShuttleManifestMapper shuttleManifestMapper = new ShuttleManifestMapper(tripMapper);
        OrderMapper orderMapper = new OrderMapper(passBundleMapper);

        TripService tripService = new TripService(festival, tripRepository, shuttleFactory);
        OxygenInventoryService oxygenInventoryService = new OxygenInventoryService(festival, oxygenFactory, oxygenTankProducer);
        ShuttleManifestService shuttleManifestService = new ShuttleManifestService(tripRepository, shuttleManifestMapper);
        OrderService orderService = new OrderService(orderRepository, orderFactory, orderMapper, tripService, oxygenInventoryService);

        orderResource = new OrderResource(orderService);
        shuttleManifestResource = new ShuttleManifestResource(shuttleManifestService);
    }

    @Test
    void addOrder_shouldAddArrivalTrip() {
        String aDate = EventDate.getDefaultStartEventDate().toString();
        OrderRequest orderDto = buildDto(PassCategories.SUPERNOVA, PassOptions.SINGLE_PASS, Collections.singletonList(aDate));

        orderResource.addOrder(orderDto);
        Response response = shuttleManifestResource.get(aDate);
        ShuttleManifestResponse shuttleManifestResponse = (ShuttleManifestResponse) response.getEntity();

        assertEquals(1, shuttleManifestResponse.getArrivals().size());
        assertEquals(aDate, shuttleManifestResponse.getArrivals().get(0).getDate());
    }

    @Test
    void addOrder_shouldAddDepartureTrip() {
        String aDate = EventDate.getDefaultStartEventDate().toString();
        OrderRequest orderDto = buildDto(PassCategories.SUPERNOVA, PassOptions.SINGLE_PASS, Collections.singletonList(aDate));

        orderResource.addOrder(orderDto);
        Response response = shuttleManifestResource.get(aDate);
        ShuttleManifestResponse shuttleManifestResponse = (ShuttleManifestResponse) response.getEntity();

        assertEquals(1, shuttleManifestResponse.getDepartures().size());
        assertEquals(aDate, shuttleManifestResponse.getDepartures().get(0).getDate());
    }

    @Test
    void addOrder_shouldAddMultipleArrivalTrips_whenThereAreManyPasses() {
        String aDate = EventDate.getDefaultStartEventDate().toString();
        String anotherDate = EventDate.getDefaultStartEventDate().plusDays(1).toString();
        OrderRequest orderDto = buildDto(PassCategories.SUPERNOVA, PassOptions.SINGLE_PASS, Arrays.asList(aDate, anotherDate));

        orderResource.addOrder(orderDto);
        Response response = shuttleManifestResource.get(null);
        ShuttleManifestResponse shuttleManifestResponse = (ShuttleManifestResponse) response.getEntity();

        assertEquals(2, shuttleManifestResponse.getArrivals().size());
        assertTrue(shuttleManifestResponse.getArrivals().stream().anyMatch(arrival -> arrival.getDate().equals(aDate)));
        assertTrue(shuttleManifestResponse.getArrivals().stream().anyMatch(arrival -> arrival.getDate().equals(anotherDate)));
    }

    @Test
    void addOrder_shouldAddMultipleDepartureTrips_whenThereAreManyPasses() {
        String aDate = EventDate.getDefaultStartEventDate().toString();
        String anotherDate = EventDate.getDefaultStartEventDate().plusDays(1).toString();
        OrderRequest orderDto = buildDto(PassCategories.SUPERNOVA, PassOptions.SINGLE_PASS, Arrays.asList(aDate, anotherDate));

        orderResource.addOrder(orderDto);
        Response response = shuttleManifestResource.get(null);
        ShuttleManifestResponse shuttleManifestResponse = (ShuttleManifestResponse) response.getEntity();

        assertEquals(2, shuttleManifestResponse.getArrivals().size());
        assertTrue(shuttleManifestResponse.getDepartures().stream().anyMatch(departure -> departure.getDate().equals(aDate)));
        assertTrue(shuttleManifestResponse.getDepartures().stream().anyMatch(departure -> departure.getDate().equals(anotherDate)));
    }

    @Test
    void addOrder_shouldAddArrivalTripOnStartDate_whenPassIsPackage() {
        OrderRequest orderDto = buildDto(PassCategories.SUPERNOVA, PassOptions.PACKAGE, null);

        orderResource.addOrder(orderDto);
        Response response = shuttleManifestResource.get(null);
        ShuttleManifestResponse shuttleManifestResponse = (ShuttleManifestResponse) response.getEntity();

        assertEquals(1, shuttleManifestResponse.getArrivals().size());
        assertEquals(EventDate.getDefaultStartEventDate().toString(), shuttleManifestResponse.getArrivals().get(0).getDate());
    }

    @Test
    void addOrder_shouldAddDepartureTripOnEndDate_whenPassIsPackage() {
        OrderRequest orderDto = buildDto(PassCategories.SUPERNOVA, PassOptions.PACKAGE, null);

        orderResource.addOrder(orderDto);
        Response response = shuttleManifestResource.get(null);
        ShuttleManifestResponse shuttleManifestResponse = (ShuttleManifestResponse) response.getEntity();

        assertEquals(1, shuttleManifestResponse.getDepartures().size());
        assertEquals(EventDate.getDefaultEndEventDate().toString(), shuttleManifestResponse.getDepartures().get(0).getDate());
    }

    @ParameterizedTest
    @EnumSource(PassCategories.class)
    void addOrder_shouldAddArrivalTripsWithCorrectName(PassCategories passCategory) {
        String aDate = EventDate.getDefaultStartEventDate().toString();
        OrderRequest orderDto = buildDto(passCategory, PassOptions.SINGLE_PASS, Collections.singletonList(aDate));
        ShuttleCategories expectedShuttleCategory = shuttleFactory.createCategory(passCategory);

        orderResource.addOrder(orderDto);
        Response response = shuttleManifestResource.get(aDate);
        ShuttleManifestResponse shuttleManifestResponse = (ShuttleManifestResponse) response.getEntity();

        assertEquals(expectedShuttleCategory.toString(), shuttleManifestResponse.getArrivals().get(0).getShuttleName());
    }

    @ParameterizedTest
    @EnumSource(PassCategories.class)
    void addOrder_shouldAddDepartureTripsWithCorrectName(PassCategories passCategory) {
        String aDate = EventDate.getDefaultStartEventDate().toString();
        OrderRequest orderDto = buildDto(passCategory, PassOptions.SINGLE_PASS, Collections.singletonList(aDate));
        ShuttleCategories expectedShuttleCategory = shuttleFactory.createCategory(passCategory);

        orderResource.addOrder(orderDto);
        Response response = shuttleManifestResource.get(aDate);
        ShuttleManifestResponse shuttleManifestResponse = (ShuttleManifestResponse) response.getEntity();

        assertEquals(expectedShuttleCategory.toString(), shuttleManifestResponse.getDepartures().get(0).getShuttleName());
    }

    @ParameterizedTest
    @EnumSource(PassCategories.class)
    void addOrder_shouldAddToNewArrivalTrip_whenTripIsFull(PassCategories passCategory) {
        int maxCapacity = getMaxCapacityForPassCategory(passCategory);
        String aDate = EventDate.getDefaultStartEventDate().toString();
        List<String> someDates = Collections.nCopies(maxCapacity + 1, aDate);
        OrderRequest orderDto = buildDto(passCategory, PassOptions.SINGLE_PASS, someDates);

        orderResource.addOrder(orderDto);
        Response response = shuttleManifestResource.get(aDate);
        ShuttleManifestResponse shuttleManifestResponse = (ShuttleManifestResponse) response.getEntity();

        assertEquals(2, shuttleManifestResponse.getArrivals().size());
    }

    @ParameterizedTest
    @EnumSource(PassCategories.class)
    void addOrder_shouldAddToNewDepartureTrip_whenTripIsFull(PassCategories passCategory) {
        int maxCapacity = getMaxCapacityForPassCategory(passCategory);
        String aDate = EventDate.getDefaultStartEventDate().toString();
        List<String> someDates = Collections.nCopies(maxCapacity + 1, aDate);
        OrderRequest orderDto = buildDto(passCategory, PassOptions.SINGLE_PASS, someDates);

        orderResource.addOrder(orderDto);
        Response response = shuttleManifestResource.get(aDate);
        ShuttleManifestResponse shuttleManifestResponse = (ShuttleManifestResponse) response.getEntity();

        assertEquals(2, shuttleManifestResponse.getDepartures().size());
    }

    @ParameterizedTest
    @EnumSource(PassCategories.class)
    void addOrder_shouldAddToExistingArrivalTrip_whenTripIsNotFull(PassCategories passCategory) {
        int maxCapacity = getMaxCapacityForPassCategory(passCategory);
        String aDate = EventDate.getDefaultStartEventDate().toString();
        List<String> someDates = Collections.nCopies(maxCapacity, aDate);
        OrderRequest orderDto = buildDto(passCategory, PassOptions.SINGLE_PASS, someDates);

        orderResource.addOrder(orderDto);
        Response response = shuttleManifestResource.get(aDate);
        ShuttleManifestResponse shuttleManifestResponse = (ShuttleManifestResponse) response.getEntity();

        assertEquals(1, shuttleManifestResponse.getArrivals().size());
    }

    @ParameterizedTest
    @EnumSource(PassCategories.class)
    void addOrder_shouldAddToExistingDepartureTrip_whenTripIsNotFull(PassCategories passCategory) {
        int maxCapacity = getMaxCapacityForPassCategory(passCategory);
        String aDate = EventDate.getDefaultStartEventDate().toString();
        List<String> someDates = Collections.nCopies(maxCapacity, aDate);
        OrderRequest orderDto = buildDto(passCategory, PassOptions.SINGLE_PASS, someDates);

        orderResource.addOrder(orderDto);
        Response response = shuttleManifestResource.get(aDate);
        ShuttleManifestResponse shuttleManifestResponse = (ShuttleManifestResponse) response.getEntity();

        assertEquals(1, shuttleManifestResponse.getDepartures().size());
    }

    private OrderRequest buildDto(PassCategories passCategory, PassOptions passOptions, List<String> eventDates) {
        PassBundleRequest passBundleRequest = new PassBundleRequest(
                passCategory.toString(),
                passOptions.toString(),
                eventDates
        );

        return new OrderRequest(
                ZonedDateTime.of(festival.getMinimumEventDateToOrder().toLocalDateTime(), ZoneId.systemDefault()).toString(),
                "VENDOR",
                passBundleRequest
        );
    }

    private int getMaxCapacityForPassCategory(PassCategories passCategory) {
        ShuttleCategories shuttleCategory = shuttleFactory.createCategory(passCategory);
        Shuttle shuttle = shuttleFactory.create(shuttleCategory);

        return shuttle.getMaxCapacity();
    }
}
