package ca.ulaval.glo4002.booking.profits.rest;

import ca.ulaval.glo4002.booking.profits.services.ProfitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class ProfitReportControllerTest {

    private ProfitReportResource resource;

    @BeforeEach
    void setUpResource() {
        ProfitService profitService = mock(ProfitService.class);

        resource = new ProfitReportResource(profitService);
    }

    @Test
    void getProfits_shouldReturnOk() {
        Response response = resource.getProfits();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}