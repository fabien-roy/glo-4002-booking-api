package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.dto.ShuttleManifestDto;
import ca.ulaval.glo4002.booking.exceptions.HumanReadableException;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;
import ca.ulaval.glo4002.booking.parsers.ShuttleManifestParser;
import ca.ulaval.glo4002.booking.services.ShuttleManifestService;
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
	    if (date == null) {
			return ResponseEntity.badRequest().build();
		}

		ShuttleManifestDto dto;
		LocalDate manifestDate = LocalDate.parse(date);

        try {
            dto = shuttleManifestParser.toDto(shuttleManifestService.findByDate(manifestDate));
        } catch (HumanReadableException exception) {
            return ResponseEntity.status(exception.getHttpStatus()).body(exception.toErrorDto());
        } catch (FestivalException exception) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(dto);
	}
}
