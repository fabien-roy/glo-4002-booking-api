package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.entities.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.repositories.OxygenRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
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
        return oxygenTanks;
    }
}
