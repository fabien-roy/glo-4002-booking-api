package ca.ulaval.glo4002.booking.endToEnd;

import ca.ulaval.glo4002.booking.constants.PassConstants;
import ca.ulaval.glo4002.booking.dto.OrderDto;
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

        ResponseEntity<OrderDto> response = (ResponseEntity<OrderDto>) context.orderController.getOrderById(context.aSinglePassOrderId);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatusCodeValue());
        assertEquals(1, response.getBody().passes.eventDates.size());
        assertEquals(context.aSinglePass.getId(), response.getBody().passes.passNumber);
        assertTrue(response.getBody().passes.eventDates.stream().anyMatch(eventDate -> eventDate.equals(context.aSinglePass.getEventDate().toString())));
        assertEquals(PassConstants.Options.SINGLE_NAME, response.getBody().passes.passOption);
    }

    @Test
    public void getOrderController_shouldReturnCorrectPassesDto_whenOrderNumberIsExistent_andOrderHasMultipleSinglePasses() {
        context.setUp().withMultipleSinglePassOrder();

        ResponseEntity<OrderDto> response = (ResponseEntity<OrderDto>) context.orderController.getOrderById(context.aSinglePassOrderId);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatusCodeValue());
        assertEquals(2, response.getBody().passes.eventDates.size());
        assertTrue(response.getBody().passes.eventDates.stream().anyMatch(eventDate -> eventDate.equals(context.aSinglePass.getEventDate().toString())));
        assertTrue(response.getBody().passes.eventDates.stream().anyMatch(eventDate -> eventDate.equals(context.anotherSinglePass.getEventDate().toString())));
        assertEquals(PassConstants.Options.PACKAGE_NAME, response.getBody().passes.passOption);
    }

    @Test
    public void getOrderController_shouldReturnCorrectPassesDto_whenOrderNumberIsExistent_andOrderHasAPackagePass() {
        context.setUp().withAPackagePassOrder();

        ResponseEntity<OrderDto> response = (ResponseEntity<OrderDto>) context.orderController.getOrderById(context.aSinglePassOrderId);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatusCodeValue());
        assertNull(response.getBody().passes.eventDates);
        assertEquals(PassConstants.Options.SINGLE_NAME, response.getBody().passes.passOption);
    }

    @Test
    public void getOrderController_shouldReturnCorrectPassesDto_whenManyOrderNumberAreExistent() {
        context.setUp().withASinglePassOrder().withAnotherSinglePassOrder();

        ResponseEntity<OrderDto> response = (ResponseEntity<OrderDto>) context.orderController.getOrderById(context.aSinglePassOrderId);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatusCodeValue());
        assertEquals(1, response.getBody().passes.eventDates.size());
        assertEquals(context.aSinglePass.getId(), response.getBody().passes.passNumber);
        assertTrue(response.getBody().passes.eventDates.stream().anyMatch(eventDate -> eventDate.equals(context.aSinglePass.getEventDate().toString())));
        assertEquals(PassConstants.Options.PACKAGE_NAME, response.getBody().passes.passOption);
    }

    @Test
    public void postOrderController_shouldReturnHttpErrorBadRequest_whenEventDateIsInvalid() {
        OrderDto orderDto = context.orderParser.toDto(context.orderParser.parseEntity(context.aSinglePassOrder));
        context.setUp();
        orderDto.passes.eventDates = PassEndToEndContext.SOME_INVALID_EVENT_DATES;

        ResponseEntity<OrderDto> response = (ResponseEntity<OrderDto>) context.orderController.addOrder(orderDto);

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatusCodeValue());
    }

    @Test
    public void postOrderController_shouldReturnHttpErrorBadRequest_whenEventDateIsNotNull_andOptionIsPackage() {
        OrderDto orderDto = context.orderParser.toDto(context.orderParser.parseEntity(context.aPackagePassOrder));
        context.setUp();
        orderDto.passes.passOption = PassConstants.Options.PACKAGE_NAME;
        orderDto.passes.eventDates = PassEndToEndContext.MULTIPLE_VALID_EVENT_DATES;

        ResponseEntity<OrderDto> response = (ResponseEntity<OrderDto>) context.orderController.addOrder(orderDto);

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatusCodeValue());
    }

    @Test
    public void postOrderController_shouldCreatePass() {
        OrderDto orderDto = context.orderParser.toDto(context.orderParser.parseEntity(context.aSinglePassOrder));
        context.setUp();
        orderDto.passes.eventDates = PassEndToEndContext.MULTIPLE_VALID_EVENT_DATES;

        ResponseEntity<OrderDto> response = (ResponseEntity<OrderDto>) context.orderController.addOrder(orderDto);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatusCodeValue());
        assertNotNull(response.getBody().passes);
    }

    @Test
    public void postOrderController_shouldCreateMutiplePass_whenManyEventDatesAreSent() {
        OrderDto orderDto = context.orderParser.toDto(context.orderParser.parseEntity(context.aSinglePassOrder));
        context.setUp();

        ResponseEntity<OrderDto> response = (ResponseEntity<OrderDto>) context.orderController.addOrder(orderDto);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatusCodeValue());
        assertNotNull(response.getBody().passes);
    }

    // TODO : ACP COS 2 : Unique pass numbers
    @Test
    public void postOrderController_shouldReturnUniquePasseNumbers() {
        OrderDto anOrderDto = context.orderParser.toDto(context.orderParser.parseEntity(context.aSinglePassOrder));
        OrderDto anotherOrderDto = context.orderParser.toDto(context.orderParser.parseEntity(context.anotherSinglePassOrder));
        context.setUp();

        ResponseEntity<OrderDto> aResponse = (ResponseEntity<OrderDto>) context.orderController.addOrder(anOrderDto);
        ResponseEntity<OrderDto> anotherResponse = (ResponseEntity<OrderDto>) context.orderController.addOrder(anotherOrderDto);

        assertEquals(Response.Status.CREATED.getStatusCode(), aResponse.getStatusCodeValue());
        assertEquals(Response.Status.CREATED.getStatusCode(), anotherResponse.getStatusCodeValue());
        assertNotNull(aResponse.getBody().passes.passNumber);
        assertNotNull(anotherResponse.getBody().passes.passNumber);
        assertNotEquals(aResponse.getBody().passes.passNumber, anotherResponse.getBody().passes.passNumber);
    }
}
