package ca.ulaval.glo4002.booking.program.artists.domain;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;

public enum ArtistOrderings {

    LOW_COSTS("lowCosts"),
    MOST_POPULAR("mostPopular");

    private String ordering;
    private static final Map<String, ArtistOrderings> lookup = new HashMap<>();

    static {
        for(ArtistOrderings option : ArtistOrderings.values()) {
            lookup.put(option.toString(), option);
        }
    }

    ArtistOrderings(String ordering) {
        this.ordering = ordering;
    }

    @Override
    public String toString() {
        return ordering;
    }

    public static ArtistOrderings get(String ordering) {
        ArtistOrderings foundOrdering = lookup.get(ordering);

        if (foundOrdering == null) throw new InvalidFormatException();

        return foundOrdering;
    }
}
