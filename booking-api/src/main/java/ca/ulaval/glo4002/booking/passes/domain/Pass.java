package ca.ulaval.glo4002.booking.passes.domain;

import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;

public class Pass {

    private PassNumber passNumber;
    private Money price;
    private EventDate eventDate;

    public Pass(PassNumber passNumber, Money price) {
        this.passNumber = passNumber;
        this.price = price;
    }

    public Pass(PassNumber passNumber, Money price, EventDate eventDate) {
        this.passNumber = passNumber;
        this.price = price;
        this.eventDate = eventDate;
    }

    public PassNumber getPassNumber() {
        return passNumber;
    }

    public Money getPrice() {
        return this.price;
    }

    public EventDate getEventDate() {
        return eventDate;
    }
}
