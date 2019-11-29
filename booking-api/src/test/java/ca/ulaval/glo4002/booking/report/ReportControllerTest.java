package ca.ulaval.glo4002.booking.report;

import ca.ulaval.glo4002.booking.oxygen.report.services.OxygenReportService;
import ca.ulaval.glo4002.booking.profits.services.ProfitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class ReportControllerTest {

    private ReportController controller;

    @BeforeEach
    void setUpController() {
        OxygenReportService oxygenReportService = mock(OxygenReportService.class);
        ProfitService profitService = mock(ProfitService.class);

        controller = new ReportController(oxygenReportService, profitService);
    }

    @Test
    void getOxygenReport_shouldReturnOk() {
        Response response = controller.getOxygenReport();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    void getProfits_shouldReturnOk() {
        Response response = controller.getProfits();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}