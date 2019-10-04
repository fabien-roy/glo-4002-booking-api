package ca.ulaval.glo4002.booking.services;

public class ShuttleServiceContext {
    /*

    private static final ShuttleCategoryBuilder shuttleCategoryBuilder = new ShuttleCategoryBuilder();
    private static final ShuttleCategory A_SHUTTLE_CATEGORY = shuttleCategoryBuilder.buildById(ShuttleConstants.Categories.ET_SPACESHIP_ID);
    private static final ShuttleCategory ANOTHER_SHUTTLE_CATEGORY = shuttleCategoryBuilder.buildById(ShuttleConstants.Categories.MILLENNIUM_FALCON_ID);

    private ShuttleParser parser = new ShuttleParser();
    private ShuttleEntity aShuttleEntity;
    private ShuttleEntity anotherShuttleEntity;
    private ShuttleEntity anotherQualityShuttleEntity;
    private ShuttleEntity aNonExistentShuttleEntity;

    public ShuttleRepository repository;
    public Shuttle aShuttle;
    public Shuttle anotherShuttle;
    public Shuttle anotherQualityShuttle;
    public Shuttle aNonExistentShuttle;
    public static final Long A_SHUTTLE_ID = 0L;
    public static final Long ANOTHER_SHUTTLE_ID = 1L;
    public static final Long A_NON_EXISTENT_SHUTTLE_ID = 3L;
    public static final Long ANOTHER_QUALITY_SHUTTLE_ID = 4L;

    public ShuttleServiceContext() {
        setUpObjects();
        setUpRepository();
    }

    private void setUpObjects() {
        aShuttle = new Shuttle(
                A_SHUTTLE_ID,
                A_SHUTTLE_CATEGORY.getPrice(),
                A_SHUTTLE_CATEGORY,
                new ArrayList<>()
                );

        anotherShuttle = new Shuttle(
                ANOTHER_SHUTTLE_ID,
                A_SHUTTLE_CATEGORY.getPrice(),
                A_SHUTTLE_CATEGORY,
                new ArrayList<>()
        );

        aNonExistentShuttle = new Shuttle(
                A_NON_EXISTENT_SHUTTLE_ID,
                A_SHUTTLE_CATEGORY.getPrice(),
                A_SHUTTLE_CATEGORY,
                new ArrayList<>()
        );

        anotherQualityShuttle = new Shuttle(
                ANOTHER_QUALITY_SHUTTLE_ID,
                A_SHUTTLE_CATEGORY.getPrice(),
                ANOTHER_SHUTTLE_CATEGORY,
                new ArrayList<>()
        );

        aShuttleEntity = parser.toEntity(aShuttle);
        anotherShuttleEntity = parser.toEntity(anotherShuttle);
        anotherQualityShuttleEntity = parser.toEntity(anotherQualityShuttle);
        aNonExistentShuttleEntity = parser.toEntity(aNonExistentShuttle);
    }

    private void setUpRepository() {
        repository = mock(ShuttleRepository.class);

        when(repository.findAll()).thenReturn(Arrays.asList(aShuttleEntity, anotherShuttleEntity, anotherQualityShuttleEntity));
        when(repository.findById(A_SHUTTLE_ID)).thenReturn(Optional.of(aShuttleEntity));
        when(repository.findById(ANOTHER_SHUTTLE_ID)).thenReturn(Optional.of(anotherShuttleEntity));
        when(repository.findById(A_NON_EXISTENT_SHUTTLE_ID)).thenReturn(Optional.empty());
    }

    */
}
