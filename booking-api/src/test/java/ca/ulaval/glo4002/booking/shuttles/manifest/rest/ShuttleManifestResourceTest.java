package ca.ulaval.glo4002.booking.shuttles.manifest.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4002.booking.shuttles.manifest.services.ShuttleManifestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

class ShuttleManifestResourceTest {

    private ShuttleManifestResource controller;
    private ShuttleManifestService service;

    @BeforeEach
    void setUpController() {
        service = mock(ShuttleManifestService.class);

        controller = new ShuttleManifestResource(service);
    }

    @Test
    void getWithADate_shouldReturnOk() {
        String aDate = "aDate";
        when(service.getTripsForDate(aDate)).thenReturn(mock(ShuttleManifestResponse.class));

        Response response = controller.get(aDate);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
    
    @Test
    void getWithoutDate_shouldReturnOk() {
        when(service.getTrips()).thenReturn(mock(ShuttleManifestResponse.class));

        Response response = controller.get(null);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}