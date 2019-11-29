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
import ca.ulaval.glo4002.booking.errors.ExceptionMapper;

@Path("/program")
public class ProgramController {

    private final ExceptionMapper exceptionMapper;
	private final ArtistService artistService;
	private final ProgramService programService;

	@Inject
	public ProgramController(ExceptionMapper exceptionMapper, ArtistService artistService, ProgramService programService) {
		this.exceptionMapper = exceptionMapper;
		this.artistService = artistService;
		this.programService = programService;
	}

	@GET
	@Path("/artists")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> getArtists(@QueryParam("orderBy") String orderBy) {
		ArtistListDto artistListDto;

		try {
			if (orderBy == null) {
				artistListDto = artistService.getAllUnordered();
			} else {
				artistListDto = artistService.getAllOrdered(orderBy);
			}
		} catch (Exception exception) {
			return exceptionMapper.mapError(exception);
		}

		return ResponseEntity.ok().body(artistListDto);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> add(ProgramDto programDto) {
		try {
			programService.add(programDto);
		} catch (Exception exception) {
			return exceptionMapper.mapError(exception);
		}

		return ResponseEntity.ok().build();
	}
}
