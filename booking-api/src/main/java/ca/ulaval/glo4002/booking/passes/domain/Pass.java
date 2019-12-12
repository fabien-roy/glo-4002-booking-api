package ca.ulaval.glo4002.booking.passes.domain;

import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;

public class Pass {

    private Long passNumber;
    private Money price;
    private EventDate eventDate;

    public Pass(Long passNumber, Money price) {
        this.passNumber = passNumber;
        this.price = price;
    }

    public Pass(Long passNumber, Money price, EventDate eventDate) {
        this.passNumber = passNumber;
        this.price = price;
        this.eventDate = eventDate;
    }

    public Long getPassNumber() {
        return passNumber;
    }

    public Money getPrice() {
        return this.price;
    }

    public EventDate getEventDate() {
        return eventDate;
    }
}
