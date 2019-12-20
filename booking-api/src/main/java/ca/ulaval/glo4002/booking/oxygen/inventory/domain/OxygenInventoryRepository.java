package ca.ulaval.glo4002.booking.oxygen.inventory.domain;

import ca.ulaval.glo4002.booking.oxygen.domain.OxygenTank;

import java.util.List;

public interface OxygenInventoryRepository {

	OxygenInventory getInventory();

	void updateInventory(OxygenInventory inventory);

    List<OxygenTank> findAll();
}
