package ca.ulaval.glo4002.booking.services;

public class ShuttleServiceTest {
    /*

    private ShuttleServiceImpl subject;
    private ShuttleServiceContext context;

    @BeforeEach
    public void setUp() {
        context = new ShuttleServiceContext();
        subject = new ShuttleServiceImpl(context.repository);
    }

    @Test
    public void findById_shouldThrowShuttleNotFoundException_whenShuttleDoesNotExist() {
        ShuttleNotFoundException thrown = assertThrows(
                ShuttleNotFoundException.class,
                () -> subject.findById(ShuttleServiceContext.A_NON_EXISTENT_SHUTTLE_ID)
        );

        assertEquals(ExceptionConstants.Shuttle.NOT_FOUND_DESCRIPTION, thrown.getMessage());
    }

    @Test
    public void findById_shouldReturnCorrectShuttle() {
        Shuttle shuttle = subject.findById(ShuttleServiceContext.A_SHUTTLE_ID);

        assertEquals(context.aShuttle.getId(), shuttle.getId());
    }

    @Test
    public void findAll_shouldReturnCorrectShuttles() {
        List<Long> ids = new ArrayList<>();

        subject.findAll().forEach(shuttle -> ids.add(shuttle.getId()));

        assertEquals(3, ids.size());
        assertTrue(ids.contains(context.aShuttle.getId()));
        assertTrue(ids.contains(context.anotherShuttle.getId()));
    }
    */
}
