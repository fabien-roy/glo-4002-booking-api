package ca.ulaval.glo4002.booking.report;

import ca.ulaval.glo4002.booking.profits.ProfitService;
import ca.ulaval.glo4002.booking.profits.ProfitsDto;
import ca.ulaval.glo4002.booking.oxygen.report.OxygenReportDto;
import ca.ulaval.glo4002.booking.oxygen.report.OxygenReportService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/report")
public class ReportController {

    private final OxygenReportService oxygenReportService;
    private final ProfitService profitService;

    @Inject
    public ReportController(OxygenReportService oxygenReportService, ProfitService profitService) {
        this.oxygenReportService = oxygenReportService;
        this.profitService = profitService;
    }

    @GET
    @Path("/o2")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOxygenReport() {
        OxygenReportDto oxygenReportDto = oxygenReportService.getOxygenReport();

        return Response.ok().entity(oxygenReportDto).build();
    }

    @GET
    @Path("/profits")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfits() {
        profitService.calculateProfit();
        ProfitsDto profitsDto = profitService.get();

        return Response.ok().entity(profitsDto).build();
    }
}
