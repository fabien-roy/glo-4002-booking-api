package ca.ulaval.glo4002.booking.festival;

import ca.ulaval.glo4002.booking.program.events.EventDatesDto;
import ca.ulaval.glo4002.booking.errors.ExceptionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ConfigurationControllerTest {

    private ConfigurationController controller;

    @BeforeEach
    void setUpController() {
        ExceptionMapper exceptionMapper = new ExceptionMapper();
        FestivalService festivalService = mock(FestivalService.class);

        controller = new ConfigurationController(exceptionMapper, festivalService);
    }

    @Test
    void addProgram_shouldReturnOk() {
        ResponseEntity<?> response = controller.setConfiguration(mock(EventDatesDto.class));

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}