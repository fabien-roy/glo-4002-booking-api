package ca.ulaval.glo4002.booking.oxygen.inventory.rest.mappers;

import ca.ulaval.glo4002.booking.oxygen.domain.OxygenCategories;
import ca.ulaval.glo4002.booking.oxygen.inventory.domain.OxygenInventory;
import ca.ulaval.glo4002.booking.oxygen.inventory.rest.OxygenInventoryItemDto;
import ca.ulaval.glo4002.booking.oxygen.inventory.rest.mappers.OxygenInventoryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OxygenInventoryMapperTest {

	private static final OxygenCategories CATEGORY_A = OxygenCategories.A;
	private static final OxygenCategories CATEGORY_B = OxygenCategories.B;
	private static final OxygenCategories CATEGORY_E = OxygenCategories.E;

	private static  final Integer CATEGORY_A_NUMBER = 5;
	private static  final Integer CATEGORY_B_NUMBER = 10;
	private static  final Integer CATEGORY_E_NUMBER = 15;

    private OxygenInventoryMapper oxygenInventoryMapper;
    private OxygenInventory inventory;

    @BeforeEach
    void setUpSubject() {
        oxygenInventoryMapper = new OxygenInventoryMapper();
    }

    @BeforeEach
    void setUpInventory() {
        inventory = mock(OxygenInventory.class);
        when(inventory.getAllQuantityByCategory(CATEGORY_A)).thenReturn(CATEGORY_A_NUMBER);
        when(inventory.getAllQuantityByCategory(CATEGORY_B)).thenReturn(CATEGORY_B_NUMBER);
        when(inventory.getAllQuantityByCategory(CATEGORY_E)).thenReturn(CATEGORY_E_NUMBER);
    }

    @Test
    void toDto_shouldBuildDtoWithCorrectOxygenCategoriesOrder() {
        List<OxygenInventoryItemDto> oxygenInventoryItemDto = oxygenInventoryMapper.toDto(inventory);

        assertEquals(OxygenCategories.E.toString(), oxygenInventoryItemDto.get(0).getGradeTankOxygen());
        assertEquals(OxygenCategories.B.toString(), oxygenInventoryItemDto.get(1).getGradeTankOxygen());
        assertEquals(OxygenCategories.A.toString(), oxygenInventoryItemDto.get(2).getGradeTankOxygen());
    }

    @Test
    void toDto_shouldBuildDtoWithCorrectTanksQuantityE() {
        List<OxygenInventoryItemDto> oxygenInventoryItemDto = oxygenInventoryMapper.toDto(inventory);
        Integer quantityE = Math.toIntExact(oxygenInventoryItemDto.get(0).getQuantity());

        assertEquals(CATEGORY_E_NUMBER, quantityE);
    }

    @Test
    void toDto_shouldBuildDtoWithCorrectTanksQuantityB() {
        List<OxygenInventoryItemDto> oxygenInventoryItemDto = oxygenInventoryMapper.toDto(inventory);
        Integer quantityB = Math.toIntExact(oxygenInventoryItemDto.get(1).getQuantity());

        assertEquals(CATEGORY_B_NUMBER, quantityB);
    }

    @Test
    void toDto_shouldBuildDtoWithCorrectTanksQuantityA() {
        List<OxygenInventoryItemDto> oxygenInventoryItemDto = oxygenInventoryMapper.toDto(inventory);
        Integer quantityA = Math.toIntExact(oxygenInventoryItemDto.get(2).getQuantity());

        assertEquals(CATEGORY_A_NUMBER, quantityA);
    }
}
