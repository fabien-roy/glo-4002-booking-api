package ca.ulaval.glo4002.booking.domain.passes;

import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.PriceCalculationStrategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PassList {

    private List<Pass> passes;
    private PassCategory category;
    private PassOption option;
    private Money price;
    private PriceCalculationStrategy priceCalculationStrategy;

    // TODO : Only used by tests...
    public PassList() {
        this.passes = new ArrayList<>();
    }

    // TODO : Only used by tests...
    public PassList(List<Pass> passes, PassCategory category, PassOption option) {
        this.passes = passes;
        this.category = category;
        this.option = option;
    }

    public PassList(PassCategory category, PassOption option, PriceCalculationStrategy priceCalculationStrategy) {
        this.category = category;
        this.option = option;
        this.priceCalculationStrategy = priceCalculationStrategy;
    }

    public List<Pass> getPasses() {
        return passes;
    }

    public void setPasses(List<Pass> passes) {
        this.passes = passes;
    }

    public PassCategory getCategory() {
        return category;
    }

    public PassOption getOption() {
        return option;
    }

    public void setOption(PassOption option) {
        this.option = option;
    }

    public Money getPrice() {
        return price;
    }

    public PriceCalculationStrategy getPriceCalculationStrategy() {
        return priceCalculationStrategy;
    }

    private Money getPassPrice() {
        return passes.get(0).getPrice();
    }

    // TODO : Test calculatePrice
    // TODO : When should we calculate order price?
    private void calculatePrice() {
        Money passPrice = priceCalculationStrategy.calculatePassPrice(passes.size(), getPassPrice());

        passes.forEach(pass -> pass.setPrice(passPrice));

        calculateTotalPrice(passPrice);
    }

    private void calculateTotalPrice(Money passPrice) {
        BigDecimal numberOfPasses = BigDecimal.valueOf(passes.size());
        BigDecimal totalValue = passPrice.getValue().multiply(numberOfPasses);

        price = new Money(totalValue);
    }

    public int size() {
        return passes.size();
    }
}
