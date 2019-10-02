package ca.ulaval.glo4002.booking.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.builders.shuttles.ShuttleCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.DateConstants;
import ca.ulaval.glo4002.booking.constants.ShuttleConstants;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.categories.ShuttleCategory;
import ca.ulaval.glo4002.booking.domainobjects.trips.ArrivalTrip;
import ca.ulaval.glo4002.booking.domainobjects.trips.DepartureTrip;
import ca.ulaval.glo4002.booking.domainobjects.trips.Trip;
import ca.ulaval.glo4002.booking.entities.TripEntity;

class TripParserTest {

	private ShuttleCategoryBuilder shuttleCategoryBuilder = new ShuttleCategoryBuilder();
    private final static Long A_TRIP_ID = 1L;
    private final static Long ANOTHER_TRIP_ID = 2L;
    private final static LocalDate A_TRIP_DATE = DateConstants.START_DATE;
    private final static Long A_SHUTTLE_ID = ShuttleConstants.Categories.ET_SPACESHIP_ID;
    private final static Double A_SHUTTLE_PRICE = ShuttleConstants.Categories.ET_SPACESHIP_PRICE;
    private List<Trip> trips = new ArrayList<>();
    private ShuttleCategory shuttleCategory = shuttleCategoryBuilder.
    		buildByName(ShuttleConstants.Categories.ET_SPACESHIP_NAME);
    private TripParser subject = new TripParser();
    private Trip aDepartureTrip;
    private Trip anArrivalTrip;
    private Shuttle shuttle = new Shuttle(A_SHUTTLE_ID, A_SHUTTLE_PRICE, shuttleCategory, trips);

    @BeforeEach
    void setUp() {
        subject = new TripParser();

        aDepartureTrip = new DepartureTrip(
                A_TRIP_ID,
                A_TRIP_DATE,
                new ArrayList<>(),
                shuttle
        );

        anArrivalTrip = new ArrivalTrip(
                ANOTHER_TRIP_ID,
                A_TRIP_DATE,
                new ArrayList<>(),
                shuttle
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
