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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.*;

class OxygenTankServiceTest {

	OxygenTankServiceContext context;
	private NebulaQuality nebulaQuality = new NebulaQuality();
	private SupergiantQuality supergiantQuality = new SupergiantQuality();
	private SupernovaQuality supernovaQuality = new SupernovaQuality();
	private final LocalDate VALID_DATE = DateConstants.START_DATE.minus(30, DAYS);
	private final LocalDate VALID_DATE_15DAYS_BEFORE_START = DateConstants.START_DATE.minus(15, DAYS);
	private final LocalDate VALID_DATE_5DAYS_BEFORE_START = DateConstants.START_DATE.minus(5, DAYS);
	private final LocalDate INVALID_DATE = DateConstants.START_DATE.plus(1, DAYS);

	@BeforeEach
	public void setUp() {
		context = new OxygenTankServiceContext();
	}

	@Test
	public void findAll_shouldReturnCorrectOxygens() {
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
	void orderCategory_shouldBeNebula_whenCreatingOxygenTankWithCategoryA() {
		OxygenTank tank = context.subject.order(nebulaQuality, VALID_DATE).iterator().next();

		assertEquals(OxygenConstants.Categories.A_ID, tank.getOxygenTankCategory().getId());
	}

	@Test
	void orderCategory_shouldBeSupergiant_whenCreatingOxygenTankWithCategoryB() {
		OxygenTank tank = context.subject.order(supergiantQuality, VALID_DATE).iterator().next();

		assertEquals(OxygenConstants.Categories.B_ID, tank.getOxygenTankCategory().getId());
	}

	@Test
	void orderCategory_shouldBeSupernova_whenCreatingOxygenTankWithCategoryE() {
		OxygenTank tank = context.subject.order(supernovaQuality, VALID_DATE).iterator().next();

		assertEquals(OxygenConstants.Categories.E_ID, tank.getOxygenTankCategory().getId());
	}

	@Test
	void oxygenCategory_shouldBeCategoryB_whenOrderIsNebula_butInLessThan20DaysAndMoreThan10OfFestivalStart() {
		OxygenTank tank = context.subject.order(nebulaQuality, VALID_DATE_15DAYS_BEFORE_START).iterator().next();

		assertEquals(OxygenConstants.Categories.B_ID, tank.getOxygenTankCategory().getId());
		assertTrue(tank.getReadyDate().isBefore(DateConstants.START_DATE));
	}

	@Test
	void oxygenCategory_shouldBeCategoryE_whenOrderIsNebula_butInLessThan10DayOfFestivalStart() {
		OxygenTank tank = context.subject.order(nebulaQuality, VALID_DATE_5DAYS_BEFORE_START).iterator().next();

		assertEquals(OxygenConstants.Categories.E_ID, tank.getOxygenTankCategory().getId());
		assertTrue(tank.getReadyDate().isBefore(DateConstants.START_DATE));
	}

	@Test
	void oxygenCategory_shouldBeCategoryE_whenOrderIsOnTheStartingDateOfFestival() {
		OxygenTank tank = context.subject.order(nebulaQuality, DateConstants.START_DATE).iterator().next();

		assertEquals(OxygenConstants.Categories.E_ID, tank.getOxygenTankCategory().getId());
		assertTrue(tank.getReadyDate().isEqual(DateConstants.START_DATE));
	}

	@Test
	void createOxygenTank_afterTheStartingDateOfFestival_shouldThrowInvalidEventDateException() {
		InvalidDateException thrown = assertThrows(
				InvalidDateException.class,
				() -> context.subject.order(nebulaQuality, INVALID_DATE)
		);

		assertEquals(ExceptionConstants.INVALID_DATE_MESSAGE, thrown.getMessage());
	}
}
