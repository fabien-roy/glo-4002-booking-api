package ca.ulaval.glo4002.booking.services;

public class InventoryServiceContext {
    /*

	public OxygenTankInventoryParser parser = new OxygenTankInventoryParser();
	public OxygenTankInventoryRepository repository;
	public OxygenTankInventory anOxygenTankInventory;
	public OxygenTank anOxygenTank;
	private OxygenTankInventoryEntity anOxygenTankInventoryEntity;

	public static final Long A_OXYGEN_TANK_CATEGORY_ID = OxygenConstants.Categories.E_ID;
	public static final Long AN_INVALID_CATEGORY = -1L;
	private static final LocalDate A_VALID_DATE = LocalDate.of(2050, 6, 20);
	private static final LocalDate A_DATE_AFTER_THE_OTHER_ONE = A_VALID_DATE.plusDays(20);

	public InventoryServiceContext() {
		setUpObjects();
		setUpRepository();
	}

	private void setUpObjects() {
		OxygenCategoryBuilder categoryBuilder = new OxygenCategoryBuilder();

		anOxygenTankInventory = new OxygenTankInventory(new HashMap<>(), new HashMap<>());

		anOxygenTank = new OxygenTank(
				categoryBuilder.buildById(A_OXYGEN_TANK_CATEGORY_ID),
				A_VALID_DATE
		);

		anOxygenTankInventoryEntity = parser.toEntity(anOxygenTankInventory);
	}

	private void setUpRepository() {
		repository = mock(OxygenTankInventoryRepository.class);

		when(repository.findAll()).thenReturn(Collections.singletonList(anOxygenTankInventoryEntity));
		when(repository.save(any(OxygenTankInventoryEntity.class))).thenReturn(parser.toEntity(anOxygenTankInventory));
	}

	// TODO : Setup for save
	public void setUpForSave() {

	}
	*/
}
