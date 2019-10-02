package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.domainobjects.report.Report;
import ca.ulaval.glo4002.booking.dto.ReportDto;
import ca.ulaval.glo4002.booking.exceptions.ControllerException;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;
import ca.ulaval.glo4002.booking.parsers.ReportParser;
import ca.ulaval.glo4002.booking.services.ReportService;
import ca.ulaval.glo4002.booking.services.ReportServiceImpl;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/report")
public class ReportController {

	private final ReportService reportService;
	private final ReportParser reportParser;

	public ReportController(ReportService reportService, ReportParser reportParser) {
		this.reportService = reportService;
		this.reportParser = reportParser;
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
