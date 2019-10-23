package ca.ulaval.glo4002.booking.domain.passes;

import ca.ulaval.glo4002.booking.domain.Money;
import ca.ulaval.glo4002.booking.domain.passes.options.PassOption;

import java.time.LocalDate;

public class Pass {

    private LocalDate eventDate;
    private Money price;
    private PassCategory category;
    private PassOption option;

    // TODO : Add EventDate class (like OrderDate)
    public Pass(LocalDate eventDate, PassCategory category, PassOption option) {
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
