package ca.ulaval.glo4002.booking.oxygen.report.rest;

import ca.ulaval.glo4002.booking.oxygen.report.services.OxygenReportService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/report")
@Produces(MediaType.APPLICATION_JSON)
public class OxygenReportResource {

    private final OxygenReportService oxygenReportService;

    @Inject
    public OxygenReportResource(OxygenReportService oxygenReportService) {
        this.oxygenReportService = oxygenReportService;
    }

    @GET
    @Path("/o2")
    public Response getOxygenReport() {
        OxygenReportDto oxygenReportDto = oxygenReportService.getOxygenReport();

        return Response.ok().entity(oxygenReportDto).build();
    }
}
