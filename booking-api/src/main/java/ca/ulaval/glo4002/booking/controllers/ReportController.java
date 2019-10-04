package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.domainobjects.report.Report;
import ca.ulaval.glo4002.booking.dto.ReportDto;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;
import ca.ulaval.glo4002.booking.exceptions.HumanReadableException;
import ca.ulaval.glo4002.booking.parsers.ReportParser;
import ca.ulaval.glo4002.booking.repositories.OxygenTankInventoryRepositoryImpl;
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
	private final ReportParser reportParser = new ReportParser();

	public ReportController() {
		OxygenTankService oxygenTankService = new OxygenTankServiceImpl(new OxygenTankRepositoryImpl());
		OxygenTankInventoryService oxygenTankInventoryService = new OxygenTankInventoryServiceImpl(new OxygenTankInventoryRepositoryImpl(), oxygenTankService);
		HistoryService historyService = new HistoryServiceImpl(oxygenTankService);

		this.reportService = new ReportServiceImpl(oxygenTankInventoryService, historyService);
	}

	public ReportController(ReportService reportService) {
		this.reportService = reportService;
	}

	@GET
	@Path("/o2")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> getOxygenTanks() {
		ReportDto dto;

		try {
			Report report = reportService.getReport();
			dto = reportParser.toDto(report);
        } catch (HumanReadableException exception) {
            return ResponseEntity.status(exception.getHttpStatus()).body(exception.toErrorDto());
        } catch (FestivalException exception) {
            return ResponseEntity.notFound().build();
        }

		return ResponseEntity.ok().body(dto);
	}
}
