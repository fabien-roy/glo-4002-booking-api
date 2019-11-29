package ca.ulaval.glo4002.booking.festival.rest;

import ca.ulaval.glo4002.booking.festival.services.FestivalService;
import ca.ulaval.glo4002.booking.program.events.rest.EventDatesDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ConfigurationControllerTest {

    private ConfigurationController controller;

    @BeforeEach
    void setUpController() {
        FestivalService festivalService = mock(FestivalService.class);

        controller = new ConfigurationController(festivalService);
    }

    @Test
    void addProgram_shouldReturnOk() {
        Response response = controller.setConfiguration(mock(EventDatesDto.class));

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}