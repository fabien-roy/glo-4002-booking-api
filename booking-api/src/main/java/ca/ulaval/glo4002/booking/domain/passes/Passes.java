package ca.ulaval.glo4002.booking.domain.passes;

import ca.ulaval.glo4002.booking.domain.passes.options.PassOption;

public class Passes {

    private PassCategory category;
    private PassOption option;

    public Passes(PassCategory category, PassOption option) {
        this.category = category;
        this.option = option;
    }
}
