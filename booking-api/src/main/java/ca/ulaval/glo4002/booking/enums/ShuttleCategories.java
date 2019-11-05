package ca.ulaval.glo4002.booking.enums;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4002.booking.exceptions.shuttles.InvalidShuttleCategoryException;

public enum ShuttleCategories {
	
	ET_SPACESHIP("ETSpaceship"),
	MILLENNIUM_FALCON("Millennium Falcon"),
	SPACE_X("Space X");
	
	private String category;
	private static final Map<String, ShuttleCategories> lookup = new HashMap<>();

	static {
		for(ShuttleCategories category : ShuttleCategories.values()) {
			lookup.put(category.toString(), category);
		}
	}
	
	ShuttleCategories(String category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return category;
	}
	
	public static ShuttleCategories get(String category) {
		ShuttleCategories foundCategory = lookup.get(category);
		
		if (foundCategory == null) throw new InvalidShuttleCategoryException();
		
		return foundCategory;
	}
}
