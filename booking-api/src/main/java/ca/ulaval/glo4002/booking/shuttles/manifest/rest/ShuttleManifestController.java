package ca.ulaval.glo4002.booking.shuttles.manifest.rest;

import ca.ulaval.glo4002.booking.shuttles.manifest.services.ShuttleManifestService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/shuttle-manifests")
public class ShuttleManifestController {

	private final ShuttleManifestService service;

	@Inject
	public ShuttleManifestController(ShuttleManifestService service) {
		this.service = service;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@QueryParam("date") String date) {
		ShuttleManifestDto shuttleManifestDto;

		if (date == null) {
			shuttleManifestDto = service.getTrips();
		} else {
			shuttleManifestDto = service.getTripsForDate(date);
		}

		return Response.ok().entity(shuttleManifestDto).build();
	}
}