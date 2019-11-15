package ca.ulaval.glo4002.booking.integration;

import ca.ulaval.glo4002.booking.orders.*;
import ca.ulaval.glo4002.booking.oxygen.*;
import ca.ulaval.glo4002.booking.oxygen.history.InMemoryOxygenHistoryRepository;
import ca.ulaval.glo4002.booking.oxygen.history.OxygenHistoryRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.InMemoryOxygenInventoryRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventoryRepository;
import ca.ulaval.glo4002.booking.passes.*;
import ca.ulaval.glo4002.booking.passes.bundles.PassBundleDto;
import ca.ulaval.glo4002.booking.passes.bundles.PassBundleFactory;
import ca.ulaval.glo4002.booking.passes.bundles.PassBundleMapper;
import ca.ulaval.glo4002.booking.shuttles.*;
import ca.ulaval.glo4002.booking.events.EventDate;
import ca.ulaval.glo4002.booking.numbers.NumberGenerator;
import ca.ulaval.glo4002.booking.orders.OrderService;
import ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventoryService;
import ca.ulaval.glo4002.booking.shuttles.manifest.ShuttleManifestController;
import ca.ulaval.glo4002.booking.shuttles.manifest.ShuttleManifestDto;
import ca.ulaval.glo4002.booking.shuttles.manifest.ShuttleManifestMapper;
import ca.ulaval.glo4002.booking.shuttles.manifest.ShuttleManifestService;
import ca.ulaval.glo4002.booking.shuttles.trips.InMemoryTripRepository;
import ca.ulaval.glo4002.booking.shuttles.trips.TripMapper;
import ca.ulaval.glo4002.booking.shuttles.trips.TripRepository;
import ca.ulaval.glo4002.booking.shuttles.trips.TripService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.http.ResponseEntity;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class TripIntegrationTest {

    private OrderController orderController;
    private ShuttleManifestController shuttleManifestController;
    private ShuttleFactory shuttleFactory;

    @BeforeEach
    void setUpController() {
        NumberGenerator numberGenerator = new NumberGenerator();

        PassFactory passFactory = new PassFactory(numberGenerator);
        PassBundleFactory passBundleFactory = new PassBundleFactory(passFactory);
        shuttleFactory = new ShuttleFactory();
        OxygenFactory oxygenFactory = new OxygenFactory();
        OrderFactory orderFactory = new OrderFactory(numberGenerator, passBundleFactory);

        TripRepository tripRepository = new InMemoryTripRepository(shuttleFactory);
        OxygenInventoryRepository oxygenInventoryRepository = new InMemoryOxygenInventoryRepository();
        OxygenHistoryRepository oxygenHistoryRepository = new InMemoryOxygenHistoryRepository();
        OrderRepository orderRepository = new InMemoryOrderRepository();

        OxygenTankProducer oxygenTankProducer = new OxygenTankProducer(oxygenInventoryRepository, oxygenHistoryRepository, oxygenFactory);

        PassBundleMapper passBundleMapper = new PassBundleMapper();
        TripMapper tripMapper = new TripMapper();
        ShuttleManifestMapper shuttleManifestMapper = new ShuttleManifestMapper(tripMapper);
        OrderMapper orderMapper = new OrderMapper(passBundleMapper);

        TripService tripService = new TripService(tripRepository, shuttleFactory);
        OxygenInventoryService oxygenInventoryService = new OxygenInventoryService(oxygenFactory, oxygenTankProducer);
        ShuttleManifestService shuttleManifestService = new ShuttleManifestService(tripRepository, shuttleManifestMapper);
        OrderService orderService = new OrderService(orderRepository, orderFactory, orderMapper, tripService, oxygenInventoryService);

        orderController = new OrderController(orderService);
        shuttleManifestController = new ShuttleManifestController(shuttleManifestService);
    }

    @Test
    void addOrder_shouldAddArrivalTrip() {
        String aDate = EventDate.START_DATE.toString();
        OrderWithPassesAsEventDatesDto orderDto = buildDto(PassCategories.SUPERNOVA, PassOptions.SINGLE_PASS, Collections.singletonList(aDate));

        orderController.addOrder(orderDto);
        ResponseEntity<?> response = shuttleManifestController.get(aDate);
        ShuttleManifestDto shuttleManifestDto = (ShuttleManifestDto) response.getBody();

        assertEquals(1, shuttleManifestDto.getArrivals().size());
        assertEquals(aDate, shuttleManifestDto.getArrivals().get(0).getDate());
    }

    @Test
    void addOrder_shouldAddDepartureTrip() {
        String aDate = EventDate.START_DATE.toString();
        OrderWithPassesAsEventDatesDto orderDto = buildDto(PassCategories.SUPERNOVA, PassOptions.SINGLE_PASS, Collections.singletonList(aDate));

        orderController.addOrder(orderDto);
        ResponseEntity<?> response = shuttleManifestController.get(aDate);
        ShuttleManifestDto shuttleManifestDto = (ShuttleManifestDto) response.getBody();

        assertEquals(1, shuttleManifestDto.getDepartures().size());
        assertEquals(aDate, shuttleManifestDto.getDepartures().get(0).getDate());
    }

    @Test
    void addOrder_shouldAddMultipleArrivalTrips_whenThereAreManyPasses() {
        String aDate = EventDate.START_DATE.toString();
        String anotherDate = EventDate.START_DATE.plusDays(1).toString();
        OrderWithPassesAsEventDatesDto orderDto = buildDto(PassCategories.SUPERNOVA, PassOptions.SINGLE_PASS, Arrays.asList(aDate, anotherDate));

        orderController.addOrder(orderDto);
        ResponseEntity<?> response = shuttleManifestController.get(null);
        ShuttleManifestDto shuttleManifestDto = (ShuttleManifestDto) response.getBody();

        assertEquals(2, shuttleManifestDto.getArrivals().size());
        assertTrue(shuttleManifestDto.getArrivals().stream().anyMatch(arrival -> arrival.getDate().equals(aDate)));
        assertTrue(shuttleManifestDto.getArrivals().stream().anyMatch(arrival -> arrival.getDate().equals(anotherDate)));
    }

    @Test
    void addOrder_shouldAddMultipleDepartureTrips_whenThereAreManyPasses() {
        String aDate = EventDate.START_DATE.toString();
        String anotherDate = EventDate.START_DATE.plusDays(1).toString();
        OrderWithPassesAsEventDatesDto orderDto = buildDto(PassCategories.SUPERNOVA, PassOptions.SINGLE_PASS, Arrays.asList(aDate, anotherDate));

        orderController.addOrder(orderDto);
        ResponseEntity<?> response = shuttleManifestController.get(null);
        ShuttleManifestDto shuttleManifestDto = (ShuttleManifestDto) response.getBody();

        assertEquals(2, shuttleManifestDto.getArrivals().size());
        assertTrue(shuttleManifestDto.getDepartures().stream().anyMatch(departure -> departure.getDate().equals(aDate)));
        assertTrue(shuttleManifestDto.getDepartures().stream().anyMatch(departure -> departure.getDate().equals(anotherDate)));
    }

    @Test
    void addOrder_shouldAddArrivalTripOnStartDate_whenPassIsPackage() {
        OrderWithPassesAsEventDatesDto orderDto = buildDto(PassCategories.SUPERNOVA, PassOptions.PACKAGE, null);

        orderController.addOrder(orderDto);
        ResponseEntity<?> response = shuttleManifestController.get(null);
        ShuttleManifestDto shuttleManifestDto = (ShuttleManifestDto) response.getBody();

        assertEquals(1, shuttleManifestDto.getArrivals().size());
        assertEquals(EventDate.START_DATE.toString(), shuttleManifestDto.getArrivals().get(0).getDate());
    }

    @Test
    void addOrder_shouldAddDepartureTripOnEndDate_whenPassIsPackage() {
        OrderWithPassesAsEventDatesDto orderDto = buildDto(PassCategories.SUPERNOVA, PassOptions.PACKAGE, null);

        orderController.addOrder(orderDto);
        ResponseEntity<?> response = shuttleManifestController.get(null);
        ShuttleManifestDto shuttleManifestDto = (ShuttleManifestDto) response.getBody();

        assertEquals(1, shuttleManifestDto.getDepartures().size());
        assertEquals(EventDate.END_DATE.toString(), shuttleManifestDto.getDepartures().get(0).getDate());
    }

    @ParameterizedTest
    @EnumSource(PassCategories.class)
    void addOrder_shouldAddArrivalTripsWithCorrectName(PassCategories passCategory) {
        String aDate = EventDate.START_DATE.toString();
        OrderWithPassesAsEventDatesDto orderDto = buildDto(passCategory, PassOptions.SINGLE_PASS, Collections.singletonList(aDate));
        ShuttleCategories expectedShuttleCategory = shuttleFactory.buildCategory(passCategory);

        orderController.addOrder(orderDto);
        ResponseEntity<?> response = shuttleManifestController.get(aDate);
        ShuttleManifestDto shuttleManifestDto = (ShuttleManifestDto) response.getBody();

        assertEquals(expectedShuttleCategory.toString(), shuttleManifestDto.getArrivals().get(0).getShuttleName());
    }

    @ParameterizedTest
    @EnumSource(PassCategories.class)
    void addOrder_shouldAddDepartureTripsWithCorrectName(PassCategories passCategory) {
        String aDate = EventDate.START_DATE.toString();
        OrderWithPassesAsEventDatesDto orderDto = buildDto(passCategory, PassOptions.SINGLE_PASS, Collections.singletonList(aDate));
        ShuttleCategories expectedShuttleCategory = shuttleFactory.buildCategory(passCategory);

        orderController.addOrder(orderDto);
        ResponseEntity<?> response = shuttleManifestController.get(aDate);
        ShuttleManifestDto shuttleManifestDto = (ShuttleManifestDto) response.getBody();

        assertEquals(expectedShuttleCategory.toString(), shuttleManifestDto.getDepartures().get(0).getShuttleName());
    }

    @ParameterizedTest
    @EnumSource(PassCategories.class)
    void addOrder_shouldAddToNewArrivalTrip_whenTripIsFull(PassCategories passCategory) {
        int maxCapacity = getMaxCapacityForPassCategory(passCategory);
        String aDate = EventDate.START_DATE.toString();
        List<String> someDates = Collections.nCopies(maxCapacity + 1, aDate);
        OrderWithPassesAsEventDatesDto orderDto = buildDto(passCategory, PassOptions.SINGLE_PASS, someDates);

        orderController.addOrder(orderDto);
        ResponseEntity<?> response = shuttleManifestController.get(aDate);
        ShuttleManifestDto shuttleManifestDto = (ShuttleManifestDto) response.getBody();

        assertEquals(2, shuttleManifestDto.getArrivals().size());
    }

    @ParameterizedTest
    @EnumSource(PassCategories.class)
    void addOrder_shouldAddToNewDepartureTrip_whenTripIsFull(PassCategories passCategory) {
        int maxCapacity = getMaxCapacityForPassCategory(passCategory);
        String aDate = EventDate.START_DATE.toString();
        List<String> someDates = Collections.nCopies(maxCapacity + 1, aDate);
        OrderWithPassesAsEventDatesDto orderDto = buildDto(passCategory, PassOptions.SINGLE_PASS, someDates);

        orderController.addOrder(orderDto);
        ResponseEntity<?> response = shuttleManifestController.get(aDate);
        ShuttleManifestDto shuttleManifestDto = (ShuttleManifestDto) response.getBody();

        assertEquals(2, shuttleManifestDto.getDepartures().size());
    }

    @ParameterizedTest
    @EnumSource(PassCategories.class)
    void addOrder_shouldAddToExistingArrivalTrip_whenTripIsNotFull(PassCategories passCategory) {
        int maxCapacity = getMaxCapacityForPassCategory(passCategory);
        String aDate = EventDate.START_DATE.toString();
        List<String> someDates = Collections.nCopies(maxCapacity, aDate);
        OrderWithPassesAsEventDatesDto orderDto = buildDto(passCategory, PassOptions.SINGLE_PASS, someDates);

        orderController.addOrder(orderDto);
        ResponseEntity<?> response = shuttleManifestController.get(aDate);
        ShuttleManifestDto shuttleManifestDto = (ShuttleManifestDto) response.getBody();

        assertEquals(1, shuttleManifestDto.getArrivals().size());
    }

    @ParameterizedTest
    @EnumSource(PassCategories.class)
    void addOrder_shouldAddToExistingDepartureTrip_whenTripIsNotFull(PassCategories passCategory) {
        int maxCapacity = getMaxCapacityForPassCategory(passCategory);
        String aDate = EventDate.START_DATE.toString();
        List<String> someDates = Collections.nCopies(maxCapacity, aDate);
        OrderWithPassesAsEventDatesDto orderDto = buildDto(passCategory, PassOptions.SINGLE_PASS, someDates);

        orderController.addOrder(orderDto);
        ResponseEntity<?> response = shuttleManifestController.get(aDate);
        ShuttleManifestDto shuttleManifestDto = (ShuttleManifestDto) response.getBody();

        assertEquals(1, shuttleManifestDto.getDepartures().size());
    }

    private OrderWithPassesAsEventDatesDto buildDto(PassCategories passCategory, PassOptions passOptions, List<String> eventDates) {
        PassBundleDto passBundleDto = new PassBundleDto(
                passCategory.toString(),
                passOptions.toString(),
                eventDates
        );

        return new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(OrderFactory.START_DATE_TIME, ZoneId.systemDefault()).toString(),
                "VENDOR",
                passBundleDto
        );
    }

    private int getMaxCapacityForPassCategory(PassCategories passCategory) {
        ShuttleCategories shuttleCategory = shuttleFactory.buildCategory(passCategory);
        Shuttle shuttle = shuttleFactory.build(shuttleCategory);

        return shuttle.getMaxCapacity();
    }
}
