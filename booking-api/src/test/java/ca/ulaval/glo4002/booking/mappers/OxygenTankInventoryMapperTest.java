package ca.ulaval.glo4002.booking.mappers;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTankInventory;
import ca.ulaval.glo4002.booking.dto.OxygenTankInventoryDto;

public class OxygenTankInventoryMapperTest {

	private OxygenTankInventoryMapper subject;
	private OxygenTankInventory inventory;

	@BeforeEach
	void setUpSubject() {
		subject = new OxygenTankInventoryMapper();
	}

	@BeforeEach
	void setUpInventory() {
		// TODO should we add tanks in inventory to test?
		inventory = new OxygenTankInventory();
	}

	void toDto_shouldBuildDtoWithCorrectOxygenCategories() {
		List<OxygenTankInventoryDto> oxygenTankInventoryDto = subject.toDto(inventory);
		// TODO do this test. Did not have time to finish it.
	}

	void toDto_shouldBuildDtoWithCorrectTanksQuantity() {
		List<OxygenTankInventoryDto> oxygenTankInventoryDto = subject.toDto(inventory);
		// TODO do this test. Did not have time to finish it.
	}
}
