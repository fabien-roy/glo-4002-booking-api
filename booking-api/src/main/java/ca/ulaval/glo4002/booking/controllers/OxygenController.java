package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.entities.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.repositories.OxygenRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/o2")
public class OxygenController {

    @Autowired
    private OxygenRepository oxygenRepository;

    public OxygenController(OxygenRepository oxygenRepository) { this.oxygenRepository = oxygenRepository; }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOxygenTank(OxygenTank oxygenTank) {
        oxygenRepository.save(oxygenTank);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }
}
