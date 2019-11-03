package ca.ulaval.glo4002.booking.domain.passes;

import ca.ulaval.glo4002.booking.domain.money.Money;

import java.math.BigDecimal;
import java.util.List;

public class PassBundle {

    private List<Pass> passes;
    private PassCategory category;
    private PassOption option;
    private Money price;

    public PassBundle(List<Pass> passes, PassCategory category, PassOption option) {
        this.passes = passes;
        this.category = category;
        this.option = option;
        this.price = passes.get(0).getPrice().multiply(new BigDecimal(passes.size()));
    }

    public List<Pass> getPasses() {
        return passes;
    }

    public PassCategory getCategory() {
        return category;
    }

    public PassOption getOption() {
        return option;
    }

    public Money getPrice() {
        return price;
    }
}
