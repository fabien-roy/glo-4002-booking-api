package ca.ulaval.glo4002.booking.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.booking.domainObjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.parsers.OxygenTankParser;
import ca.ulaval.glo4002.booking.services.OxygenTankService;

@Path("report")
public class ReportController {

	private final OxygenTankService oxygenTankService;
	private final OxygenTankParser oxygenTankParser;

	public ReportController(OxygenTankService oxygenTankService, OxygenTankParser oxygenTankParser) {
		this.oxygenTankService = oxygenTankService;
		this.oxygenTankParser = oxygenTankParser;
	}

	@GET
	@Path("/o2")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OxygenTank> getOxygenTanks() {
		List<OxygenTank> oxygenTanks = new ArrayList<>();
		oxygenTankService.findAll().forEach(oxygenTanks::add);
		Collections.sort(oxygenTanks, Comparator.comparing(OxygenTank::getTimeRequested));
		return oxygenTanks;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addOxygenTank(OxygenTank tank) {
		oxygenTankService.save(tank);
		return Response.status(Response.Status.CREATED.getStatusCode()).build();
	}
}
