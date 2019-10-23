package ca.ulaval.glo4002.booking.domain.passes;

import ca.ulaval.glo4002.booking.domain.passes.money.Money;
import ca.ulaval.glo4002.booking.domain.passes.options.PassOption;

public class Pass {

    private EventDate eventDate;
    private Money price;

    public Pass(EventDate eventDate) {
        this.eventDate = eventDate;
    }

    public Money getPrice() {
        return this.price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }
}
