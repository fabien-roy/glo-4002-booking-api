package ca.ulaval.glo4002.booking.domain.passes;

import ca.ulaval.glo4002.booking.domain.passes.money.Money;
import ca.ulaval.glo4002.booking.domain.passes.options.PassOption;

import java.math.BigDecimal;
import java.util.List;

public class PassList {

    private List<Pass> passes;
    private PassCategory category;
    private PassOption option;
    private Money price;

    public PassList(List<Pass> passes, PassCategory category, PassOption option) {
        this.passes = passes;
        this.category = category;
        this.option = option;
    }

    // TODO : Test calculatePrice
    // TODO : When should we calculate order price?
    private void calculatePrice() {
        Money passPrice = option.calculatePrice(passes.size());

        passes.forEach(pass -> pass.setPrice(passPrice));

        calculateTotalPrice(passPrice);
    }

    private void calculateTotalPrice(Money passPrice) {
        BigDecimal numberOfPasses = BigDecimal.valueOf(passes.size());
        BigDecimal totalValue = passPrice.getValue().multiply(numberOfPasses);

        price = new Money(totalValue);
    }

    public Money getPrice() {
        return price;
    }

    public int size() {
        return passes.size();
    }
}
