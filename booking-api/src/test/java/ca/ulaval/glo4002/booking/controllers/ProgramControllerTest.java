package ca.ulaval.glo4002.booking.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ca.ulaval.glo4002.booking.dto.events.ArtistListDto;
import ca.ulaval.glo4002.booking.dto.events.ProgramDto;
import ca.ulaval.glo4002.booking.enums.ArtistOrderings;
import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.services.ArtistService;
import ca.ulaval.glo4002.booking.services.ProgramService;

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

    @Test
    @Ignore("WIP")
    void add_shouldReturnOk() {
        ProgramDto aProgramDto = mock(ProgramDto.class);

    }

    @Test
    @Ignore("WIP")
    void add_shouldReturnBadRequest_whenBadRequest() {
        ProgramDto aProgramDto = mock(ProgramDto.class);
        doThrow(new InvalidFormatException()).when(programService).add(aProgramDto);


    }
}