package ca.ulaval.glo4002.booking.enums;

import ca.ulaval.glo4002.booking.exceptions.InvalidOxygenCategoryException;

import java.util.HashMap;
import java.util.Map;

public enum OxygenCategory {

    A("A"),
    B("B"),
    E("E");

    private String category;
    private static final Map<String, OxygenCategory> lookup = new HashMap<>();

    static {
        for(OxygenCategory category : OxygenCategory.values()) {
            lookup.put(category.toString(), category);
        }
    }

    OxygenCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return category;
    }

    public static OxygenCategory get(String category) {
        OxygenCategory foundCategory = lookup.get(category);

        if (foundCategory == null) throw new InvalidOxygenCategoryException();

        return foundCategory;
    }
}
