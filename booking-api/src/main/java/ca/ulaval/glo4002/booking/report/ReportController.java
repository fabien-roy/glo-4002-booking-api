package ca.ulaval.glo4002.booking.report;

import ca.ulaval.glo4002.booking.exceptions.ExceptionMapper;
import ca.ulaval.glo4002.booking.profits.ProfitService;
import ca.ulaval.glo4002.booking.profits.ProfitsDto;
import ca.ulaval.glo4002.booking.oxygen.report.OxygenReportDto;
import ca.ulaval.glo4002.booking.oxygen.report.OxygenReportService;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/report")
public class ReportController {

    private final ExceptionMapper exceptionMapper;
    private final OxygenReportService oxygenReportService;
    private final ProfitService profitService;

    @Inject
    public ReportController(ExceptionMapper exceptionMapper, OxygenReportService oxygenReportService, ProfitService profitService) {
        this.exceptionMapper = exceptionMapper;
        this.oxygenReportService = oxygenReportService;
        this.profitService = profitService;
    }

    @GET
    @Path("/o2")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> getOxygenReport() {
        OxygenReportDto oxygenReportDto = null;

        try {
            oxygenReportDto = oxygenReportService.getOxygenReport();
        } catch (Exception exception) {
            exceptionMapper.mapError(exception);
        }

        return ResponseEntity.ok().body(oxygenReportDto);
    }

    @GET
    @Path("/profits")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> getProfits() {
        ProfitsDto profitsDto = null;

        try {
            profitService.calculateProfit();
            profitsDto = profitService.get();
        } catch (Exception exception) {
            exceptionMapper.mapError(exception);
        }

        return ResponseEntity.ok().body(profitsDto);
    }
}
