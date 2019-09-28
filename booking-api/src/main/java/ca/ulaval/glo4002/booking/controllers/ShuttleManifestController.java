package ca.ulaval.glo4002.booking.controllers;

import java.time.LocalDate;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import ca.ulaval.glo4002.booking.entities.shuttles.ShuttleManifest;
import ca.ulaval.glo4002.booking.exceptions.InvalidDateException;
import ca.ulaval.glo4002.booking.validators.FestivalDateValidator;

@Path("/shuttle-manifests")
@Produces(MediaType.APPLICATION_JSON)
public class ShuttleManifestController {
	
	@GET
	public ShuttleManifest getShuttleManifests(@QueryParam("date") String date) {
		LocalDate manifestDate = LocalDate.parse(date);
		
		if (!FestivalDateValidator.dateIsOutsideFestivalDates(manifestDate)) {
			
		} else throw new InvalidDateException(Status.BAD_REQUEST);
		
		return null;
		
	}

}
