package ca.ulaval.glo4002.booking.domain.passes;

import ca.ulaval.glo4002.booking.domain.EventDate;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.domain.EventDate;
import ca.ulaval.glo4002.booking.domain.Number;

public class Pass {

    private Number passNumber;
    private EventDate eventDate;
    private Money price;

    public Pass(Number passNumber, Money price) {
        this.passNumber = passNumber;
        this.price = price;
    }

    public Pass(Number passNumber, EventDate eventDate, Money price) {
        this.passNumber = passNumber;
        this.eventDate = eventDate;
        this.price = price;
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
}
