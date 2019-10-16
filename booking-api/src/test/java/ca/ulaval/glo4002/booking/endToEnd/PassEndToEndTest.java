package ca.ulaval.glo4002.booking.endToEnd;

import ca.ulaval.glo4002.booking.constants.PassConstants;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsPassesDto;
import ca.ulaval.glo4002.booking.dto.PassDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.core.Response;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

public class PassEndToEndTest {

    private PassEndToEndContext context;

    @BeforeEach
    void setUp(){
        context = new PassEndToEndContext();
    }

    @Test
    public void getOrderController_shouldReturnCorrectPassesDto_whenOrderNumberIsExistent() {
        context.setUp().withASinglePassOrder();

        ResponseEntity<OrderWithPassesAsPassesDto> response = (ResponseEntity<OrderWithPassesAsPassesDto>) context.orderController.getByOrderNumber(context.getOrderNumber(context.aSinglePassOrderId));

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatusCodeValue());
        assertEquals(1, response.getBody().passes.size());
        assertEquals(context.aSinglePass.getId(), response.getBody().passes.get(0).passNumber);
        assertEquals(context.aSinglePass.getEventDate().toString(), response.getBody().passes.get(0).eventDate);
        assertEquals(PassConstants.Options.SINGLE_NAME, response.getBody().passes.get(0).passOption);
    }

    @Test
    public void getOrderController_shouldReturnCorrectPassesDto_whenOrderNumberIsExistent_andOrderHasMultipleSinglePasses() {
        context.setUp().withMultipleSinglePassOrder();

        ResponseEntity<OrderWithPassesAsPassesDto> response = (ResponseEntity<OrderWithPassesAsPassesDto>) context.orderController.getByOrderNumber(context.getOrderNumber(context.aSinglePassOrderId));

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatusCodeValue());
        assertEquals(2, response.getBody().passes.size());
        assertTrue(response.getBody().passes.stream().anyMatch(pass -> pass.eventDate.equals(context.aSinglePass.getEventDate().toString())));
        assertTrue(response.getBody().passes.stream().anyMatch(pass -> pass.eventDate.equals(context.anotherSinglePass.getEventDate().toString())));
        assertTrue(response.getBody().passes.stream().allMatch(pass -> pass.passOption.equals(PassConstants.Options.SINGLE_NAME)));
    }

    @Test
    public void getOrderController_shouldReturnCorrectPassesDto_whenOrderNumberIsExistent_andOrderHasAPackagePass() {
        context.setUp().withAPackagePassOrder();

        ResponseEntity<OrderWithPassesAsPassesDto> response = (ResponseEntity<OrderWithPassesAsPassesDto>) context.orderController.getByOrderNumber(context.getOrderNumber(context.aSinglePassOrderId));

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatusCodeValue());
        assertNull(response.getBody().passes.get(0).eventDate);
        assertEquals(PassConstants.Options.PACKAGE_NAME, response.getBody().passes.get(0).passOption);
    }

    @Test
    public void getOrderController_shouldReturnCorrectPassesDto_whenManyOrderNumberAreExistent() {
        context.setUp().withASinglePassOrder().withAnotherSinglePassOrder();

        ResponseEntity<OrderWithPassesAsPassesDto> response = (ResponseEntity<OrderWithPassesAsPassesDto>) context.orderController.getByOrderNumber(context.getOrderNumber(context.aSinglePassOrderId));

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatusCodeValue());
        assertEquals(1, response.getBody().passes.size());
        assertEquals(context.aSinglePass.getId(), response.getBody().passes.get(0).passNumber);
        assertTrue(response.getBody().passes.stream().anyMatch(pass -> pass.eventDate.equals(context.aSinglePass.getEventDate().toString())));
        assertEquals(PassConstants.Options.SINGLE_NAME, response.getBody().passes.get(0).passOption);
    }

    @Test
    public void postOrderController_shouldReturnHttpErrorBadRequest_whenEventDateIsInvalid() {
        OrderWithPassesAsEventDatesDto orderDto = context.aSinglePassOrderDto;
        context.setUp();
        orderDto.passes.eventDates = PassEndToEndContext.SOME_INVALID_EVENT_DATES;

        ResponseEntity<OrderWithPassesAsPassesDto> response = (ResponseEntity<OrderWithPassesAsPassesDto>) context.orderController.addOrder(orderDto);

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatusCodeValue());
    }

    @Test
    public void postOrderController_shouldReturnHttpErrorBadRequest_whenEventDateIsNotNull_andOptionIsPackage() {
        context.setUp();
        OrderWithPassesAsEventDatesDto orderDto = context.aPackagePassOrderDto;
        orderDto.passes.passOption = PassConstants.Options.PACKAGE_NAME;
        orderDto.passes.eventDates = PassEndToEndContext.MULTIPLE_VALID_EVENT_DATES;

        ResponseEntity<OrderWithPassesAsPassesDto> response = (ResponseEntity<OrderWithPassesAsPassesDto>) context.orderController.addOrder(orderDto);

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatusCodeValue());
    }

    @Test
    public void postOrderController_shouldCreatePass() {
        OrderWithPassesAsEventDatesDto orderDto = context.aSinglePassOrderDto;
        context.setUp();
        orderDto.passes.eventDates = PassEndToEndContext.MULTIPLE_VALID_EVENT_DATES;

        ResponseEntity<OrderWithPassesAsPassesDto> response = (ResponseEntity<OrderWithPassesAsPassesDto>) context.orderController.addOrder(orderDto);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatusCodeValue());
        assertNotNull(response.getBody().passes);
    }

    @Test
    public void postOrderController_shouldCreateMutiplePass_whenManyEventDatesAreSent() {
        OrderWithPassesAsEventDatesDto orderDto = context.aMultipleSinglePassOrderDto;
        context.setUp();

        ResponseEntity<OrderWithPassesAsPassesDto> response = (ResponseEntity<OrderWithPassesAsPassesDto>) context.orderController.addOrder(orderDto);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatusCodeValue());
        assertNotNull(response.getBody().passes);
    }

    @Test
    public void postOrderController_shouldReturnUniquePasseNumbers() {
        OrderWithPassesAsEventDatesDto anOrderDto = context.aSinglePassOrderDto;
        OrderWithPassesAsEventDatesDto anotherOrderDto = context.anotherSinglePassOrderDto;
        context.setUp();

        ResponseEntity<OrderWithPassesAsPassesDto> aResponse = (ResponseEntity<OrderWithPassesAsPassesDto>) context.orderController.addOrder(anOrderDto);
        ResponseEntity<OrderWithPassesAsPassesDto> anotherResponse = (ResponseEntity<OrderWithPassesAsPassesDto>) context.orderController.addOrder(anotherOrderDto);

        assertEquals(Response.Status.CREATED.getStatusCode(), aResponse.getStatusCodeValue());
        assertEquals(Response.Status.CREATED.getStatusCode(), anotherResponse.getStatusCodeValue());
        assertNotNull(aResponse.getBody().passes);
        assertNotNull(anotherResponse.getBody().passes);
        for (PassDto pass : aResponse.getBody().passes) {
            assertFalse(anotherResponse.getBody().passes.stream().anyMatch(anotherPass -> anotherPass.passNumber.equals(pass.passNumber)));
        }
    }
}
