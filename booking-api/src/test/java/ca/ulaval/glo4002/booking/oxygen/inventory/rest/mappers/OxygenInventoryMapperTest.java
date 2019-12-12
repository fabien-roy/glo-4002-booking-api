package ca.ulaval.glo4002.booking.oxygen.inventory.rest.mappers;

import ca.ulaval.glo4002.booking.oxygen.domain.OxygenCategories;
import ca.ulaval.glo4002.booking.oxygen.inventory.domain.OxygenInventory;
import ca.ulaval.glo4002.booking.oxygen.inventory.rest.OxygenInventoryItemResponse;
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
	private static final int CATEGORY_A_QUANTITY = 5;
	private static final int CATEGORY_B_QUANTITY = 10;
	private static final int CATEGORY_E_QUANTITY = 15;

    private OxygenInventoryMapper oxygenInventoryMapper;
    private OxygenInventory inventory;

    @BeforeEach
    void setUpSubject() {
        oxygenInventoryMapper = new OxygenInventoryMapper();
    }

    @BeforeEach
    void setUpInventory() {
        inventory = mock(OxygenInventory.class);
        when(inventory.getAllQuantityByCategory(CATEGORY_A)).thenReturn(CATEGORY_A_QUANTITY);
        when(inventory.getAllQuantityByCategory(CATEGORY_B)).thenReturn(CATEGORY_B_QUANTITY);
        when(inventory.getAllQuantityByCategory(CATEGORY_E)).thenReturn(CATEGORY_E_QUANTITY);
    }

    @Test
    void toResponse_shouldBuildResponseWithCorrectTanksQuantityE() {
        List<OxygenInventoryItemResponse> oxygenInventoryItemResponse = oxygenInventoryMapper.toResponse(inventory);
        int quantityE = oxygenInventoryItemResponse.get(0).getQuantity();

        assertEquals(CATEGORY_E_QUANTITY, quantityE);
    }

    @Test
    void toResponse_shouldBuildResponseWithCorrectTanksQuantityB() {
        List<OxygenInventoryItemResponse> oxygenInventoryItemResponse = oxygenInventoryMapper.toResponse(inventory);
        int quantityB = oxygenInventoryItemResponse.get(1).getQuantity();

        assertEquals(CATEGORY_B_QUANTITY, quantityB);
    }

    @Test
    void toResponse_shouldBuildResponseWithCorrectTanksQuantityA() {
        List<OxygenInventoryItemResponse> oxygenInventoryItemResponse = oxygenInventoryMapper.toResponse(inventory);
        int quantityA = oxygenInventoryItemResponse.get(2).getQuantity();

        assertEquals(CATEGORY_A_QUANTITY, quantityA);
    }
}
