package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryServiceTest {

    private InventoryServiceContext context;

    @BeforeEach
    public void setUp() {
        context = new InventoryServiceContext();
    }

    // TODO : Solve this test
    @Test
    void whenOxygenTankIsRequestedAndInventoryIsInSurplus_thenTankInUseIsUpdated() {
        context.subject.addTank(
                context.anOxygenTank.getOxygenTankCategory().getId(),
                20L
        );

        context.subject.requestOxygenTanks(context.anOxygenTank);
        Long inUseTanks = context.anInventory.getInUseTanksByCategoryId(context.anOxygenTank.getOxygenTankCategory().getId());
        Long expectedInUseTanks = context.anOxygenTank.getOxygenTankCategory().getProduction().getProducedTanks();

        assertEquals(expectedInUseTanks, inUseTanks);
    }

    // TODO : Solve test
    @Test
    void whenOxygenTankIsRequestedAndInventoryHaveAPortionInSurplus_thenTankInUseIsUpdated() {
        context.subject.addTank(
                context.anOxygenTank.getOxygenTankCategory().getId(),
                3L
        );

        context.subject.requestOxygenTanks(context.anOxygenTank);
        /*
        Long inUseTanks = context.anInventory.getInUseTanksByCategoryId(context.anOxygenTank.getOxygenTankCategory().getId());
        Long expectedInUseTanks = context.anOxygenTank.getOxygenTankCategory().getProduction().getProducedTanks();
        */

        assertEquals(3, (long) context.anInventory.getInUseTanksByCategoryId(OxygenConstants.Categories.E_ID));
    }

    // TODO : Solve this test
    /*
    @Test
    void whenOxygenTankIsRequestedAndThereIsNoSurplus_thenShouldReturnTheQuantityRequested() {
        context.subject.requestOxygenTanks(context.anOxygenTank);

        assertEquals(10, (long) context.anInventory.?);
    }
    */
}
