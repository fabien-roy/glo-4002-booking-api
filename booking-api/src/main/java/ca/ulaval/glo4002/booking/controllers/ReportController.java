package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.domainobjects.report.Report;
import ca.ulaval.glo4002.booking.dto.ReportDto;
import ca.ulaval.glo4002.booking.exceptions.ControllerException;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;
import ca.ulaval.glo4002.booking.parsers.ReportParser;
import ca.ulaval.glo4002.booking.repositories.*;
import ca.ulaval.glo4002.booking.services.*;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/report")
public class ReportController {

	private final ReportService reportService;
	private final ReportParser reportParser = new ReportParser();

	private final InventoryItemRepository inventoryItemRepository;
	private final InventoryRepository inventoryRepository;
	private final OxygenTankRepository oxygenTankRepository;
	private final InventoryItemService inventoryItemService;
	private final InventoryService inventoryService;
	private final HistoryService historyService;

	public ReportController() {
	    // TODO : OXY : Inject this
		this.inventoryItemRepository = new InventoryItemRepositoryImpl();
		this.inventoryRepository = new InventoryRepositoryImpl();
		this.oxygenTankRepository = new OxygenTankRepositoryImpl();
        this.inventoryItemService = new InventoryItemServiceImpl(inventoryItemRepository);
		this.inventoryService = new InventoryServiceImpl(inventoryRepository, inventoryItemService);
		OxygenTankService oxygenTankService = new OxygenTankServiceImpl(oxygenTankRepository, inventoryService);
		this.historyService = new HistoryServiceImpl(oxygenTankService);

		this.reportService = new ReportServiceImpl(inventoryService, historyService);
	}

	public ReportController(ReportService reportService, OxygenTankRepository oxygenTankRepository, InventoryItemRepository inventoryItemRepository, InventoryRepository inventoryRepository, InventoryItemService inventoryItemService, InventoryService inventoryService, HistoryService historyService) {
	    // TODO : OXY : Only report service and parser should be injected
		this.reportService = reportService;

		this.inventoryItemRepository = inventoryItemRepository;
		this.oxygenTankRepository = oxygenTankRepository;
		this.inventoryRepository = inventoryRepository;
		this.inventoryItemService = inventoryItemService;
		this.inventoryService = inventoryService;
		this.historyService = historyService;
	}

	@GET
	@Path("/o2")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> getOxygenTanks() {
		ReportDto dto;

		try {
			Report report = reportService.getReport();
			dto = reportParser.toDto(report);
        } catch (ControllerException exception) {
            return ResponseEntity.status(exception.getHttpStatus()).body(exception.toErrorDto());
        } catch (FestivalException exception) {
            return ResponseEntity.notFound().build();
        }

		return ResponseEntity.ok().body(dto);
	}
}
