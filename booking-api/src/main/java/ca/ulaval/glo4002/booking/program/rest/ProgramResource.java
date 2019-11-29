package ca.ulaval.glo4002.booking.program.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.booking.program.services.ProgramService;
import ca.ulaval.glo4002.booking.program.artists.rest.ArtistListDto;
import ca.ulaval.glo4002.booking.program.artists.services.ArtistService;

@Path("/program")
@Produces(MediaType.APPLICATION_JSON)
public class ProgramResource {

	private final ArtistService artistService;
	private final ProgramService programService;

	@Inject
	public ProgramResource(ArtistService artistService, ProgramService programService) {
		this.artistService = artistService;
		this.programService = programService;
	}

	@GET
	@Path("/artists")
	public Response getArtists(@QueryParam("orderBy") String orderBy) {
		ArtistListDto artistListDto;

		// TODO : Make orderBy nullable in artist service
		if (orderBy == null) {
			artistListDto = artistService.getAllUnordered();
		} else {
			artistListDto = artistService.getAllOrdered(orderBy);
		}

		return Response.ok().entity(artistListDto).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(ProgramRequest programRequest) {
		programService.add(programRequest);

		return Response.ok().build();
	}
}
