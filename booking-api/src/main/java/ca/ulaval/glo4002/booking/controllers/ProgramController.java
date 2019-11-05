package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.dto.events.ArtistListDto;
import ca.ulaval.glo4002.booking.dto.events.ProgramDto;
import ca.ulaval.glo4002.booking.exceptions.BookingException;
import ca.ulaval.glo4002.booking.services.ArtistService;
import ca.ulaval.glo4002.booking.services.ProgramService;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/program")
public class ProgramController {

    private final ProgramService programService;
    private final ArtistService artistService;

    @Inject
    public ProgramController(ProgramService programService, ArtistService artistService) {
        this.programService = programService;
        this.artistService = artistService;
    }

    @GET
    @Path("/artists/")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> getArtists(@QueryParam("orderBy") String orderBy) {
        ArtistListDto artistListDto;

        try {
            artistListDto = artistService.getAll(orderBy);
        } catch (BookingException exception) {
            return ResponseEntity.status(exception.getStatus()).body(exception.toErrorDto());
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(artistListDto);
    }

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
}
