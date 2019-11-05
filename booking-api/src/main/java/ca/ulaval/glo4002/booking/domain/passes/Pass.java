package ca.ulaval.glo4002.booking.domain.passes;

import ca.ulaval.glo4002.booking.domain.EventDate;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.domain.Number;

public class Pass {

    private Number passNumber;
    private Money price;
    private EventDate eventDate;

    public Pass(Number passNumber, Money price) {
        this.passNumber = passNumber;
        this.price = price;
    }

    public Pass(Number passNumber, Money price, EventDate eventDate) {
        this.passNumber = passNumber;
        this.price = price;
        this.eventDate = eventDate;
    }

    public Number getPassNumber() {
        return passNumber;
    }

    public Money getPrice() {
        return this.price;
    }

    public EventDate getEventDate() {
        return eventDate;
    }
}
