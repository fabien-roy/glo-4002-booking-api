package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.exceptions.shuttles.ShuttleNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShuttleServiceTest {

    private ShuttleServiceImpl subject;
    private ShuttleServiceContext context;

    @BeforeEach
    public void setUp() {
        context = new ShuttleServiceContext();
        subject = new ShuttleServiceImpl(context.repository);
    }

    @Test
    public void findById_shouldThrowShuttleNotFoundException_whenShuttleDoesNotExist() {
        ShuttleNotFoundException thrown = assertThrows(
                ShuttleNotFoundException.class,
                () -> subject.findById(ShuttleServiceContext.A_NON_EXISTENT_SHUTTLE_ID)
        );

        assertEquals(ExceptionConstants.Shuttle.NOT_FOUND_DESCRIPTION, thrown.getMessage());
    }

    @Test
    public void findById_shouldReturnCorrectShuttle() {
        Shuttle shuttle = subject.findById(ShuttleServiceContext.A_SHUTTLE_ID);

        assertEquals(context.aShuttle.getId(), shuttle.getId());
    }

    @Test
    public void findAll_shouldReturnCorrectShuttles() {
        List<Long> ids = new ArrayList<>();

        subject.findAll().forEach(shuttle -> ids.add(shuttle.getId()));

        assertEquals(3, ids.size());
        assertTrue(ids.contains(context.aShuttle.getId()));
        assertTrue(ids.contains(context.anotherShuttle.getId()));
    }

    @Test
    public void order_shouldReturnShuttle_whenShuttleIsNotFull(){
        List<Shuttle> shuttles = new ArrayList<>();

        subject.order(context.aShuttle.getShuttleCategory().getQuality()).forEach(shuttles::add);

        assertEquals(1, shuttles.size());
        assertTrue(shuttles.stream().allMatch(shuttle -> shuttle.getId().equals(ShuttleServiceContext.A_SHUTTLE_ID)));
    }

    @Test
    public void order_shouldReturnNewShuttle_whenLastShuttleIsFull(){
        List<Shuttle> shuttles = new ArrayList<>();
        context.setUpWithFullTrip();

        subject.order(context.aShuttle.getShuttleCategory().getQuality()).forEach(shuttles::add);

        assertEquals(1, shuttles.size());
        assertTrue(shuttles.stream().allMatch(shuttle -> shuttle.getId().equals(ShuttleServiceContext.ANOTHER_SHUTTLE_ID)));
    }

    @Test
    public void order_shouldReturnBothShuttles_whenLastShuttleIsAlmostFull(){
        List<Shuttle> shuttles = new ArrayList<>();
        context.setUpWithAlmostFullTrip();

        subject.order(context.aShuttle.getShuttleCategory().getQuality()).forEach(shuttles::add);
        subject.order(context.aShuttle.getShuttleCategory().getQuality()).forEach(shuttles::add);

        assertEquals(2, shuttles.size());
        assertTrue(shuttles.stream().anyMatch(shuttle -> shuttle.getId().equals(ShuttleServiceContext.A_SHUTTLE_ID)));
        assertTrue(shuttles.stream().anyMatch(shuttle -> shuttle.getId().equals(ShuttleServiceContext.ANOTHER_SHUTTLE_ID)));
    }

    @Test
    public void order_shouldReturnCorrectQualityForShuttle(){
        List<Shuttle> shuttles = new ArrayList<>();

        subject.order(context.anotherQualityShuttle.getShuttleCategory().getQuality()).forEach(shuttles::add);

        assertEquals(1, shuttles.size());
        assertTrue(shuttles.stream().allMatch(shuttle -> shuttle.getId().equals(ShuttleServiceContext.ANOTHER_QUALITY_SHUTTLE_ID)));
    }
}
