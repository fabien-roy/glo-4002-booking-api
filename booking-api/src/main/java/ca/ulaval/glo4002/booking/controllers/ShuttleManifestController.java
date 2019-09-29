package ca.ulaval.glo4002.booking.controllers;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.entities.shuttles.ShuttleManifest;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;
import ca.ulaval.glo4002.booking.validators.FestivalDateValidator;

@Path("/shuttle-manifests")
@Produces(MediaType.APPLICATION_JSON)
public class ShuttleManifestController {
	//TODO : Return a real shuttle manifest using a manifest builder
	@GET
	public Response getShuttleManifests(@QueryParam("date") String date) {
		LocalDate manifestDate = LocalDate.parse(date);
		
		if (!FestivalDateValidator.dateIsOutsideFestivalDates(manifestDate)) {
			//TODO : manifest generation logic
		} else throw new FestivalException(ExceptionConstants.INVALID_EVENT_DATE_MESSAGE);
		
		return Response.ok().entity(new ShuttleManifest(manifestDate, new ArrayList<>(), new ArrayList<>())).build();
		
	}

}
