package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.domainObjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.exceptions.shuttles.ShuttleNotFoundException;
import ca.ulaval.glo4002.booking.repositories.ShuttleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/shuttles")
public class ShuttleController {

	@Autowired
	private ShuttleRepository shuttleRepository;

	public ShuttleController(ShuttleRepository shuttleRepository) {
		this.shuttleRepository = shuttleRepository;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Shuttle> getShuttles() {
		List<Shuttle> shuttles = new ArrayList<>();
		shuttleRepository.findAll().forEach(shuttles::add);
		return shuttles;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Shuttle getShuttleById(@PathParam("id") Long entityId) {
		return shuttleRepository.findById(entityId).orElseThrow(ShuttleNotFoundException::new);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addShuttle(Shuttle shuttle) {
		shuttleRepository.save(shuttle);
		return Response.status(Response.Status.CREATED.getStatusCode()).build();
	}
}
