package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.shuttles.Shuttle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

public class ShuttleServiceTest {

    public static final Long A_PASS_ID = 9L;
    private ShuttleServiceContext context;

    @BeforeEach
    void setUp(){
        context = new ShuttleServiceContext();
    }

    @Test
    void findAll_shouldReturnAllExistingShuttles(){
        context.setUpForFindAll();

        assertEquals(context.allShuttles, context.subject.findAll());
    }

    @Test
    void orderArrival_shouldReturnOrderedShuttleWithAnID_andOrderAPassenger(){
        Shuttle orderedShuttle = context.subject.orderArrival(context.aShuttleInventoryEntity, context.aShuttleWithoutID, A_PASS_ID);

        assertEquals(context.aShuttle.getId(), orderedShuttle.getId());
        assertNotNull(orderedShuttle.getId());
        verify(context.passengerService).order(context.aShuttleEntity, A_PASS_ID);
    }

    @Test
    void orderDeparture_shouldReturnOrderedShuttleWithAnID_andOrderAPassenger(){
        Shuttle orderedShuttle = context.subject.orderDeparture(context.aShuttleInventoryEntity, context.aShuttleWithoutID, A_PASS_ID);

        assertEquals(context.aShuttle.getId(), orderedShuttle.getId());
        assertNotNull(orderedShuttle.getId());
        verify(context.passengerService).order(context.aShuttleEntity, A_PASS_ID);
    }

    /*

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
    */
}
