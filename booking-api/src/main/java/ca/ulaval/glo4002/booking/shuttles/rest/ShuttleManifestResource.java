package ca.ulaval.glo4002.booking.shuttles.rest;

import ca.ulaval.glo4002.booking.shuttles.services.ShuttleManifestService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/shuttle-manifests")
@Produces(MediaType.APPLICATION_JSON)
public class ShuttleManifestResource {

	private final ShuttleManifestService service;

	@Inject
	public ShuttleManifestResource(ShuttleManifestService service) {
		this.service = service;
	}

	@GET
	public Response get(@QueryParam("date") String date) {
		ShuttleManifestResponse shuttleManifestResponse;

		if (date == null) {
			shuttleManifestResponse = service.getTrips();
		} else {
			shuttleManifestResponse = service.getTripsForDate(date);
		}

		return Response.ok().entity(shuttleManifestResponse).build();
	}
}
