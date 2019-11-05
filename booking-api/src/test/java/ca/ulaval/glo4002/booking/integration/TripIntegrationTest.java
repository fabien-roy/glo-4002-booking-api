package ca.ulaval.glo4002.booking.integration;

import ca.ulaval.glo4002.booking.controllers.OrderController;
import ca.ulaval.glo4002.booking.domain.EventDate;
import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.NumberGenerator;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.domain.orders.OrderNumber;
import ca.ulaval.glo4002.booking.domain.passes.Pass;
import ca.ulaval.glo4002.booking.domain.passes.PassBundle;
import ca.ulaval.glo4002.booking.domain.passes.PassCategory;
import ca.ulaval.glo4002.booking.dto.*;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.PassOptions;
import ca.ulaval.glo4002.booking.exceptions.InvalidEventDateException;
import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.factories.OrderFactory;
import ca.ulaval.glo4002.booking.factories.PassBundleFactory;
import ca.ulaval.glo4002.booking.factories.PassFactory;
import ca.ulaval.glo4002.booking.factories.ShuttleFactory;
import ca.ulaval.glo4002.booking.mappers.OrderMapper;
import ca.ulaval.glo4002.booking.mappers.PassBundleMapper;
import ca.ulaval.glo4002.booking.repositories.InMemoryOrderRepository;
import ca.ulaval.glo4002.booking.repositories.InMemoryTripRepository;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;
import ca.ulaval.glo4002.booking.repositories.TripRepository;
import ca.ulaval.glo4002.booking.services.OrderService;
import ca.ulaval.glo4002.booking.services.TripService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class TripIntegrationTest {

    private OrderController controller;
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUpController() {
        NumberGenerator numberGenerator = new NumberGenerator();

        PassFactory passFactory = new PassFactory(numberGenerator);
        PassBundleFactory passBundleFactory = new PassBundleFactory(passFactory);
        ShuttleFactory shuttleFactory = new ShuttleFactory();
        OrderFactory orderFactory = new OrderFactory(numberGenerator, passBundleFactory);

        TripRepository tripRepository = new InMemoryTripRepository(shuttleFactory);
        orderRepository = new InMemoryOrderRepository();

        PassBundleMapper passBundleMapper = new PassBundleMapper();
        OrderMapper orderMapper = new OrderMapper(passBundleMapper);

        TripService tripService = new TripService(tripRepository, shuttleFactory);
        OrderService orderService = new OrderService(orderRepository, orderFactory, orderMapper, tripService);

        controller = new OrderController(orderService);
    }

    @Test
    public void addOrder_shouldAddArrivalTrip() {
        PassBundleDto passBundleDto = new PassBundleDto(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.SINGLE_PASS.toString(),
                Collections.singletonList(EventDate.START_DATE.toString())
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(OrderFactory.START_DATE_TIME, ZoneId.systemDefault()).toString(),
                "VENDOR",
                passBundleDto
        );
    }

    @Test
    public void addOrder_shouldAddDepartureTrip() {
        // TODO
    }

    @Test
    public void addOrder_shouldAddMultipleArrivalTrips_whenThereAreManyPasses() {
        // TODO
    }

    @Test
    public void addOrder_shouldAddMultipleDepartureTrips_whenThereAreManyPasses() {
        // TODO
    }

    @Test
    public void addOrder_shouldAddArrivalTripOnStartDate_whenPassIsPackage() {
        // TODO
    }

    @Test
    public void addOrder_shouldAddDepartureTripOnEndDate_whenPassIsPackage() {
        // TODO
    }

    @Test
    public void addOrder_shouldAddArrivalTripsWithCorrectCategory() {
        // TODO
    }

    @Test
    public void addOrder_shouldAddDepartureTripsWithCorrectCategory() {
        // TODO
    }

    @Test
    public void addOrder_shouldAddToNewArrivalTrip_whenTripIsFull() {
        // TODO
    }

    @Test
    public void addOrder_shouldAddToNewDepartureTrip_whenTripIsFull() {
        // TODO
    }

    @Test
    public void addOrder_shouldAddToExistingArrivalTrip_whenTripIsNotFull() {
        // TODO
    }

    @Test
    public void addOrder_shouldAddToExistingDepartureTrip_whenTripIsNotFull() {
        // TODO
    }
}
