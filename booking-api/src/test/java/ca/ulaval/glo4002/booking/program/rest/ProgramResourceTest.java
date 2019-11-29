package ca.ulaval.glo4002.booking.program.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4002.booking.program.services.ProgramService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.program.artists.rest.ArtistListResponse;
import ca.ulaval.glo4002.booking.program.artists.domain.ArtistOrderings;
import ca.ulaval.glo4002.booking.program.artists.services.ArtistService;

import javax.ws.rs.core.Response;

class ProgramResourceTest {

    private ProgramResource controller;
    private ProgramService programService;
    private ArtistService artistService;

    @BeforeEach
    void setUpResource() {
        artistService = mock(ArtistService.class);
        programService = mock(ProgramService.class);

        controller = new ProgramResource(artistService, programService);
    }

    @Test
    void getArtistsOrderedValidOrdering_shouldReturnOk() {
        String ordering = ArtistOrderings.MOST_POPULAR.toString();
        when(artistService.getAllOrdered(ordering)).thenReturn(mock(ArtistListResponse.class));

        Response response = controller.getArtists(ordering);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    void getArtistsUnordered_shouldReturnOk() {
        when(artistService.getAllUnordered()).thenReturn(mock(ArtistListResponse.class));

        Response response = controller.getArtists(null);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    void getArtistsOrderedInValidOrdering_shouldReturnOk() {
        String ordering = "A string";
        when(artistService.getAllOrdered(ordering)).thenReturn(mock(ArtistListResponse.class));

        Response response = controller.getArtists(ordering);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    void addProgram_shouldReturnOk() {
        Response response = controller.add(mock(ProgramRequest.class));

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}