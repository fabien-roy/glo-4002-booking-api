package ca.ulaval.glo4002.booking.domainObjects.trips;

import ca.ulaval.glo4002.booking.domainObjects.trips.types.DepartureTripType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepartureTripTest {

    @Test
    public void construction_shouldSetTripTypeToDeparture() {
        DepartureTrip subject = new DepartureTrip(LocalDate.now(), new ArrayList<>());

        assertTrue(subject.getType() instanceof DepartureTripType);
    }
}
