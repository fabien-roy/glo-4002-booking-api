package ca.ulaval.glo4002.booking.shuttles.rest.mappers;

import ca.ulaval.glo4002.booking.shuttles.rest.ShuttleManifestResponse;
import ca.ulaval.glo4002.booking.shuttles.trips.domain.Trip;
import ca.ulaval.glo4002.booking.shuttles.trips.rest.TripResponse;
import ca.ulaval.glo4002.booking.shuttles.trips.rest.mappers.TripMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ShuttleManifestMapperTest {

    private ShuttleManifestMapper shuttleManifestMapper;
    private TripMapper tripMapper;

    @BeforeEach
    void setUpMapper() {
        tripMapper = mock(TripMapper.class);

        shuttleManifestMapper = new ShuttleManifestMapper(tripMapper);
    }

    @Test
    void toResponse_shouldMapArrivals() {
        List<Trip> arrivals = new ArrayList<>();
        TripResponse expectedArrivalResponse = mock(TripResponse.class);
        List<TripResponse> expectedArrivalResponses = Collections.singletonList(expectedArrivalResponse);
        when(tripMapper.toResponse(arrivals)).thenReturn(expectedArrivalResponses);

        ShuttleManifestResponse response = shuttleManifestMapper.toResponse(arrivals, new ArrayList<>());

        assertEquals(expectedArrivalResponse, response.getArrivals().get(0));
    }

    @Test
    void toResponse_shouldMapDepartures() {
        List<Trip> departures = new ArrayList<>();
        TripResponse expectedDepartureResponse = mock(TripResponse.class);
        List<TripResponse> expectedDepartureResponses = Collections.singletonList(expectedDepartureResponse);
        when(tripMapper.toResponse(departures)).thenReturn(expectedDepartureResponses);

        ShuttleManifestResponse response = shuttleManifestMapper.toResponse(new ArrayList<>(), departures);

        assertEquals(expectedDepartureResponse, response.getDepartures().get(0));
    }
}