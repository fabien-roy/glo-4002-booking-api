package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.constants.DateConstants;
import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domainobjects.qualities.NebulaQuality;
import ca.ulaval.glo4002.booking.domainobjects.qualities.SupergiantQuality;
import ca.ulaval.glo4002.booking.domainobjects.qualities.SupernovaQuality;
import ca.ulaval.glo4002.booking.exceptions.dates.InvalidDateException;
import ca.ulaval.glo4002.booking.exceptions.oxygen.OxygenTankNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OxygenTankServiceTest {

	private OxygenTankServiceContext context;

	@BeforeEach
	public void setUp() {
		context = new OxygenTankServiceContext();
	}

	@Test
	public void findAll_shouldReturnCorrectOxygenTanks() {
		List<Long> tankIds = new ArrayList<>();
		context.subject.findAll().forEach(oxygenTank -> tankIds.add(oxygenTank.getId()));

		assertEquals(3, tankIds.size());
		assertTrue(tankIds.contains(context.oxygenTankA.getId()));
		assertTrue(tankIds.contains(context.oxygenTankB.getId()));
		assertTrue(tankIds.contains(context.oxygenTankE.getId()));
	}

	@Test
	public void findById_shouldThrowOxygenTankNotFoundException_whenOxygenTankDoesNotExist() {
		OxygenTankNotFoundException thrown = assertThrows(
				OxygenTankNotFoundException.class,
				() -> context.subject.findById(context.nonExistentOxygenTankId)
		);

		assertEquals(ExceptionConstants.Oxygen.TANK_NOT_FOUND_ERROR, thrown.getMessage());
	}

	@Test
	public void findById_shouldReturnCorrectOxygenTank() {
		OxygenTank oxygenTank = context.subject.findById(context.oxygenTankAId);

		assertEquals(context.oxygenTankA.getId(), oxygenTank.getId());
	}

	@Test
	public void saveAll_shouldSaveOxygenTank() {
		context.setUpRepositoryForSave();

		context.subject.saveAll(new ArrayList<>(Collections.singletonList(context.nonExistentOxygenTank)));
		OxygenTank oxygenTank = context.subject.findById(context.nonExistentOxygenTankId);

		assertEquals(context.nonExistentOxygenTank.getId(), oxygenTank.getId());
	}

	@Test
	void createOxygenTank_afterTheStartingDateOfFestival_shouldThrowInvalidEventDateException() {
		InvalidDateException thrown = assertThrows(
				InvalidDateException.class,
				() -> context.subject.order(new NebulaQuality(), OxygenTankServiceContext.AN_INVALID_DATE)
		);

		assertEquals(ExceptionConstants.INVALID_DATE_ERROR, thrown.getMessage());
	}

	@Test
	void orderCategory_shouldBeNebula_whenCreatingOxygenTankWithCategoryA() {
		List<OxygenTank> oxygenTanks = new ArrayList<>();

		context.subject.order(new NebulaQuality(), OxygenTankServiceContext.A_VALID_DATE).forEach(oxygenTanks::add);

		assertTrue(oxygenTanks.stream().allMatch(oxygenTank -> oxygenTank.getOxygenTankCategory().getId().equals(OxygenConstants.Categories.A_ID)));
	}

	@Test
	void orderCategory_shouldBeSupergiant_whenCreatingOxygenTankWithCategoryB() {
		List<OxygenTank> oxygenTanks = new ArrayList<>();

		context.subject.order(new SupergiantQuality(), OxygenTankServiceContext.A_VALID_DATE).forEach(oxygenTanks::add);

		assertTrue(oxygenTanks.stream().allMatch(oxygenTank -> oxygenTank.getOxygenTankCategory().getId().equals(OxygenConstants.Categories.B_ID)));
	}

	@Test
	void orderCategory_shouldBeSupernova_whenCreatingOxygenTankWithCategoryE() {
		List<OxygenTank> oxygenTanks = new ArrayList<>();

		context.subject.order(new SupernovaQuality(), OxygenTankServiceContext.A_VALID_DATE).forEach(oxygenTanks::add);

		assertTrue(oxygenTanks.stream().allMatch(oxygenTank -> oxygenTank.getOxygenTankCategory().getId().equals(OxygenConstants.Categories.E_ID)));
	}

	@Test
	void oxygenCategory_shouldBeCategoryB_whenOrderIsNebula_butInLessThan20DaysAndMoreThan10OfFestivalStart() {
		List<OxygenTank> oxygenTanks = new ArrayList<>();

		context.subject.order(new NebulaQuality(), OxygenTankServiceContext.A_VALID_DATE_15DAYS_BEFORE_START).forEach(oxygenTanks::add);

		assertTrue(oxygenTanks.stream().allMatch(oxygenTank -> oxygenTank.getOxygenTankCategory().getId().equals(OxygenConstants.Categories.B_ID)));
		assertTrue(oxygenTanks.stream().allMatch(oxygenTank -> oxygenTank.getReadyDate().equals(DateConstants.START_DATE)));
	}

	@Test
	void oxygenCategory_shouldBeCategoryE_whenOrderIsNebula_butInLessThan10DayOfFestivalStart() {
		List<OxygenTank> oxygenTanks = new ArrayList<>();

		context.subject.order(new NebulaQuality(), OxygenTankServiceContext.A_VALID_DATE_5DAYS_BEFORE_START).forEach(oxygenTanks::add);

		assertTrue(oxygenTanks.stream().allMatch(oxygenTank -> oxygenTank.getOxygenTankCategory().getId().equals(OxygenConstants.Categories.E_ID)));
		assertTrue(oxygenTanks.stream().allMatch(oxygenTank -> oxygenTank.getReadyDate().equals(DateConstants.START_DATE)));
	}

	@Test
	void oxygenCategory_shouldBeCategoryE_whenOrderIsOnTheStartingDateOfFestival() {
		List<OxygenTank> oxygenTanks = new ArrayList<>();

		context.subject.order(new NebulaQuality(), DateConstants.START_DATE).forEach(oxygenTanks::add);

		assertTrue(oxygenTanks.stream().allMatch(oxygenTank -> oxygenTank.getOxygenTankCategory().getId().equals(OxygenConstants.Categories.E_ID)));
		assertTrue(oxygenTanks.stream().allMatch(oxygenTank -> oxygenTank.getReadyDate().equals(DateConstants.START_DATE)));
	}
}
