package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.dto.ShuttleManifestDto;
import ca.ulaval.glo4002.booking.exceptions.HumanReadableException;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;
import ca.ulaval.glo4002.booking.parsers.ShuttleManifestParser;
import ca.ulaval.glo4002.booking.repositories.*;
import ca.ulaval.glo4002.booking.services.*;
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

	public ShuttleManifestController() {
		PassengerService passengerService = new PassengerServiceImpl(new PassengerRepositoryImpl());
		ShuttleService shuttleService = new ShuttleServiceImpl(new ShuttleRepositoryImpl(), passengerService);
		ShuttleInventoryService shuttleInventoryService = new ShuttleInventoryServiceImpl(new ShuttleInventoryRepositoryImpl(), shuttleService);

		this.shuttleManifestService = new ShuttleManifestServiceImpl(shuttleInventoryService);
		this.shuttleManifestParser = new ShuttleManifestParser();
	}

	public ShuttleManifestController(ShuttleManifestService shuttleManifestService) {
		this.shuttleManifestService = shuttleManifestService;
        this.shuttleManifestParser = new ShuttleManifestParser();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> getShuttleManifestsWithDate(@QueryParam("date") String date) {
		ShuttleManifestDto dto;

	    if (date == null) {
			try {
				dto = shuttleManifestParser.toDto(shuttleManifestService.findAll());
			} catch (HumanReadableException exception) {
				return ResponseEntity.status(exception.getHttpStatus()).body(exception.toErrorDto());
			} catch (FestivalException exception) {
				return ResponseEntity.notFound().build();
			}
		} else {
			LocalDate manifestDate = LocalDate.parse(date);

			try {
				dto = shuttleManifestParser.toDto(shuttleManifestService.findByDate(manifestDate));
			} catch (HumanReadableException exception) {
				return ResponseEntity.status(exception.getHttpStatus()).body(exception.toErrorDto());
			} catch (FestivalException exception) {
				return ResponseEntity.notFound().build();
			}
		}

        return ResponseEntity.ok().body(dto);
	}
}
