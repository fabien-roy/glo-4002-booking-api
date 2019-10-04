package ca.ulaval.glo4002.booking.services;

public class ShuttleInventoryServiceTest {
    /*

    private ShuttleInventoryServiceImpl subject;
    private ShuttleInventoryServiceContext context;

    @BeforeEach
    public void setUp() {
        context = new ShuttleInventoryServiceContext();
        subject = new ShuttleInventoryServiceImpl(context.repository);
    }

    @Test
    public void order_shouldReturnShuttle_whenShuttleIsNotFull(){
        List<Shuttle> shuttles = new ArrayList<>();

        subject.order(context.aShuttle.getShuttleCategory().getQuality(), ShuttleInventoryServiceContext.AN_ARRIVAL_DATE, ShuttleInventoryServiceContext.A_DEPARTURE_DATE).forEach(shuttles::add);

        assertEquals(1, shuttles.size());
        assertTrue(shuttles.stream().allMatch(shuttle -> shuttle.getId().equals(ShuttleServiceContext.A_SHUTTLE_ID)));
    }

    @Test
    public void order_shouldReturnNewShuttle_whenLastShuttleIsFull(){
        List<Shuttle> shuttles = new ArrayList<>();
        context.setUpWithFullTrip();

        subject.order(context.aShuttle.getShuttleCategory().getQuality(), ShuttleInventoryServiceContext.AN_ARRIVAL_DATE, ShuttleInventoryServiceContext.A_DEPARTURE_DATE).forEach(shuttles::add);

        assertEquals(1, shuttles.size());
        assertTrue(shuttles.stream().allMatch(shuttle -> shuttle.getId().equals(ShuttleServiceContext.ANOTHER_SHUTTLE_ID)));
    }

    @Test
    public void order_shouldReturnBothShuttles_whenLastShuttleIsAlmostFull(){
        List<Shuttle> shuttles = new ArrayList<>();
        context.setUpWithAlmostFullTrip();

        subject.order(context.aShuttle.getShuttleCategory().getQuality(), ShuttleInventoryServiceContext.AN_ARRIVAL_DATE, ShuttleInventoryServiceContext.A_DEPARTURE_DATE).forEach(shuttles::add);
        subject.order(context.aShuttle.getShuttleCategory().getQuality(), ShuttleInventoryServiceContext.AN_ARRIVAL_DATE, ShuttleInventoryServiceContext.A_DEPARTURE_DATE).forEach(shuttles::add);

        assertEquals(2, shuttles.size());
        assertTrue(shuttles.stream().anyMatch(shuttle -> shuttle.getId().equals(ShuttleServiceContext.A_SHUTTLE_ID)));
        assertTrue(shuttles.stream().anyMatch(shuttle -> shuttle.getId().equals(ShuttleServiceContext.ANOTHER_SHUTTLE_ID)));
    }

    @Test
    public void order_shouldReturnCorrectQualityForShuttle(){
        List<Shuttle> shuttles = new ArrayList<>();

        subject.order(context.anotherQualityShuttle.getShuttleCategory().getQuality(), ShuttleInventoryServiceContext.AN_ARRIVAL_DATE, ShuttleInventoryServiceContext.A_DEPARTURE_DATE).forEach(shuttles::add);

        assertEquals(1, shuttles.size());
        assertTrue(shuttles.stream().allMatch(shuttle -> shuttle.getId().equals(ShuttleServiceContext.ANOTHER_QUALITY_SHUTTLE_ID)));
    }
    */
}
