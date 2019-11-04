package ca.ulaval.glo4002.booking.enums;

import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;

import java.util.HashMap;
import java.util.Map;

public enum PassCategories {

    SUPERNOVA("supernova"),
    SUPERGIANT("supergiant"),
    NEBULA("nebula");

    private String category;
    private static final Map<String, PassCategories> lookup = new HashMap<>();

    static {
        for(PassCategories category : PassCategories.values()) {
            lookup.put(category.toString(), category);
        }
    }

    PassCategories(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return category;
    }

    public static PassCategories get(String category) {
        PassCategories foundCategory = lookup.get(category);

        if (foundCategory == null) throw new InvalidFormatException();

        return foundCategory;
    }
}
