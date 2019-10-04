package ca.ulaval.glo4002.booking.parsers;

class ShuttleManifestParserTest {

/*
	private ShuttleCategoryBuilder shuttleCategoryBuilder = new ShuttleCategoryBuilder();
    private static final LocalDate A_DATE = DateConstants.START_DATE;
    private static final LocalDate ANOTHER_DATE = DateConstants.END_DATE;
    
    private final static Passenger A_PASSENGER = new Passenger(1L);
    private final static Passenger A_SECOND_PASSENGER = new Passenger(2L);
    private ShuttleManifestParser subject;
    private ShuttleManifest shuttleManifest;
    private ShuttleManifestDto shuttleManifestDto;
    
    private Shuttle spaceX = new Shuttle(ShuttleConstants.Categories.SPACE_X_ID,
    		ShuttleConstants.Categories.SPACE_X_PRICE,
    		shuttleCategoryBuilder.buildByName(ShuttleConstants.Categories.SPACE_X_NAME),
    		new ArrayList<>());
    
    private DepartureTrip aDepartureTrip = new DepartureTrip(A_DATE,
    		new ArrayList<>(Arrays.asList(A_PASSENGER, A_SECOND_PASSENGER))
    		, spaceX);
    
    private ArrivalTrip anArrivalTrip = new ArrivalTrip(ANOTHER_DATE,
    		new ArrayList<>(Arrays.asList(A_PASSENGER, A_SECOND_PASSENGER))
    		, spaceX);
    
    @BeforeEach
    public void setUp() {
    	subject = new ShuttleManifestParser();
    	shuttleManifest = new ShuttleManifest(new ArrayList<>(Arrays.asList(aDepartureTrip)),
    			new ArrayList<>(Arrays.asList(anArrivalTrip)));
    }
    
    @Test
    public void manifestToDto_shouldReturnValidDto() {
    	shuttleManifestDto = subject.toDto(shuttleManifest);
    	
    	assertEquals(shuttleManifest.getArrivals().size(), shuttleManifestDto.arrivals.size());
    	assertEquals(shuttleManifest.getDepartures().size(), shuttleManifestDto.departures.size());
    	assertTrue(shuttleManifestDto.arrivals.get(0) instanceof ShuttleDto);
    	assertTrue(shuttleManifestDto.departures.get(0) instanceof ShuttleDto);
    }
    */
}
