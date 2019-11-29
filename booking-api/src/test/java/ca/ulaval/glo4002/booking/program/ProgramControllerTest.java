package ca.ulaval.glo4002.booking.program;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4002.booking.errors.ExceptionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ca.ulaval.glo4002.booking.program.artists.ArtistListDto;
import ca.ulaval.glo4002.booking.program.artists.ArtistOrderings;
import ca.ulaval.glo4002.booking.program.artists.ArtistService;

class ProgramControllerTest {

    private ProgramController controller;
    private ProgramService programService;
    private ArtistService artistService;

    @BeforeEach
    void setUpController() {
        ExceptionMapper exceptionMapper = new ExceptionMapper();
        artistService = mock(ArtistService.class);
        programService = mock(ProgramService.class);

        controller = new ProgramController(exceptionMapper, artistService, programService);
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

    @Test
    void addProgram_shouldReturnOk() {
        ResponseEntity<?> response = controller.add(mock(ProgramDto.class));

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}