package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.domainObjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.repositories.OxygenRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Path("report")
public class ReportController {

    @Autowired
    private OxygenRepository oxygenRepository;

    public ReportController(OxygenRepository oxygenRepository) { this.oxygenRepository = oxygenRepository; }

    @GET
    @Path("/o2")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OxygenTank> getOxygenTanks() {
        List<OxygenTank> oxygenTanks = new ArrayList<>();
        oxygenRepository.findAll().forEach(oxygenTanks::add);
        Collections.sort(oxygenTanks, Comparator.comparing(OxygenTank::getTimeRequested));
        return oxygenTanks;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOxygenTank(OxygenTank tank){
        oxygenRepository.save(tank);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }
}
