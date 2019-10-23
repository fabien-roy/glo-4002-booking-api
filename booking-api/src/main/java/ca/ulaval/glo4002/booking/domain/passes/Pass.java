package ca.ulaval.glo4002.booking.domain.passes;

import ca.ulaval.glo4002.booking.domain.Money;
import ca.ulaval.glo4002.booking.domain.passes.options.PassOption;

import java.time.LocalDate;

public class Pass {

    private LocalDate eventDate;
    private Money price;
    private PassOption option;
    private PassCategory category;

    // TODO : Add EventDate class (like OrderDate)
    public Pass(LocalDate eventDate, PassOption option, PassCategory category) {
        this.eventDate = eventDate;
        this.option = option;
        this.category = category;
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
}
