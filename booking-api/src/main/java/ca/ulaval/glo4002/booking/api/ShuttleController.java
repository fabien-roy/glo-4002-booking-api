package ca.ulaval.glo4002.booking.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4002.booking.data.transport.Shuttle;
import ca.ulaval.glo4002.booking.data.transport.ShuttleNotFoundException;
import ca.ulaval.glo4002.booking.repositories.ShuttleRepository;

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
