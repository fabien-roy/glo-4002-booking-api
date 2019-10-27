package ca.ulaval.glo4002.booking.domain.oxygen;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import ca.ulaval.glo4002.booking.enums.OxygenTankCategory;

public class OxygenTankInventory {

	private Map<OxygenTankCategory, List<OxygenTank>> notInUseTanks;
	private Map<OxygenTankCategory, List<OxygenTank>> inUseTanks;

	public OxygenTankInventory() {
		this.notInUseTanks = new EnumMap<>(OxygenTankCategory.class);
		this.inUseTanks = new EnumMap<>(OxygenTankCategory.class);

		notInUseTanks.put(OxygenTankCategory.CATEGORY_A, new ArrayList<OxygenTank>());
		notInUseTanks.put(OxygenTankCategory.CATEGORY_B, new ArrayList<OxygenTank>());
		notInUseTanks.put(OxygenTankCategory.CATEGORY_E, new ArrayList<OxygenTank>());

		inUseTanks.put(OxygenTankCategory.CATEGORY_A, new ArrayList<OxygenTank>());
		inUseTanks.put(OxygenTankCategory.CATEGORY_B, new ArrayList<OxygenTank>());
		inUseTanks.put(OxygenTankCategory.CATEGORY_E, new ArrayList<OxygenTank>());
	}

	// TODO : not sure if needed
	public List<OxygenTank> getNotInUseByCategory(OxygenTankCategory category) {
		return notInUseTanks.get(category);
	}

	// TODO : not sure if needed
	public List<OxygenTank> getInUseByCategory(OxygenTankCategory category) {
		return inUseTanks.get(category);
	}

	// TODO : not sure if needed, used in test
	public Integer getNotInUseQuantityByCategory(OxygenTankCategory category) {
		return notInUseTanks.get(category).size();
	}

	// TODO : not sure if needed, used in test
	public Integer getInUseQuantityByCategory(OxygenTankCategory category) {
		return inUseTanks.get(category).size();
	}

	public void addTankToInventory(OxygenTankCategory category, List<OxygenTank> newTanks) {
		notInUseTanks.get(category).addAll(newTanks);
	}

	public Integer requestTankByCategory(OxygenTankCategory category, Integer quantity) {
		Integer quantityStillNeeded = quantity;
		// TODO : Better name ?
		List<OxygenTank> notInUseCategory = notInUseTanks.get(category);
		List<OxygenTank> inUseCategory = inUseTanks.get(category);

		while (notInUseCategory.size() > 0 && quantityStillNeeded > 0) {
			inUseCategory.add(notInUseCategory.remove(notInUseCategory.size() - 1));

			quantityStillNeeded--;
		}

		return quantityStillNeeded;
	}
}