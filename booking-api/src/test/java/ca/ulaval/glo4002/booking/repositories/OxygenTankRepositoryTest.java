package ca.ulaval.glo4002.booking.repositories;

class OxygenTankRepositoryTest {
	/*

	private OxygenTankRepositoryContext context;
	private OxygenTankRepositoryImpl subject;

	@BeforeEach
	public void setUp() {
		context = new OxygenTankRepositoryContext();
		subject = new OxygenTankRepositoryImpl(context.entityManager);
	}

	@Test
	public void findById_shouldThrowOxygenTankNotFoundException_whenOxygenTankDoesNotExist() {
		OxygenTankNotFoundException thrown = assertThrows(
				OxygenTankNotFoundException.class,
				() -> subject.findById(OxygenTankRepositoryContext.NON_EXISTENT_OXYGEN_ID)
		);

		assertEquals(ExceptionConstants.Oxygen.TANK_NOT_FOUND_ERROR, thrown.getMessage());
	}

	@Test
	public void findById_shouldReturnCorrectOxygenTank() {
		OxygenTankEntity oxygenTank = subject.findById(OxygenTankRepositoryContext.A_OXYGEN_ID).get();

		assertEquals(context.oxygenTankA, oxygenTank);
	}

	@Test
	public void findAll_shouldReturnCorrectOxygenTanks() {
		List<OxygenTankEntity> oxygenTank = new ArrayList<>();

		subject.findAll().forEach(oxygenTank::add);

		assertEquals(3, oxygenTank.size());
		assertTrue(oxygenTank.contains(context.oxygenTankA));
		assertTrue(oxygenTank.contains(context.oxygenTankB));
		assertTrue(oxygenTank.contains(context.oxygenTankE));
	}

	@Test
	public void saveAll_shouldSaveOxygenTanks() {
		context.setUpEntityManagerForSave();
		subject.saveAll(new ArrayList<>(Collections.singletonList(context.nonExistentOxygenTank)));

		OxygenTankEntity tank = subject.findById(OxygenTankRepositoryContext.NON_EXISTENT_OXYGEN_ID).get();

		assertEquals(context.nonExistentOxygenTank, tank);
	}
	*/
}
