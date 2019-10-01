package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.domainObjects.report.Report;
import ca.ulaval.glo4002.booking.dto.ReportDto;
import ca.ulaval.glo4002.booking.parsers.ReportParser;
import ca.ulaval.glo4002.booking.services.ReportServiceImpl;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/report")
public class ReportController {

	private final ReportServiceImpl reportService;
	private final ReportParser reportParser;

	public ReportController(ReportServiceImpl reportService, ReportParser reportParser) {
		this.reportService = reportService;
		this.reportParser = reportParser;
	}

	@GET
	@Path("/o2")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ReportDto> getOxygenTanks() {
		Report report = reportService.getReport();
		ReportDto dto;

        dto = reportParser.toDto(report);

		return ResponseEntity.ok().body(dto);
	}
}
