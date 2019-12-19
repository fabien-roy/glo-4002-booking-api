package ca.ulaval.glo4002.booking.shuttles.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4002.booking.shuttles.services.ShuttleManifestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

class ShuttleManifestResourceTest {

    private ShuttleManifestResource resource;
    private ShuttleManifestService service;

    @BeforeEach
    void setUpResource() {
        service = mock(ShuttleManifestService.class);

        resource = new ShuttleManifestResource(service);
    }

    @Test
    void getWithADate_shouldReturnOk() {
        String aDate = "aDate";
        when(service.getTripsForDate(aDate)).thenReturn(mock(ShuttleManifestResponse.class));

        Response response = resource.get(aDate);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
    
    @Test
    void getWithoutDate_shouldReturnOk() {
        when(service.getTrips()).thenReturn(mock(ShuttleManifestResponse.class));

        Response response = resource.get(null);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}