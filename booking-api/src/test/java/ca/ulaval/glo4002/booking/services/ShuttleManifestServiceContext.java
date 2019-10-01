package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.domainObjects.shuttles.Passenger;
import ca.ulaval.glo4002.booking.domainObjects.trips.ArrivalTrip;
import ca.ulaval.glo4002.booking.domainObjects.trips.DepartureTrip;
import ca.ulaval.glo4002.booking.domainObjects.trips.Trip;
import ca.ulaval.glo4002.booking.entities.TripEntity;
import ca.ulaval.glo4002.booking.parsers.TripParser;
import ca.ulaval.glo4002.booking.repositories.TripRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShuttleManifestServiceContext {

    private final static Long A_PASSENGER_ID = 1L;
    private final static Long ANOTHER_PASSENGER_ID = 2L;
    private final static Long YET_ANOTHER_PASSENGER_ID = 3L;
    private final static Long AND_ANOTHER_PASSENGER_ID = 4L;
    private final static List<Passenger> SOME_PASSENGERS = new ArrayList<>(Arrays.asList(
          new Passenger(A_PASSENGER_ID),
          new Passenger(ANOTHER_PASSENGER_ID)
    ));
    private final static List<Passenger> SOME_OTHER_PASSENGERS = new ArrayList<>(Arrays.asList(
            new Passenger(YET_ANOTHER_PASSENGER_ID),
            new Passenger(AND_ANOTHER_PASSENGER_ID)
    ));
    public final static LocalDate A_DATE = FestivalConstants.Dates.START_DATE;
    public final static LocalDate ANOTHER_DATE = FestivalConstants.Dates.START_DATE.plusDays(1);
    public TripRepository repository;
    public TripParser parser = new TripParser();
    public Trip aDepartureTrip;
    public Trip anotherDepartureTrip;
    public Trip anArrivalTrip;
    public Trip anotherArrivalTrip;
    public TripEntity aDepartureTripEntity;
    public TripEntity anotherDepartureTripEntity;
    public TripEntity anArrivalTripEntity;
    public TripEntity anotherArrivalTripEntity;

    public ShuttleManifestServiceContext() {
        setUpTrips();
        setUpRepository();
    }

    private void setUpTrips() {
        aDepartureTrip = new DepartureTrip(
                A_DATE,
                SOME_PASSENGERS
        );

        anotherDepartureTrip = new DepartureTrip(
                A_DATE,
                SOME_OTHER_PASSENGERS
        );

        anArrivalTrip = new ArrivalTrip(
                A_DATE,
                SOME_OTHER_PASSENGERS
        );

        anotherArrivalTrip = new ArrivalTrip(
                ANOTHER_DATE,
                SOME_PASSENGERS
        );

        aDepartureTripEntity = parser.toEntity(aDepartureTrip);
        anotherDepartureTripEntity = parser.toEntity(anotherDepartureTrip);
        anArrivalTripEntity = parser.toEntity(anArrivalTrip);
        anotherArrivalTripEntity = parser.toEntity(anotherArrivalTrip);
    }

    private void setUpRepository() {
        repository = mock(TripRepository.class);

        when(repository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(
                aDepartureTripEntity,
                anotherDepartureTripEntity,
                anArrivalTripEntity,
                anotherArrivalTripEntity
        )));
    }
}
