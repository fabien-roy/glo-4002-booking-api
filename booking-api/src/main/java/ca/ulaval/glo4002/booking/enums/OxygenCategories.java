package ca.ulaval.glo4002.booking.enums;

import ca.ulaval.glo4002.booking.exceptions.oxygen.InvalidOxygenCategoryException;

import java.util.HashMap;
import java.util.Map;

public enum OxygenCategories {

    A("A"),
    B("B"),
    E("E");

    private String category;
    private static final Map<String, OxygenCategories> lookup = new HashMap<>();

    static {
        for(OxygenCategories category : OxygenCategories.values()) {
            lookup.put(category.toString(), category);
        }
    }

    OxygenCategories(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return category;
    }

    public static OxygenCategories get(String category) {
        OxygenCategories foundCategory = lookup.get(category);

        if (foundCategory == null) throw new InvalidOxygenCategoryException(category);

        return foundCategory;
    }
}
