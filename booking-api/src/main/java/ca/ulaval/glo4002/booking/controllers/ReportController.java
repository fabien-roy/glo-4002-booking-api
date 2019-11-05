package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.services.ProfitService;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/report")
public class ReportController {

    private final ProfitService profitService;

    @Inject
    public ReportController(ProfitService profitService) {
        this.profitService = profitService;
    }

    @GET
    @Path("/profits")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> getProfits() {
        // TODO : profitService.get()

        return null;
    }
}
