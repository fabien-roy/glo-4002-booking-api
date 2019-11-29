package ca.ulaval.glo4002.booking.oxygen.inventory;

import ca.ulaval.glo4002.booking.oxygen.domain.OxygenTank;

import java.util.List;

public interface OxygenInventoryRepository {

	OxygenInventory getInventory();

	void setInventory(OxygenInventory inventory);

    List<OxygenTank> findall();
}
