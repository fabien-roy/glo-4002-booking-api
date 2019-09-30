package ca.ulaval.glo4002.booking.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.booking.domainObjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.dto.OxygenTankDto;
import ca.ulaval.glo4002.booking.parsers.OxygenTankParser;
import ca.ulaval.glo4002.booking.services.OxygenTankService;

@Path("/o2")
public class OxygenController {

	private final OxygenTankService oxygenTankService;
	private final OxygenTankParser oxygenTankParser;

	public OxygenController(OxygenTankService oxygenTankService, OxygenTankParser oxygenTankParser) {
		this.oxygenTankService = oxygenTankService;
		this.oxygenTankParser = oxygenTankParser;
	}

	// TODO : I think we don't need this method, oxygenTank will never require a post
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addOxygenTank(OxygenTank oxygenTank) {
		oxygenTankService.save(oxygenTank);
		return Response.status(Response.Status.CREATED.getStatusCode()).build();
	}

	// TODO : Test this
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<OxygenTankDto> getOxygenTank() {
		List<OxygenTank> oxygenTanks = new ArrayList<>();
		oxygenTankService.findAll().forEach(oxygenTanks::add);

		List<OxygenTankDto> oxygenTankDtos = new ArrayList<>();
		oxygenTanks.forEach(order -> oxygenTankDtos.add(oxygenTankParser.toDto(order)));

		return oxygenTankDtos;
	}
}
