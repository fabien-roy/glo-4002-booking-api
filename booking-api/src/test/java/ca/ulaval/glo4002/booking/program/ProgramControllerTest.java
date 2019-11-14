package ca.ulaval.glo4002.booking.program;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4002.booking.program.ProgramController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ca.ulaval.glo4002.booking.artists.ArtistListDto;
import ca.ulaval.glo4002.booking.artists.ArtistOrderings;
import ca.ulaval.glo4002.booking.artists.ArtistService;
import ca.ulaval.glo4002.booking.program.ProgramService;

class ProgramControllerTest {

    private ProgramController controller;
    private ProgramService programService;
    private ArtistService artistService;

    @BeforeEach
    void setUpController() {
        programService = mock(ProgramService.class);
        artistService = mock(ArtistService.class);

        controller = new ProgramController(artistService);
    }

    @Test
    void getArtistsOrderedValidOrdering_shouldReturnOk() {
        String ordering = ArtistOrderings.MOST_POPULAR.toString();
        when(artistService.getAllOrdered(ordering)).thenReturn(mock(ArtistListDto.class));

        ResponseEntity<?> response = controller.getArtists(ordering);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getArtistsUnordered_shouldReturnOk() {
        when(artistService.getAllUnordered()).thenReturn(mock(ArtistListDto.class));

        ResponseEntity<?> response = controller.getArtists(null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getArtistsOrderedInValidOrdering_shouldReturnOk() {
        String ordering = "A string";
        when(artistService.getAllOrdered(ordering)).thenReturn(mock(ArtistListDto.class));

        ResponseEntity<?> response = controller.getArtists(ordering);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}