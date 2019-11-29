package ca.ulaval.glo4002.booking.oxygen.inventory.infrastructure;

import ca.ulaval.glo4002.booking.oxygen.domain.OxygenTank;
import ca.ulaval.glo4002.booking.oxygen.inventory.domain.OxygenInventory;

import java.util.List;

public interface OxygenInventoryRepository {

	OxygenInventory getInventory();

	void setInventory(OxygenInventory inventory);

    List<OxygenTank> findAll();
}
