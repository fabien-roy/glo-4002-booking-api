package ca.ulaval.glo4002.booking.report;

import ca.ulaval.glo4002.booking.exceptions.ExceptionMapper;
import ca.ulaval.glo4002.booking.oxygen.report.OxygenReportService;
import ca.ulaval.glo4002.booking.profits.ProfitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class ReportControllerTest {

    private ReportController controller;

    @BeforeEach
    void setUpController() {
        ExceptionMapper exceptionMapper = new ExceptionMapper();
        OxygenReportService oxygenReportService = mock(OxygenReportService.class);
        ProfitService profitService = mock(ProfitService.class);

        controller = new ReportController(exceptionMapper, oxygenReportService, profitService);
    }

    @Test
    void getOxygenReport_shouldReturnOk() {
        ResponseEntity<?> response = controller.getOxygenReport();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getProfits_shouldReturnOk() {
        ResponseEntity<?> response = controller.getProfits();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}