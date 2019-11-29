package ca.ulaval.glo4002.booking.oxygen.report.rest;

import ca.ulaval.glo4002.booking.oxygen.report.services.OxygenReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class OxygenReportResourceTest {

    private OxygenReportResource resource;

    @BeforeEach
    void setUpResource() {
        OxygenReportService oxygenReportService = mock(OxygenReportService.class);

        resource = new OxygenReportResource(oxygenReportService);
    }

    @Test
    void getOxygenReport_shouldReturnOk() {
        Response response = resource.getOxygenReport();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}