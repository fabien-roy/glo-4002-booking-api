package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.dto.ProfitsDto;
import ca.ulaval.glo4002.booking.dto.events.ArtistListDto;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenReportDto;
import ca.ulaval.glo4002.booking.exceptions.BookingException;
import ca.ulaval.glo4002.booking.services.OxygenReportService;
import ca.ulaval.glo4002.booking.services.ProfitService;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/report")
public class ReportController {

    private final OxygenReportService oxygenReportService;
    private final ProfitService profitService;

    @Inject
    public ReportController(OxygenReportService oxygenReportService, ProfitService profitService) {
        this.oxygenReportService = oxygenReportService;
        this.profitService = profitService;
    }

    @GET
    @Path("/o2")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> getOxygenReport() {
        OxygenReportDto oxygenReportDto = oxygenReportService.getOxygenReport();

        return ResponseEntity.ok().body(oxygenReportDto);
    }

    @GET
    @Path("/profits")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> getProfits() {
        ProfitsDto profitsDto = profitService.get();

        return ResponseEntity.ok().body(profitsDto);
    }
}
