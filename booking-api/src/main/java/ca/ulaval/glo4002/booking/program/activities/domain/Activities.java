package ca.ulaval.glo4002.booking.program.activities.domain;

import ca.ulaval.glo4002.booking.program.rest.exceptions.InvalidProgramException;

import java.util.HashMap;
import java.util.Map;

public enum Activities {

    YOGA("yoga"),
    CARDIO("cardio");

    private String activity;
    private static final Map<String, Activities> lookup = new HashMap<>();

    static {
        for(Activities option : Activities.values()) {
            lookup.put(option.toString(), option);
        }
    }

    Activities(String activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return activity;
    }

    public static Activities get(String activity) {
        Activities foundActivity = lookup.get(activity);

        if (foundActivity == null) throw new InvalidProgramException();

        return foundActivity;
    }
}
