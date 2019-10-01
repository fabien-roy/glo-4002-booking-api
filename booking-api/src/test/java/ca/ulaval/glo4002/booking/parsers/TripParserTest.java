package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.domainObjects.trips.ArrivalTrip;
import ca.ulaval.glo4002.booking.domainObjects.trips.DepartureTrip;
import ca.ulaval.glo4002.booking.domainObjects.trips.Trip;
import ca.ulaval.glo4002.booking.entities.TripEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TripParserTest {

    private final static Long A_TRIP_ID = 1L;
    private final static Long ANOTHER_TRIP_ID = 2L;
    private final static LocalDate A_TRIP_DATE = FestivalConstants.Dates.START_DATE;
    private TripParser subject = new TripParser();
    private Trip aDepartureTrip;
    private Trip anArrivalTrip;

    @BeforeEach
    void setUp() {
        subject = new TripParser();

        aDepartureTrip = new DepartureTrip(
                A_TRIP_ID,
                A_TRIP_DATE,
                new ArrayList<>()
        );

        anArrivalTrip = new ArrivalTrip(
                ANOTHER_TRIP_ID,
                A_TRIP_DATE,
                new ArrayList<>()
        );
    }

    @Test
    void parseEntity_shouldReturnValidTrip() {
        TripEntity entity = subject.toEntity(aDepartureTrip);

        Trip trip = subject.parseEntity(entity);

        assertEquals(entity.getId(), trip.getId());
        assertEquals(entity.getDate(), trip.getDate());
        assertEquals(entity.getTypeId(), trip.getType().getId());
    }

    @Test
    void parseToEntity_shouldReturnValidTripEntity_whenTripIsDeparture() {
        TripEntity entity = subject.toEntity(aDepartureTrip);

        assertEquals(aDepartureTrip.getId(), entity.getId());
        assertEquals(aDepartureTrip.getDate(), entity.getDate());
        assertEquals(aDepartureTrip.getType().getId(), entity.getTypeId());
    }

    @Test
    void parseToEntity_shouldReturnValidTripEntity_whenTripIsArrival() {
        TripEntity entity = subject.toEntity(anArrivalTrip);

        assertEquals(anArrivalTrip.getId(), entity.getId());
        assertEquals(anArrivalTrip.getDate(), entity.getDate());
        assertEquals(anArrivalTrip.getType().getId(), entity.getTypeId());
    }
}
