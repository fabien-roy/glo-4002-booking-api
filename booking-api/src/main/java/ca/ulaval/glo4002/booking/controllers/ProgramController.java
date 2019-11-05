package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.dto.events.ProgramDto;
import ca.ulaval.glo4002.booking.dto.orders.OrderWithPassesAsEventDatesDto;
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
    @Path("/artists/}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> getArtists(@QueryParam("orderBy") String orderBy) {
        // TODO : artistService.get(orderBy)

        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> addOrder(ProgramDto programDto) {
        // TODO : programService.add(programDto)

        return null;
    }
}
