package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.domain.events.EventDate;
import ca.ulaval.glo4002.booking.dto.events.ArtistListDto;
import ca.ulaval.glo4002.booking.dto.events.ProgramDto;
import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.services.ArtistService;
import ca.ulaval.glo4002.booking.services.ProgramService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProgramControllerTest {

    private ProgramController controller;
    private ProgramService programService;
    private ArtistService artistService;

    @BeforeEach
    void setUpController() {
        programService = mock(ProgramService.class);
        artistService = mock(ArtistService.class);

        controller = new ProgramController(programService, artistService);
    }

    @Test
    void getArtists_shouldReturnOk() {
        String aDate = EventDate.START_DATE.toString();
        when(artistService.getAll(aDate)).thenReturn(mock(ArtistListDto.class));

        ResponseEntity<?> response = controller.getArtists(aDate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getArtists_shouldReturnBadRequest_whenBadRequest() {
        String aDate = "aDate";
        when(artistService.getAll(aDate)).thenThrow(new InvalidFormatException());

        ResponseEntity<?> response = controller.getArtists(aDate);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void add_shouldReturnOk() {
        ProgramDto aProgramDto = mock(ProgramDto.class);

        ResponseEntity<?> response = controller.add(aProgramDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void add_shouldReturnBadRequest_whenBadRequest() {
        ProgramDto aProgramDto = mock(ProgramDto.class);
        doThrow(new InvalidFormatException()).when(programService).add(aProgramDto);

        ResponseEntity<?> response = controller.add(aProgramDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}