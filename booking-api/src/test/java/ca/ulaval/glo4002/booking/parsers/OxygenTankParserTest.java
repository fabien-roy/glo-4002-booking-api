package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.dto.OxygenTankDto;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

class OxygenTankParserTest {

	private static final LocalDate A_VALID_DATE = FestivalConstants.Dates.START_DATE.minusDays(20);

	private OxygenTankParser subject;
	private OxygenTankDto oxygenTankDto = new OxygenTankDto();

	@BeforeEach
	void setUp() {
		subject = new OxygenTankParser();
		oxygenTankDto.oxygenCategory = OxygenConstants.Categories.A_ID;
		oxygenTankDto.requestDate = A_VALID_DATE.toString();
	}

	// TODO : Holy fuck. This file checks logic that has absolutely nothing to do with OxygenTankParser

    /*
	@Test
	void whenParseEntityToOxygenTank_ThenOxygenTankIsCreate() {
		OxygenTankEntity entity = new OxygenTankEntity(OxygenConstants.Categories.A_ID, A_VALID_DATE);

		OxygenTank tank = subject.parseEntity(entity);

		assertNotNull(tank.getOxygenTankCategory().getId());
		assertEquals(OxygenConstants.Categories.A_ID, tank.getOxygenTankCategory().getId());
		assertNotNull(tank.getTimeRequested());
		assertEquals(A_VALID_DATE, tank.getTimeRequested());
	}

	@Test
	void whenParseOxygenTankToEntity_ThenEntityIsCreate() {
		OxygenTank tank = this.createOxygenTank();

		OxygenTankEntity entity = subject.toEntity(tank);

		assertNotNull(entity.categoryId);
		assertEquals(OxygenConstants.Categories.A_ID, entity.categoryId);
		assertNotNull(entity.requestDate); //
		assertEquals(A_VALID_DATE, entity.requestDate);
	}

	private OxygenTank createOxygenTank() {
		OxygenCategoryBuilder categoryBuilder = new OxygenCategoryBuilder();

		return new OxygenTank(categoryBuilder.buildById(OxygenConstants.Categories.A_ID), A_VALID_DATE);
	}
	*/
}
