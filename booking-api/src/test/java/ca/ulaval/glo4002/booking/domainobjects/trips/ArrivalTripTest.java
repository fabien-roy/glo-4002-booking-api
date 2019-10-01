package ca.ulaval.glo4002.booking.domainobjects.trips;

import ca.ulaval.glo4002.booking.domainobjects.trips.types.ArrivalTripType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArrivalTripTest {

    @Test
    public void construction_shouldSetTripTypeToArrival() {
        ArrivalTrip subject = new ArrivalTrip(LocalDate.now(), new ArrayList<>());

        assertTrue(subject.getType() instanceof ArrivalTripType);
    }
}
