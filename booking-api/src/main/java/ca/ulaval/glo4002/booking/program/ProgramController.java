package ca.ulaval.glo4002.booking.program;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;

import ca.ulaval.glo4002.booking.program.artists.ArtistListDto;
import ca.ulaval.glo4002.booking.program.artists.ArtistService;

@Path("/program")
public class ProgramController {

	private final ArtistService artistService;
	private final ProgramService programService;

	@Inject
	public ProgramController(ArtistService artistService, ProgramService programService) {
		this.artistService = artistService;
		this.programService = programService;
	}

	@GET
	@Path("/artists")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> getArtists(@QueryParam("orderBy") String orderBy) {
		ArtistListDto artistListDto;

		// Make orderBy nullable in artist service
		if (orderBy == null) {
			artistListDto = artistService.getAllUnordered();
		} else {
			artistListDto = artistService.getAllOrdered(orderBy);
		}

		return ResponseEntity.ok().body(artistListDto);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> add(ProgramDto programDto) {
		programService.add(programDto);

		return ResponseEntity.ok().build();
	}
}
