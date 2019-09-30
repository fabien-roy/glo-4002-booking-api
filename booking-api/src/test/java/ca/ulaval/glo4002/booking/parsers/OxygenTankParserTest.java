package ca.ulaval.glo4002.booking.parsers;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.builders.oxygen.OxygenCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainObjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.dto.OxygenTankDto;
import ca.ulaval.glo4002.booking.entities.OxygenTankEntity;
import ca.ulaval.glo4002.booking.exceptions.oxygen.OxygenCategoryNotFoundException;

class OxygenTankParserTest {

	private OxygenTankParser subject;
	private OxygenTankDto oxygenTankDto = new OxygenTankDto();
	private static final Long AN_INVALID_CATEGORY = 54L;
	public final static LocalDate A_VALID_DATE = LocalDate.of(2050, 6, 20);

	@BeforeEach
	void setUp() {
		subject = new OxygenTankParser();
		oxygenTankDto.oxygenCategory = OxygenConstants.Categories.A_ID;
		oxygenTankDto.requestDate = this.A_VALID_DATE.toString();
	}

	@Test
	void whenCategoryIsInvalid_thenCategoryNotFoundExceptionIsThrown() {
		oxygenTankDto.oxygenCategory = AN_INVALID_CATEGORY;

		OxygenCategoryNotFoundException thrown = assertThrows(OxygenCategoryNotFoundException.class,
				() -> subject.parseDto(oxygenTankDto));

		assertEquals(ExceptionConstants.OXYGEN_CATEGORY_NOT_FOUND_MESSAGE, thrown.getMessage());
	}

	@Test
	void whenCategoryIsValid_thenCategoryIsAssignedToOxygenTank() {
		oxygenTankDto.oxygenCategory = OxygenConstants.Categories.A_ID;
		OxygenTank tank = subject.parseDto(oxygenTankDto);

		assertNotNull(tank.getOxygenTankCategory().getId());
		assertEquals(OxygenConstants.Categories.A_ID, tank.getOxygenTankCategory().getId());
	}

	@Test
	void whenParseDtoToOxygenTank_ThenOxygenTankIsCreate() {
		oxygenTankDto.oxygenCategory = OxygenConstants.Categories.A_ID;
		oxygenTankDto.requestDate = this.A_VALID_DATE.toString();
		OxygenTank tank = subject.parseDto(oxygenTankDto);

		assertNotNull(tank.getOxygenTankCategory().getId());
		assertEquals(OxygenConstants.Categories.A_ID, tank.getOxygenTankCategory().getId());
		assertNotNull(tank.getTimeRequested());
		assertNotNull(tank.getTimeRequested());
		assertEquals(this.A_VALID_DATE, tank.getTimeRequested());
	}

	@Test
	void whenParseEntityToOxygenTank_ThenOxygenTankIsCreate() {
		OxygenTankEntity entity = new OxygenTankEntity(OxygenConstants.Categories.A_ID, this.A_VALID_DATE);
		OxygenTank tank = subject.parseEntity(entity);

		assertNotNull(tank.getOxygenTankCategory().getId());
		assertEquals(OxygenConstants.Categories.A_ID, tank.getOxygenTankCategory().getId());
		assertNotNull(tank.getTimeRequested());
		assertEquals(this.A_VALID_DATE, tank.getTimeRequested());
	}

	@Test
	void whenParseOxygenTankToEntity_ThenEntityIsCreate() {
		OxygenTank tank = this.createOxygenTank();
		OxygenTankEntity entity = subject.toEntity(tank);

		assertNotNull(entity.catId);
		assertEquals(OxygenConstants.Categories.A_ID, entity.catId);
		assertNotNull(entity.requestDate); //
		assertEquals(this.A_VALID_DATE, entity.requestDate);
	}

	@Test
	void whenParseOxygenTankToDto_ThenDtoIsCreate() {
		OxygenTank tank = this.createOxygenTank();
		OxygenTankDto dto = subject.toDto(tank);

		assertNotNull(dto.oxygenCategory);
		assertEquals(OxygenConstants.Categories.A_ID, dto.oxygenCategory);
		assertNotNull(dto.requestDate);
		assertEquals(this.A_VALID_DATE.toString(), dto.requestDate);
	}

	private OxygenTank createOxygenTank() {
		OxygenCategoryBuilder categoryBuilder = new OxygenCategoryBuilder();
		return new OxygenTank(categoryBuilder.buildById(OxygenConstants.Categories.A_ID), this.A_VALID_DATE);
	}

}
