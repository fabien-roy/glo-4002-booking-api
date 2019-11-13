package ca.ulaval.glo4002.booking.domain.passes;

import ca.ulaval.glo4002.booking.domain.Profit;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.PassOptions;

import java.math.BigDecimal;
import java.util.List;

public class PassBundle {

    private List<Pass> passes;
    private PassCategory category;
    private PassOptions option;
    private Money price;

    public PassBundle(List<Pass> passes, PassCategory category, PassOptions option) {
        this.passes = passes;
        this.category = category;
        this.option = option;
        this.price = passes.get(0).getPrice().multiply(new BigDecimal(passes.size()));
    }

    public List<Pass> getPasses() {
        return passes;
    }

    public PassCategories getCategory() {
        return category.getCategory();
    }

    public PassOptions getOption() {
        return option;
    }

    public Money getPrice() {
        return price;
    }

    public Money getPricePerOption(PassOptions option) {
        return category.getPricePerOption(option);
    }
    
    public void upateProfit(Profit profit) {
    	profit.addRevenue(price);
    }
}
