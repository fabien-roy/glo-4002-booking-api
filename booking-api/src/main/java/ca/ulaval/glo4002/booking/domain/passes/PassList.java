package ca.ulaval.glo4002.booking.domain.passes;

import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.PriceCalculationStrategy;

import java.math.BigDecimal;
import java.util.List;

public class PassList {

    private List<Pass> passes;
    private PassCategory category;
    private PassOption option;
    private Money price;
    private PriceCalculationStrategy priceCalculationStrategy;

    public PassList(List<Pass> passes, PassCategory category, PassOption option, PriceCalculationStrategy priceCalculationStrategy) {
        this.passes = passes;
        this.category = category;
        this.option = option;
        this.priceCalculationStrategy = priceCalculationStrategy;

        calculatePrice();
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
    private void calculatePrice() {
        Money passPrice = priceCalculationStrategy.calculatePassPrice(passes.size(), getPassPrice());

        BigDecimal numberOfPasses = BigDecimal.valueOf(passes.size());
        BigDecimal totalValue = passPrice.getValue().multiply(numberOfPasses);

        price = new Money(totalValue);
    }

    public int size() {
        return passes.size();
    }
}
