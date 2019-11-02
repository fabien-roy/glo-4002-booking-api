package ca.ulaval.glo4002.booking.mappers;

import org.junit.jupiter.api.BeforeEach;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTankInventory;

public class OxygenTankInventoryMapperTest {

	private OxygenTankInventoryMapper subject;
	private OxygenTankInventory inventory;

	@BeforeEach
	void setUp() {
		subject = new OxygenTankInventoryMapper();
		// setupInventory
		inventory = new OxygenTankInventory();
	}

	void toDto_shouldBuildDtoWithCorrectOxygenCategories() {
		// TODO do this test
	}

	void toDto_shouldBuildDtoWithCorrectTanksQuantity() {
		// TODO do this test
	}
}
