package ca.ulaval.glo4002.booking.endToEnd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassEndToEndTest {

    private PassEndToEndContext context;

    @BeforeEach
    void setUp(){
        context = new PassEndToEndContext();
    }

    @Test
    public void getOrderController_shouldReturnCorrectPassesDto_whenOrderNumberIsExistent() {
        context.setUp().withAnOrder();

        // TODO
    }

    @Test
    public void getOrderController_shouldReturnCorrectPassesDto_whenManyOrderNumberAreExistent() {
        context.setUp().withAnOrder().withAnotherOrder();

        // TODO
    }

    @Test
    public void postOrderController_shouldReturnHttpErrorBadRequest_whenEventDateIsInvalid() {
        context.setUp();

        // TODO
    }

    @Test
    public void postOrderController_shouldCreatePass() {
        context.setUp();

        // TODO
    }

    @Test
    public void postOrderController_shouldCreateMutiplePass_whenManyEventDatesAreSent() {
        context.setUp();

        // TODO
    }

    @Test
    public void postOrderController_shouldReturnUniquePasseNumbers() {
        context.setUp();

        // TODO
    }
}
