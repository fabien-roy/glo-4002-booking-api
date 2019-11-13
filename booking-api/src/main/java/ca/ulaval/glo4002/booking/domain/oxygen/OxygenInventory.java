package ca.ulaval.glo4002.booking.domain.oxygen;

import ca.ulaval.glo4002.booking.enums.OxygenCategories;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class OxygenInventory {

	private Map<OxygenCategories, List<OxygenTank>> notInUseTanks;
	private Map<OxygenCategories, List<OxygenTank>> inUseTanks;
	private ArrayList<OxygenCategories> categoriesOrder;

	public OxygenInventory() {
		this.notInUseTanks = new EnumMap<>(OxygenCategories.class);
		this.inUseTanks = new EnumMap<>(OxygenCategories.class);
		this.categoriesOrder = new ArrayList<>();

		notInUseTanks.put(OxygenCategories.A, new ArrayList<>());
		notInUseTanks.put(OxygenCategories.B, new ArrayList<>());
		notInUseTanks.put(OxygenCategories.E, new ArrayList<>());

		inUseTanks.put(OxygenCategories.A, new ArrayList<>());
		inUseTanks.put(OxygenCategories.B, new ArrayList<>());
		inUseTanks.put(OxygenCategories.E, new ArrayList<>());

		categoriesOrder.add(OxygenCategories.A);
		categoriesOrder.add(OxygenCategories.B);
		categoriesOrder.add(OxygenCategories.E);
	}

	public List<OxygenTank> getNotInUseTankByCategory(OxygenCategories category) {
		return notInUseTanks.get(category);
	}

	public List<OxygenTank> getInUseTanksByCategory(OxygenCategories category) {
		return inUseTanks.get(category);
	}

	public List<OxygenTank> getAllTanksByCategory(OxygenCategories category) {
		List<OxygenTank> tanks = new ArrayList<>();

		tanks.addAll(getNotInUseTankByCategory(category));
		tanks.addAll(getInUseTanksByCategory(category));

		return tanks;
	}

	public List<OxygenTank> getAllTanks() {
		List<OxygenTank> tanks = new ArrayList<>();

		inUseTanks.keySet().forEach(key -> tanks.addAll(getAllTanksByCategory(key)));

		return tanks;
	}

	public Integer getNotInUseQuantityByCategory(OxygenCategories category) {
		return notInUseTanks.get(category).size();
	}

	public Integer getInUseQuantityByCategory(OxygenCategories category) {
		return inUseTanks.get(category).size();
	}

	public Integer getAllQuantityByCategory(OxygenCategories category) {
		Integer used = getInUseQuantityByCategory(category);
		Integer notUsed = getNotInUseQuantityByCategory(category);

		return used + notUsed;
	}

	public void addTanksToInventory(OxygenCategories category, List<OxygenTank> newTanks) {
		notInUseTanks.get(category).addAll(newTanks);
	}

	public Integer requestTankByCategory(OxygenCategories baseCategory, OxygenCategories maxCategory, Integer quantity) {
		Integer quantityStillNeeded = quantity;
		int basePosition = categoriesOrder.indexOf(baseCategory);
		int maxPosition = categoriesOrder.indexOf(maxCategory);

		for(int i = basePosition; i <= maxPosition; i++){
			OxygenCategories currentCategory = categoriesOrder.get(i);

			List<OxygenTank> notInUseTanksForCategory = notInUseTanks.get(currentCategory);
			List<OxygenTank> inUseTanksForCategory = inUseTanks.get(currentCategory);

			while (!notInUseTanksForCategory.isEmpty() && quantityStillNeeded > 0) {
				OxygenTank transferredTank = notInUseTanksForCategory.remove(notInUseTanksForCategory.size() - 1);
				inUseTanksForCategory.add(transferredTank);

				quantityStillNeeded--;
			}
		}

		return quantityStillNeeded;
	}

	// TODO : Test OxygenInventory.getHistory
	public OxygenHistory getHistory() {
		List<OxygenTank> allTanks = getAllTanks();

		return new OxygenHistory(allTanks);
	}
}