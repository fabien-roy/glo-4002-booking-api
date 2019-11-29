package ca.ulaval.glo4002.booking.passes.domain;

import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;

import java.util.HashMap;
import java.util.Map;

public enum PassOptions {

    PACKAGE("package"),
    SINGLE_PASS("singlePass");

    private String option;
    private static final Map<String, PassOptions> lookup = new HashMap<>();

    static {
        for(PassOptions option : PassOptions.values()) {
            lookup.put(option.toString(), option);
        }
    }

    PassOptions(String option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return option;
    }

    public static PassOptions get(String option) {
        PassOptions foundOption = lookup.get(option);

        if (foundOption == null) throw new InvalidFormatException();

        return foundOption;
    }
}
