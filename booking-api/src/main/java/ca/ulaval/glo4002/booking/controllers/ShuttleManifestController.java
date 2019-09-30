package ca.ulaval.glo4002.booking.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.http.ResponseEntity;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.domainObjects.shuttles.ShuttleManifest;
import ca.ulaval.glo4002.booking.dto.ShuttleManifestDto;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;
import ca.ulaval.glo4002.booking.parsers.OrderParser;
import ca.ulaval.glo4002.booking.services.OrderService;
import ca.ulaval.glo4002.booking.services.ShuttleService;
import ca.ulaval.glo4002.booking.validators.FestivalDateValidator;

@Path("/shuttle-manifests")
public class ShuttleManifestController {
	
	private final ShuttleService shuttleService;
	private final OrderService orderService;
	private final OrderParser orderParser;
	
	
	public ShuttleManifestController(ShuttleService shuttleService, OrderService orderService,
			OrderParser orderParser) {
		this.shuttleService = shuttleService;
		this.orderService = orderService;
		this.orderParser = orderParser;
	}


	//TODO : Return a real shuttle manifest using a manifest builder
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<List<ShuttleManifestDto>> getShuttleManifestsWithDate(@QueryParam("date") String date) {
		LocalDate manifestDate = LocalDate.parse(date);
		
		if (!FestivalDateValidator.isOutsideFestivalDates(manifestDate)) {
			//TODO : manifest generation logic
		} else throw new FestivalException(ExceptionConstants.INVALID_EVENT_DATE_MESSAGE);
		
		//TODO: implement response with date param
		return null;
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<List<ShuttleManifestDto>> getShuttleManifestsWithoutDate() {
		//TODO : implement response without date param
		return null;
	}

}
