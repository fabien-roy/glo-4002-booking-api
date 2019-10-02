package ca.ulaval.glo4002.booking.parsers;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.builders.oxygen.OxygenCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.DateConstants;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.dto.OxygenTankDto;
import ca.ulaval.glo4002.booking.entities.OxygenTankEntity;

class OxygenTankParserTest {

	private static final LocalDate A_VALID_DATE = DateConstants.START_DATE.minusDays(20);

	private OxygenTankParser subject;
	private OxygenTankDto oxygenTankDto = new OxygenTankDto();
	private OxygenCategoryBuilder categoryBuilder = new OxygenCategoryBuilder();
	private OxygenTank oxygenTank;

	@BeforeEach
	void setUp() {
		subject = new OxygenTankParser();
		oxygenTankDto.oxygenCategory = OxygenConstants.Categories.A_ID;
		oxygenTankDto.requestDate = A_VALID_DATE.toString();

		oxygenTank = new OxygenTank(categoryBuilder.buildById(OxygenConstants.Categories.B_ID), A_VALID_DATE,
				A_VALID_DATE);
	}

	// TODO : More Actual parser tests

	@Test
	void whenParsingEntity_orderShouldBeValid() {
		OxygenTankEntity entity = subject.toEntity(oxygenTank);

		OxygenTank parsedOxygenTank = subject.parseEntity(entity);

		assertEquals(entity.getId(), parsedOxygenTank.getId());
		assertEquals(entity.getRequestDate(), parsedOxygenTank.getRequestDate());
		assertEquals(entity.getReadyDate(), parsedOxygenTank.getReadyDate());
	}

	void whenParsingToEntity_entityShouldBeValid() {
		OxygenTankEntity entity = subject.toEntity(oxygenTank);

		assertEquals(oxygenTank.getId(), entity.getId());
		assertEquals(oxygenTank.getRequestDate(), entity.getRequestDate());
		assertEquals(oxygenTank.getReadyDate(), entity.getReadyDate());
	}

	// TODO : Most logic here must be moved to OxygenTankServiceTest

	/*
	 * @Test void whenParseEntityToOxygenTank_ThenOxygenTankIsCreate() {
	 * OxygenTankEntity entity = new
	 * OxygenTankEntity(OxygenConstants.Categories.A_ID, A_VALID_DATE);
	 * 
	 * OxygenTank tank = subject.parseEntity(entity);
	 * 
	 * assertNotNull(tank.getOxygenTankCategory().getId());
	 * assertEquals(OxygenConstants.Categories.A_ID,
	 * tank.getOxygenTankCategory().getId()); assertNotNull(tank.getRequestDate());
	 * assertEquals(A_VALID_DATE, tank.getRequestDate()); }
	 * 
	 * @Test void whenParseOxygenTankToEntity_ThenEntityIsCreate() { OxygenTank tank
	 * = this.createOxygenTank();
	 * 
	 * OxygenTankEntity entity = subject.toEntity(tank);
	 * 
	 * assertNotNull(entity.categoryId);
	 * assertEquals(OxygenConstants.Categories.A_ID, entity.categoryId);
	 * assertNotNull(entity.requestDate); // assertEquals(A_VALID_DATE,
	 * entity.requestDate); }
	 * 
	 * private OxygenTank createOxygenTank() { OxygenCategoryBuilder categoryBuilder
	 * = new OxygenCategoryBuilder();
	 * 
	 * return new
	 * OxygenTank(categoryBuilder.buildById(OxygenConstants.Categories.A_ID),
	 * A_VALID_DATE); }
	 */
}
