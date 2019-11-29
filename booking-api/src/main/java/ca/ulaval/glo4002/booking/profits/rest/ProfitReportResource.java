package ca.ulaval.glo4002.booking.profits.rest;

import ca.ulaval.glo4002.booking.profits.services.ProfitService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/report")
@Produces(MediaType.APPLICATION_JSON)
public class ProfitReportResource {

    private final ProfitService profitService;

    @Inject
    public ProfitReportResource(ProfitService profitService) {
        this.profitService = profitService;
    }

    @GET
    @Path("/profits")
    public Response getProfits() {
        ProfitResponse profitResponse = profitService.getReport();

        return Response.ok().entity(profitResponse).build();
    }
}
