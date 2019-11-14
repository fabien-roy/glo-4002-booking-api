package ca.ulaval.glo4002.booking.program;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;

import ca.ulaval.glo4002.booking.artists.ArtistListDto;
import ca.ulaval.glo4002.booking.exceptions.BookingException;
import ca.ulaval.glo4002.booking.artists.ArtistService;

@Path("/program")
public class ProgramController {

    private final ArtistService artistService;

    @Inject
    public ProgramController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GET
    @Path("/artists")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> getArtists(@QueryParam("orderBy") String orderBy) {
        ArtistListDto artistListDto;

        try {
        	if(orderBy == null) {
        		artistListDto = artistService.getAllUnordered();
        	} else {
        		artistListDto = artistService.getAllOrdered(orderBy);
        	}
        } catch (BookingException exception) {
            return ResponseEntity.status(exception.getStatus()).body(exception.toErrorDto());
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(artistListDto);
    }
/*
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> add(ProgramDto programDto) {
        try {
            programService.add(programDto);
        } catch (BookingException exception) {
            return ResponseEntity.status(exception.getStatus()).body(exception.toErrorDto());
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    } 
    */
}
