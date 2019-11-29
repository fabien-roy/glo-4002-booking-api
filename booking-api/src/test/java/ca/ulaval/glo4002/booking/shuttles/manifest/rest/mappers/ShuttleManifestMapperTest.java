package ca.ulaval.glo4002.booking.shuttles.manifest.rest.mappers;

import ca.ulaval.glo4002.booking.shuttles.manifest.rest.mappers.ShuttleManifestMapper;
import ca.ulaval.glo4002.booking.shuttles.trips.domain.Trip;
import ca.ulaval.glo4002.booking.shuttles.trips.rest.mappers.TripMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
    void toDto_shouldCallTripMapperTwoTimes() {
        List<Trip> arrivals = new ArrayList<>();
        List<Trip> departures = new ArrayList<>();

        shuttleManifestMapper.toDto(arrivals, departures);

        verify(tripMapper, times(2)).toDto(any());
    }
}