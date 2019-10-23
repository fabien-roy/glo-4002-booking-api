package ca.ulaval.glo4002.booking.domain.passes;

import ca.ulaval.glo4002.booking.domain.passes.money.Money;
import ca.ulaval.glo4002.booking.domain.passes.options.PassOption;

public class Pass {

    private EventDate eventDate;
    private Money price;
    private PassCategory category;
    private PassOption option;

    public Pass(EventDate eventDate, PassCategory category, PassOption option) {
        this.eventDate = eventDate;
        this.category = category;
        this.option = option;
    }

    public Money getPrice() {
        return this.price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public Money calculatePrice(int passQuantity) {
        return option.calculatePrice(passQuantity);
    }

    public PassCategory getCategory() {
        return category;
    }

    public PassOption getOption() {
        return option;
    }
}
