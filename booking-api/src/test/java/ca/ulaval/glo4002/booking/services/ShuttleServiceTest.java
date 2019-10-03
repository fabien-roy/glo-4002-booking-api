package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.domainobjects.orders.Order;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderNotFoundException;
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
        this.context = new ShuttleServiceContext();
        this.subject = new ShuttleServiceImpl(this.context.repository, this.context.passengerRepository);
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

        assertEquals(2, ids.size());
        assertTrue(ids.contains(context.aShuttle.getId()));
        assertTrue(ids.contains(context.anotherShuttle.getId()));
    }

}
