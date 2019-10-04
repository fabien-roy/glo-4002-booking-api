package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTankInventory;
import ca.ulaval.glo4002.booking.dto.InventoryItemDto;
import ca.ulaval.glo4002.booking.entities.OxygenTankInventoryEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OxygenTankOxygenTankInventoryParserTest {

	private OxygenTankInventoryParser subject;
	private OxygenTankInventory oxygenTankInventory;
	private OxygenTankInventory oxygenTankInventoryOneEmptyCategory;
	private OxygenTankInventory anEmptyOxygenTankInventory;
	private static final Long A_VALID_NUMBER_OF_TANK_CATEGORY_A_STORED = 1L;
	private static final Long A_VALID_NUMBER_OF_TANK_CATEGORY_B_STORED = 2L;
	private static final Long A_VALID_NUMBER_OF_TANK_CATEGORY_E_STORED = 3L;

	@BeforeEach
	void setup() {
		Map<Long, Long> inUseTanks = new HashMap<>();
		Map<Long, Long> notInUseTanks = new HashMap<>();
		Map<Long, Long> notInUseOneEmptyCategory = new HashMap<>();
		Map<Long, Long> inUseOneEmptyCategory = new HashMap<>();

		inUseTanks.put(OxygenConstants.Categories.A_ID, A_VALID_NUMBER_OF_TANK_CATEGORY_A_STORED);
		inUseTanks.put(OxygenConstants.Categories.B_ID, A_VALID_NUMBER_OF_TANK_CATEGORY_B_STORED);
		inUseTanks.put(OxygenConstants.Categories.E_ID, A_VALID_NUMBER_OF_TANK_CATEGORY_E_STORED);

		notInUseTanks.put(OxygenConstants.Categories.A_ID, A_VALID_NUMBER_OF_TANK_CATEGORY_A_STORED);
		notInUseTanks.put(OxygenConstants.Categories.B_ID, A_VALID_NUMBER_OF_TANK_CATEGORY_B_STORED);
		notInUseTanks.put(OxygenConstants.Categories.E_ID, A_VALID_NUMBER_OF_TANK_CATEGORY_E_STORED);

		notInUseOneEmptyCategory.put(OxygenConstants.Categories.A_ID, 0L);
		notInUseOneEmptyCategory.put(OxygenConstants.Categories.B_ID, 0L);
		notInUseOneEmptyCategory.put(OxygenConstants.Categories.E_ID, 0L);

        inUseOneEmptyCategory.put(OxygenConstants.Categories.A_ID, A_VALID_NUMBER_OF_TANK_CATEGORY_A_STORED);
        inUseOneEmptyCategory.put(OxygenConstants.Categories.B_ID, A_VALID_NUMBER_OF_TANK_CATEGORY_B_STORED);
        inUseOneEmptyCategory.put(OxygenConstants.Categories.E_ID, 0L);

		anEmptyOxygenTankInventory = new OxygenTankInventory(new HashMap<>(), new HashMap<>());
		oxygenTankInventory = new OxygenTankInventory(notInUseTanks, inUseTanks);
		oxygenTankInventoryOneEmptyCategory = new OxygenTankInventory(notInUseOneEmptyCategory, inUseOneEmptyCategory);
		subject = new OxygenTankInventoryParser();
	}

	@Test
	void whenParsingEntity_InventoryShouldBeValid() {
		OxygenTankInventoryEntity entity = subject.toEntity(oxygenTankInventory);

		OxygenTankInventory parsedOxygenTankInventory = subject.parseEntity(entity);

		assertEquals(entity.getInUseTanks().get(0).getQuantity(), parsedOxygenTankInventory.getInUseTanks().get(OxygenConstants.Categories.E_ID));
		assertEquals(entity.getInUseTanks().get(1).getQuantity(), parsedOxygenTankInventory.getInUseTanks().get(OxygenConstants.Categories.B_ID));
		assertEquals(entity.getInUseTanks().get(2).getQuantity(), parsedOxygenTankInventory.getInUseTanks().get(OxygenConstants.Categories.A_ID));

		assertEquals(entity.getNotInUseTanks().get(0).getQuantity(), parsedOxygenTankInventory.getNotInUseTanks().get(OxygenConstants.Categories.E_ID));
		assertEquals(entity.getInUseTanks().get(1).getQuantity(), parsedOxygenTankInventory.getNotInUseTanks().get(OxygenConstants.Categories.B_ID));
		assertEquals(entity.getInUseTanks().get(2).getQuantity(), parsedOxygenTankInventory.getNotInUseTanks().get(OxygenConstants.Categories.A_ID));
	}

	@Test
	void whenParsingToEntity_entityShouldBeValid() {
		OxygenTankInventoryEntity entity = subject.toEntity(oxygenTankInventory);

		assertEquals(A_VALID_NUMBER_OF_TANK_CATEGORY_E_STORED, entity.getInUseTanks().get(0).getQuantity());
		assertEquals(A_VALID_NUMBER_OF_TANK_CATEGORY_B_STORED, entity.getInUseTanks().get(1).getQuantity());
		assertEquals(A_VALID_NUMBER_OF_TANK_CATEGORY_A_STORED, entity.getInUseTanks().get(2).getQuantity());
	}

	@Test
	void whenParsingToDto_dtoShouldBeValid() {
		List<InventoryItemDto> dtos = subject.toDto(oxygenTankInventory);
		Long totalTankCategoryA = A_VALID_NUMBER_OF_TANK_CATEGORY_A_STORED * 2;
		Long totalTankCategoryB = A_VALID_NUMBER_OF_TANK_CATEGORY_B_STORED * 2;
		Long totalTankCategoryE = A_VALID_NUMBER_OF_TANK_CATEGORY_E_STORED * 2;

		assertEquals("E", dtos.get(0).gradeTankOxygen);
		assertEquals(totalTankCategoryE, dtos.get(0).quantity);
		assertEquals("B", dtos.get(1).gradeTankOxygen);
		assertEquals(totalTankCategoryB, dtos.get(1).quantity);
		assertEquals("A", dtos.get(2).gradeTankOxygen);
		assertEquals(totalTankCategoryA, dtos.get(2).quantity);
	}

	@Test
	void whenParsingToDto_andAllCategoryInInventoryIsEmpty_thenNothingShouldBeAddedToTheDto() {
		List<InventoryItemDto> dtos = subject.toDto(anEmptyOxygenTankInventory);

		assertEquals(0, dtos.size());
	}

	@Test
	void whenParsingToDto_andOneCategoryInTheInventoryIsEmpty_thenItShouldNotBeAddedToTheDto() {
		List<InventoryItemDto> dtos = subject.toDto(oxygenTankInventoryOneEmptyCategory);

		assertEquals(2, dtos.size());
	}

}
