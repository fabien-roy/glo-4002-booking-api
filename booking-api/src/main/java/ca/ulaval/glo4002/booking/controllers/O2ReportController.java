package ca.ulaval.glo4002.booking.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ca.ulaval.glo4002.booking.dto.*;
import org.springframework.http.ResponseEntity;

import ca.ulaval.glo4002.booking.domainObjects.oxygen.OxygenTank;
//TODO : Activate when implemented
//import ca.ulaval.glo4002.booking.parsers.HistoryItemParser;
import ca.ulaval.glo4002.booking.parsers.OxygenTankParser;
import ca.ulaval.glo4002.booking.services.OxygenTankService;

@Path("report")
public class O2ReportController {

	private final OxygenTankService oxygenTankService;
	private final OxygenTankParser oxygenTankParser;
	//private final HistoryItemParser historyItemParser;

	public O2ReportController(OxygenTankService oxygenTankService, OxygenTankParser oxygenTankParser) {
		this.oxygenTankService = oxygenTankService;
		this.oxygenTankParser = oxygenTankParser;
		//this.historyItemParser = historyItemParser;
	}

	@GET
	@Path("/o2")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<O2ReportDto> getOxygenTanks() {
		List<OxygenTank> oxygenTanks = new ArrayList<>();

		oxygenTankService.findAll().forEach(oxygenTanks::add);

		O2ReportDto report = new O2ReportDto();
		List<InventoryItemDto> inventoryItemDtos = new ArrayList<>();
		// TODO : Implements OxygenTankInventoryServiceImpl.getInventory() will return values already contained in OxygenTankInventory(domain entities)
		// In the form of a List<InventoryItemDto> I don't think there is a need for a Parser in that case
		List<HistoryItemDto> historyItemDtos = new ArrayList<>();
		//TODO : implements 
		//oxygenTanks.forEach(tank -> historyItemDtos.add(historyItemParser.toDto(tank)));

		return ResponseEntity.ok().body(report);
	}

	// TODO : To delete oxygenTank does not take post
	/*@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addOxygenTank(OxygenTank tank) {
		oxygenTankService.save(tank);
		return Response.status(Response.Status.CREATED.getStatusCode()).build();
	}*/
}
