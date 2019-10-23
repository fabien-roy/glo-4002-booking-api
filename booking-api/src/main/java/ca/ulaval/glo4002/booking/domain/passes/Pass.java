package ca.ulaval.glo4002.booking.domain.passes;

import ca.ulaval.glo4002.booking.domain.passes.money.Money;

public class Pass {

    private EventDate eventDate;
    private Money price;

    public Pass() {

    }

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
