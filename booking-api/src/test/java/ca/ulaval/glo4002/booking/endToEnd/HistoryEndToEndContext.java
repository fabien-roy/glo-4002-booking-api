package ca.ulaval.glo4002.booking.endToEnd;

public class HistoryEndToEndContext {
    /*

    private static final LocalDate FIRST_DATE = DateConstants.START_DATE.minusDays(15);
    private static final LocalDate SECOND_DATE = DateConstants.START_DATE.minusDays(10);
    private static final LocalDate THIRD_DATE = DateConstants.START_DATE.minusDays(5);
    private static final LocalDate FOURTH_DATE = DateConstants.START_DATE;

    private EntityManager entityManager;
    public OxygenCategoryBuilder categoryBuilder = new OxygenCategoryBuilder();
    private ReportDto aReportDto = new ReportDto();
    public ReportController reportController;
    public OxygenProductionBuilder productionBuilder = new OxygenProductionBuilder();
    public OxygenTankEntity aFirstAndEOxygenTank;
    public OxygenTankEntity aSecondAndBOxygenTank;
    public OxygenTankEntity aThirdAndAOxygenTank;
    public HistoryItemDto firstHistoryItemDto = new HistoryItemDto();
    public HistoryItemDto secondHistoryItemDto = new HistoryItemDto();
    public HistoryItemDto thirdHistoryItemDto = new HistoryItemDto();
    public HistoryItemDto fourthHistoryItemDto = new HistoryItemDto();

    public HistoryEndToEndContext() {
        setUpObjects();
        setUpEntityManager();
    }

    private void setUpObjects() {
        OxygenCategory oxygenCategoryE = categoryBuilder.buildById(OxygenConstants.Categories.E_ID);
        OxygenCategory oxygenCategoryB = categoryBuilder.buildById(OxygenConstants.Categories.B_ID);
        OxygenCategory oxygenCategoryA = categoryBuilder.buildById(OxygenConstants.Categories.A_ID);

        aFirstAndEOxygenTank = new OxygenTankEntity(
                oxygenCategoryE.getId(),
                FIRST_DATE,
                SECOND_DATE
        );

        aSecondAndBOxygenTank = new OxygenTankEntity(
                oxygenCategoryB.getId(),
                FIRST_DATE,
                THIRD_DATE
        );

        aThirdAndAOxygenTank = new OxygenTankEntity(
                oxygenCategoryA.getId(),
                SECOND_DATE,
                FOURTH_DATE
        );

        firstHistoryItemDto.date = FIRST_DATE.toString();
        firstHistoryItemDto.qtyOxygenTankBought = oxygenCategoryE.getProduction().getProducedTanks() + oxygenCategoryB.getProduction().getProducedTanks();
        firstHistoryItemDto.qtyCandlesUsed = 0L;
        firstHistoryItemDto.qtyWaterUsed = oxygenCategoryB.getProduction().getProducedUnits();
        firstHistoryItemDto.qtyOxygenTankMade = 0L;

        secondHistoryItemDto.date = SECOND_DATE.toString();
        secondHistoryItemDto.qtyOxygenTankBought = oxygenCategoryA.getProduction().getProducedTanks();
        secondHistoryItemDto.qtyCandlesUsed = oxygenCategoryA.getProduction().getProducedUnits();
        secondHistoryItemDto.qtyWaterUsed = 0L;
        secondHistoryItemDto.qtyOxygenTankMade = oxygenCategoryE.getProduction().getProducedTanks();

        thirdHistoryItemDto.date = THIRD_DATE.toString();
        thirdHistoryItemDto.qtyOxygenTankBought = 0L;
        thirdHistoryItemDto.qtyCandlesUsed = 0L;
        thirdHistoryItemDto.qtyWaterUsed = 0L;
        thirdHistoryItemDto.qtyOxygenTankMade = oxygenCategoryB.getProduction().getProducedTanks();

        fourthHistoryItemDto.date = FOURTH_DATE.toString();
        fourthHistoryItemDto.qtyOxygenTankBought = 0L;
        fourthHistoryItemDto.qtyCandlesUsed = 0L;
        fourthHistoryItemDto.qtyWaterUsed = 0L;
        fourthHistoryItemDto.qtyOxygenTankMade = oxygenCategoryA.getProduction().getProducedTanks();

        aReportDto.history = new ArrayList<>(Arrays.asList(firstHistoryItemDto, secondHistoryItemDto, thirdHistoryItemDto, fourthHistoryItemDto));
    }

    private void setUpEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(RepositoryConstants.STAGING_PERSISTENCE_NAME);
        entityManager = entityManagerFactory.createEntityManager();
    }

    public HistoryEndToEndContext setUp() {
        InventoryItemRepository inventoryItemRepository = mock(InventoryItemRepository.class);
        OxygenTankInventoryRepository oxygenTankInventoryRepository = mock(OxygenTankInventoryRepository.class);
        InventoryItemService inventoryItemService = mock(InventoryItemService.class);
        OxygenTankInventoryService oxygenTankInventoryService = mock(OxygenTankInventoryService.class);
        OxygenTankInventory oxygenTankInventory = mock(OxygenTankInventory.class);
        when(oxygenTankInventory.getInUseTanks()).thenReturn(new HashMap<>());
        when(oxygenTankInventory.getNotInUseTanks()).thenReturn(new HashMap<>());
        when(oxygenTankInventoryService.get()).thenReturn(oxygenTankInventory);

        OxygenTankRepository oxygenTankRepository = new OxygenTankRepositoryImpl(entityManager);
        OxygenTankService oxygenTankService = new OxygenTankServiceImpl(oxygenTankRepository, oxygenTankInventoryService);
        HistoryService historyService = new HistoryServiceImpl(oxygenTankService);
        ReportService reportService = new ReportServiceImpl(oxygenTankInventoryService, historyService);

        reportController = new ReportController(reportService);

        return this;
    }

    public HistoryEndToEndContext withHistory() {
        addOxygenTank(aFirstAndEOxygenTank);
        addOxygenTank(aSecondAndBOxygenTank);
        addOxygenTank(aThirdAndAOxygenTank);

        return this;
    }

    private Long addOxygenTank(OxygenTankEntity oxygenTank) {

        entityManager.getTransaction().begin();
        entityManager.persist(oxygenTank);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        List<OxygenTankEntity> oxygenTanks = entityManager.createQuery(RepositoryConstants.OXYGEN_TANK_FIND_ALL_QUERY, OxygenTankEntity.class).getResultList();
        Long oxygenTankId = oxygenTanks.get(0).getId();
        entityManager.getTransaction().commit();

        return oxygenTankId;
    }
    */
}
