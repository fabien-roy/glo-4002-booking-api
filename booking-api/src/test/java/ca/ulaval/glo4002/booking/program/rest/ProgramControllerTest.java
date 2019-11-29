package ca.ulaval.glo4002.booking.program.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4002.booking.program.rest.ProgramController;
import ca.ulaval.glo4002.booking.program.rest.ProgramDto;
import ca.ulaval.glo4002.booking.program.services.ProgramService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.program.artists.rest.ArtistListDto;
import ca.ulaval.glo4002.booking.program.artists.domain.ArtistOrderings;
import ca.ulaval.glo4002.booking.program.artists.services.ArtistService;

import javax.ws.rs.core.Response;

class ProgramControllerTest {

    private ProgramController controller;
    private ProgramService programService;
    private ArtistService artistService;

    @BeforeEach
    void setUpController() {
        artistService = mock(ArtistService.class);
        programService = mock(ProgramService.class);

        controller = new ProgramController(artistService, programService);
    }

    @Test
    void getArtistsOrderedValidOrdering_shouldReturnOk() {
        String ordering = ArtistOrderings.MOST_POPULAR.toString();
        when(artistService.getAllOrdered(ordering)).thenReturn(mock(ArtistListDto.class));

        Response response = controller.getArtists(ordering);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    void getArtistsUnordered_shouldReturnOk() {
        when(artistService.getAllUnordered()).thenReturn(mock(ArtistListDto.class));

        Response response = controller.getArtists(null);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    void getArtistsOrderedInValidOrdering_shouldReturnOk() {
        String ordering = "A string";
        when(artistService.getAllOrdered(ordering)).thenReturn(mock(ArtistListDto.class));

        Response response = controller.getArtists(ordering);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    void addProgram_shouldReturnOk() {
        Response response = controller.add(mock(ProgramDto.class));

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}