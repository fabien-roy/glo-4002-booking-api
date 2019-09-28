package ca.ulaval.glo4002.booking.controllers;

import java.util.Date;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;

@Path("/shuttle-manifests")
@Produces(MediaType.APPLICATION_JSON)
public class ShuttleManifestController {
	
	public Response getShuttleManifests(@QueryParam("date") @DateTimeFormat
			(iso = DateTimeFormat.ISO) LocalDate date) {
		
	}

}
