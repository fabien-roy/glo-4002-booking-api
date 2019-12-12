package ca.ulaval.glo4002.booking.oxygen.inventory.domain;

import ca.ulaval.glo4002.booking.oxygen.domain.OxygenCategories;
import ca.ulaval.glo4002.booking.oxygen.domain.OxygenTank;

import java.util.*;

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

	public List<OxygenTank> getAllTanksForCategory(OxygenCategories category) {
		List<OxygenTank> categoryList = new ArrayList<>();

		categoryList.addAll(inUseTanks.get(category));
		categoryList.addAll(notInUseTanks.get(category));

		return categoryList;
	}

	public List<OxygenTank> getAllTanks() {
		List<OxygenTank> allTanks = new ArrayList<>();

		inUseTanks.keySet().forEach(category -> allTanks.addAll(inUseTanks.get(category)));
		notInUseTanks.keySet().forEach(category -> allTanks.addAll(notInUseTanks.get(category)));

		return allTanks;
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

	public Integer assignTanksByCategory(OxygenCategories baseCategory, OxygenCategories maxCategory, Integer quantity) {
		Integer quantityStillNeeded = quantity;
		int basePosition = categoriesOrder.indexOf(baseCategory); // TODO : Recheck index usage, it is dangerous
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
}