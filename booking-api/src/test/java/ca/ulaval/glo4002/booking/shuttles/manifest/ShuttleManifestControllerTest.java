package ca.ulaval.glo4002.booking.shuttles.manifest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

class ShuttleManifestControllerTest {

    private ShuttleManifestController controller;
    private ShuttleManifestService service;

    @BeforeEach
    void setUpController() {
        service = mock(ShuttleManifestService.class);

        controller = new ShuttleManifestController(service);
    }

    @Test
    void getWithADate_shouldReturnOk() {
        String aDate = "aDate";
        when(service.getTripsForDate(aDate)).thenReturn(mock(ShuttleManifestDto.class));

        Response response = controller.get(aDate);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
    
    @Test
    void getWithoutDate_shouldReturnOk() {
        when(service.getTrips()).thenReturn(mock(ShuttleManifestDto.class));

        Response response = controller.get(null);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}