package ca.ulaval.glo4002.booking.enums;

import ca.ulaval.glo4002.booking.exceptions.InvalidOxygenCategoryException;

import java.util.HashMap;
import java.util.Map;

public enum OxygenTankCategory {

    CATEGORY_A("Grade A"),
    CATEGORY_B("Grade B"),
    CATEGORY_E("Grade E");

    private String category;
    private static final Map<String, OxygenTankCategory> lookup = new HashMap<>();

    static {
        for(OxygenTankCategory category : OxygenTankCategory.values()) {
            lookup.put(category.toString(), category);
        }
    }

    OxygenTankCategory(String category) {
        this.category = category;
    }

    public String toString() {
        return category;
    }

    public static OxygenTankCategory get(String category) {
        OxygenTankCategory foundCategory = lookup.get(category);

        if(foundCategory == null) throw new InvalidOxygenCategoryException("oxygenCategory does not exist");

        return foundCategory;
    }
}
