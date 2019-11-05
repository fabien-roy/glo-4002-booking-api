package ca.ulaval.glo4002.booking.mappers;

import ca.ulaval.glo4002.booking.domain.shuttles.ShuttleManifest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

class ShuttleManifestMapperTest {

    private ShuttleManifestMapper shuttleManifestMapper;
    private ShuttleManifest shuttleManifest;
    private TripMapper tripMapper;

    @BeforeEach
    void setUpMapper() {
        tripMapper = mock(TripMapper.class);

        shuttleManifestMapper = new ShuttleManifestMapper(tripMapper);
    }

    @BeforeEach
    void setUpOrder() {
        shuttleManifest = mock(ShuttleManifest.class);

        when(shuttleManifest.getArrivals()).thenReturn(new ArrayList<>());
        when(shuttleManifest.getDepartures()).thenReturn(new ArrayList<>());
    }

    @Test
    void toDto_shouldCallTripMapperTwoTimes() {
        shuttleManifestMapper.toDto(shuttleManifest);

        verify(tripMapper, times(2)).toDto(any());
    }
}