package ca.ulaval.glo4002.booking.domain.passes;

import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.passes.money.Money;

public class Pass {

    private Number passNumber;
    private EventDate eventDate;
    private Money price;

    public Pass(Number passNumber) {
        this.passNumber = passNumber;
    }

    public Pass(Number passNumber, EventDate eventDate) {
        this.passNumber = passNumber;
        this.eventDate = eventDate;
    }

    public Number getPassNumber() {
        return passNumber;
    }

    public EventDate getEventDate() {
        return eventDate;
    }

    public Money getPrice() {
        return this.price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }
}
