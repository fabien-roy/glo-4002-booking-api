package ca.ulaval.glo4002.booking.report.rest;

import ca.ulaval.glo4002.booking.profits.services.ProfitService;
import ca.ulaval.glo4002.booking.profits.rest.ProfitResponse;
import ca.ulaval.glo4002.booking.oxygen.report.rest.OxygenReportDto;
import ca.ulaval.glo4002.booking.oxygen.report.services.OxygenReportService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// TODO : Separate ReportController into two controllers

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
        ProfitResponse profitResponse = profitService.getReport();

        return Response.ok().entity(profitResponse).build();
    }
}
