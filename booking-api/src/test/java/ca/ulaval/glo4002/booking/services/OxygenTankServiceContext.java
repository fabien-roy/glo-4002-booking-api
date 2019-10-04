package ca.ulaval.glo4002.booking.services;

class OxygenTankServiceContext {

	/*
	public static final LocalDate A_VALID_DATE = DateConstants.START_DATE.minusDays(30);
	public static final LocalDate A_VALID_DATE_15DAYS_BEFORE_START = DateConstants.START_DATE.minusDays(15);
	public static final LocalDate A_VALID_DATE_5DAYS_BEFORE_START = DateConstants.START_DATE.minusDays(5);
	public static final LocalDate AN_INVALID_DATE = DateConstants.START_DATE.plusDays(1);
	public static final LocalDate A_DATE_AFTER_THE_OTHER_ONE = A_VALID_DATE.plusDays(20);

	private OxygenTankRepository repository;
	private OxygenTankEntity oxygenTankAEntity;
	private OxygenTankEntity oxygenTankBEntity;
	private OxygenTankEntity oxygenTankEEntity;
	private OxygenTankEntity oxygenTankNonExistentEntity;
	private OxygenTankParser parser = new OxygenTankParser();
	private OxygenCategoryBuilder categoryBuilder = new OxygenCategoryBuilder();

	public OxygenTankService subject;
	public OxygenTank oxygenTankA;
	public OxygenTank oxygenTankB;
	public OxygenTank oxygenTankE;
	public OxygenTank nonExistentOxygenTank;
	public Long oxygenTankAId;
	public Long oxygenTankBId;
	public Long oxygenTankEId;
	public Long nonExistentOxygenTankId = 0L;

	OxygenTankServiceContext() {
		setUpOxygenTanks();
		setUpRepository();
		setUpSubject();
	}

	private void setUpOxygenTanks() {
		oxygenTankA = new OxygenTank(categoryBuilder.buildById(OxygenConstants.Categories.A_ID), A_VALID_DATE);

		oxygenTankB = new OxygenTank(categoryBuilder.buildById(OxygenConstants.Categories.B_ID), A_VALID_DATE);

		oxygenTankE = new OxygenTank(categoryBuilder.buildById(OxygenConstants.Categories.E_ID), A_VALID_DATE);

		nonExistentOxygenTank = new OxygenTank(categoryBuilder.buildById(OxygenConstants.Categories.A_ID), A_VALID_DATE);

		oxygenTankAEntity = parser.toEntity(oxygenTankA);
		oxygenTankAId = oxygenTankAEntity.getId();
		oxygenTankBEntity = parser.toEntity(oxygenTankB);
		oxygenTankBId = oxygenTankBEntity.getId();
		oxygenTankEEntity = parser.toEntity(oxygenTankE);
		oxygenTankEId = oxygenTankEEntity.getId();
		oxygenTankNonExistentEntity = parser.toEntity(nonExistentOxygenTank);
	}

	private void setUpRepository() {
		this.repository = mock(OxygenTankRepository.class);

		when(repository.findAll()).thenReturn(Arrays.asList(oxygenTankAEntity, oxygenTankBEntity, oxygenTankEEntity));
		when(repository.findById(oxygenTankAId)).thenReturn(Optional.of(oxygenTankAEntity));
		when(repository.findById(oxygenTankBId)).thenReturn(Optional.of(oxygenTankBEntity));
		when(repository.findById(oxygenTankEId)).thenReturn(Optional.of(oxygenTankEEntity));
	}

	private void setUpSubject() {
		OxygenTankInventoryService oxygenTankInventoryService = mock(OxygenTankInventoryService.class);

		subject = new OxygenTankServiceImpl(repository, oxygenTankInventoryService);
	}

	void setUpRepositoryForSave() {
		when(repository.findById(nonExistentOxygenTankId)).thenReturn(Optional.of(oxygenTankNonExistentEntity));
	}
	*/
}
