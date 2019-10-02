package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.domainobjects.shuttles.ShuttleManifest;
import ca.ulaval.glo4002.booking.parsers.ShuttleManifestParser;
import ca.ulaval.glo4002.booking.services.ShuttleManifestService;
import ca.ulaval.glo4002.booking.util.FestivalDateUtil;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;

@Path("/shuttle-manifests")
public class ShuttleManifestController {
	
	private final ShuttleManifestService shuttleManifestService;
    private final ShuttleManifestParser shuttleManifestParser;

	public ShuttleManifestController(ShuttleManifestService shuttleManifestService, ShuttleManifestParser shuttleManifestParser) {
		this.shuttleManifestService = shuttleManifestService;
        this.shuttleManifestParser = shuttleManifestParser;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> getShuttleManifestsWithDate(@QueryParam("date") String date) {
        ShuttleManifest shuttleManifest;
		LocalDate manifestDate = LocalDate.parse(date);

		// TODO : TRANS : Use same logic as other controllers to throw with an ErrorDto
		if (!FestivalDateUtil.isOutsideFestivalDates(manifestDate)) {
		    shuttleManifest = shuttleManifestService.findByDate(manifestDate);
        } else if(date == null) {
        	shuttleManifest = shuttleManifestService.findAll();
        } else {
        	return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(shuttleManifestParser.toDto(shuttleManifest));
	}
}
