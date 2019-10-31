package ca.ulaval.glo4002.booking.domain.oxygen;

import java.util.*;

import ca.ulaval.glo4002.booking.enums.OxygenCategory;

public class OxygenTankInventory {

	private Map<OxygenCategory, List<OxygenTank>> notInUseTanks;
	private Map<OxygenCategory, List<OxygenTank>> inUseTanks;

	public OxygenTankInventory() {
		this.notInUseTanks = new EnumMap<>(OxygenCategory.class);
		this.inUseTanks = new EnumMap<>(OxygenCategory.class);

		notInUseTanks.put(OxygenCategory.A, new ArrayList<>());
		notInUseTanks.put(OxygenCategory.B, new ArrayList<>());
		notInUseTanks.put(OxygenCategory.E, new ArrayList<>());

		inUseTanks.put(OxygenCategory.A, new ArrayList<>());
		inUseTanks.put(OxygenCategory.B, new ArrayList<>());
		inUseTanks.put(OxygenCategory.E, new ArrayList<>());
	}

	public List<OxygenTank> getNotInUseTankByCategory(OxygenCategory category) {
		return notInUseTanks.get(category);
	}

	public List<OxygenTank> getInUseTanksByCategory(OxygenCategory category) {
		return inUseTanks.get(category);
	}

	public Integer getNotInUseQuantityByCategory(OxygenCategory category) {
		return notInUseTanks.get(category).size();
	}

	public Integer getInUseQuantityByCategory(OxygenCategory category) {
		return inUseTanks.get(category).size();
	}

	public void addTanksToInventory(OxygenCategory category, List<OxygenTank> newTanks) {
		notInUseTanks.get(category).addAll(newTanks);
	}

	public Integer requestTankByCategory(OxygenCategory category, Integer quantity) {
		Integer quantityStillNeeded = quantity;
		List<OxygenTank> notInUseTanksForCategory = notInUseTanks.get(category);
		List<OxygenTank> inUseTanksForCategory = inUseTanks.get(category);

		while (!notInUseTanksForCategory.isEmpty() && quantityStillNeeded > 0) {
			inUseTanksForCategory.add(notInUseTanksForCategory.remove(notInUseTanksForCategory.size() - 1));

			quantityStillNeeded--;
		}

		return quantityStillNeeded;
	}

	// TODO : OXY is it ok ?
	public Set<OxygenCategory> getKeys() {
		return inUseTanks.keySet();
	}
}