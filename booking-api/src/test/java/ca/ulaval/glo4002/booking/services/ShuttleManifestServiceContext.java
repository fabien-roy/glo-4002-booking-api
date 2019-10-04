package ca.ulaval.glo4002.booking.services;

public class ShuttleManifestServiceContext {

    /*
	private ShuttleCategoryBuilder shuttleCategoryBuilder = new ShuttleCategoryBuilder();
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
    public final static LocalDate A_DATE = DateConstants.START_DATE;
    public final static LocalDate ANOTHER_DATE = DateConstants.START_DATE.plusDays(1);
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
    public Shuttle shuttle = new Shuttle(ShuttleConstants.Categories.ET_SPACESHIP_ID,
    		ShuttleConstants.Categories.ET_SPACESHIP_PRICE,
    		shuttleCategoryBuilder.buildByName(ShuttleConstants.Categories.ET_SPACESHIP_NAME),
    		new ArrayList<>());

    public ShuttleManifestServiceContext() {
        setUpTrips();
        setUpRepository();
    }

    private void setUpTrips() {
        aDepartureTrip = new DepartureTrip(
                A_DATE,
                SOME_PASSENGERS,
                shuttle
        );

        anotherDepartureTrip = new DepartureTrip(
                A_DATE,
                SOME_OTHER_PASSENGERS,
                shuttle
        );

        anArrivalTrip = new ArrivalTrip(
                A_DATE,
                SOME_OTHER_PASSENGERS,
                shuttle
        );

        anotherArrivalTrip = new ArrivalTrip(
                ANOTHER_DATE,
                SOME_PASSENGERS,
                shuttle
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
    */
}
