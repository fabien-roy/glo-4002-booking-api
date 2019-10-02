package ca.ulaval.glo4002.booking.entities.oxygen;

import static java.time.temporal.ChronoUnit.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.builders.oxygen.OxygenCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.DateConstants;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;

public class OxygenTankTest {

	private final LocalDate VALID_DATE = DateConstants.START_DATE.minus(30, DAYS);
	private OxygenTank subject;
	private OxygenCategoryBuilder oxygenCategoryBuilder;

	@BeforeEach
	void setup() {
		oxygenCategoryBuilder = new OxygenCategoryBuilder();
		subject = new OxygenTank(oxygenCategoryBuilder.buildById(OxygenConstants.Categories.A_ID), VALID_DATE,
				VALID_DATE);
	}

	@Test
	void whenOxygenTankIsCreated_thenCategoryIsAssigned() {
		assertNotNull(subject.getOxygenTankCategory());
	}

	@Test
	void whenOxygenTankIsCreated_thenRequestDateIsAssigned() {
		assertNotNull(subject.getRequestDate());
	}

	@Test
	void whenOxygenTankIsCreated_thenReadyDateIsAssigned() {
		assertNotNull(subject.getReadyDate());
	}
}
