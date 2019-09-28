package ca.ulaval.glo4002.booking.controllers;

import java.time.LocalDate;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response.Status;

import org.springframework.http.MediaType;

import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.exceptions.InvalidDateException;
import ca.ulaval.glo4002.booking.responses.ShuttleManifestResponse;

@Path("/shuttle-manifests")
public class ShuttleManifestController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ShuttleManifestResponse getShuttleManifests(@QueryParam("date") String date) {
		LocalDate manifestDate = LocalDate.parse(date);
		
		if (!dateIsOutsideFestivalDates(manifestDate)) {
			
		} else throw new InvalidDateException(Status.BAD_REQUEST);
		
		return null;
		
	}
	
	public boolean dateIsOutsideFestivalDates(LocalDate date) {
		return date.isBefore(FestivalConstants.Dates.START_DATE) 
				|| date.isAfter(FestivalConstants.Dates.END_DATE);
	}

}
