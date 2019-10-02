package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.domainobjects.report.Report;
import ca.ulaval.glo4002.booking.dto.ReportDto;
import ca.ulaval.glo4002.booking.exceptions.ControllerException;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;
import ca.ulaval.glo4002.booking.parsers.ReportParser;
import ca.ulaval.glo4002.booking.repositories.InventoryRepository;
import ca.ulaval.glo4002.booking.repositories.InventoryRepositoryImpl;
import ca.ulaval.glo4002.booking.repositories.OxygenTankRepository;
import ca.ulaval.glo4002.booking.repositories.OxygenTankRepositoryImpl;
import ca.ulaval.glo4002.booking.services.*;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/report")
public class ReportController {

	private final ReportService reportService;
	private final ReportParser reportParser;

	private final InventoryRepository inventoryRepository;
	private final OxygenTankRepository oxygenTankRepository;
	private final InventoryService inventoryService;
	private final HistoryService historyService;
	private final OxygenTankService oxygenTankService;

	public ReportController() {
	    // TODO : OXY : Inject this
		this.inventoryRepository = new InventoryRepositoryImpl();
		this.oxygenTankRepository = new OxygenTankRepositoryImpl();
		this.inventoryService = new InventoryServiceImpl(inventoryRepository);
		this.oxygenTankService = new OxygenTankServiceImpl(oxygenTankRepository, inventoryService);
		this.historyService = new HistoryServiceImpl(oxygenTankService);

		this.reportService = new ReportServiceImpl(inventoryService, historyService);
		this.reportParser = new ReportParser();
	}

	public ReportController(ReportService reportService, ReportParser reportParser, OxygenTankRepository oxygenTankRepository, InventoryRepository inventoryRepository, InventoryService inventoryService, HistoryService historyService, OxygenTankService oxygenTankService) {
	    // TODO : OXY : Only report service and parser should be injected
		this.reportService = reportService;
		this.reportParser = reportParser;

		this.oxygenTankRepository = oxygenTankRepository;
		this.inventoryRepository = inventoryRepository;
		this.inventoryService = inventoryService;
		this.historyService = historyService;
		this.oxygenTankService = oxygenTankService;
	}

	@GET
	@Path("/o2")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> getOxygenTanks() {
		Report report = reportService.getReport();
		ReportDto dto;

		try {
			dto = reportParser.toDto(report);
        } catch (ControllerException exception) {
            return ResponseEntity.status(exception.getHttpStatus()).body(exception.toErrorDto());
        } catch (FestivalException exception) {
            return ResponseEntity.notFound().build();
        }

		return ResponseEntity.ok().body(dto);
	}
}
