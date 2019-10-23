package ca.ulaval.glo4002.booking.domain.passes;

import ca.ulaval.glo4002.booking.domain.Id;
import ca.ulaval.glo4002.booking.domain.passes.money.Money;

public class Pass {

    private PassNumber passNumber;
    private EventDate eventDate;
    private Money price;

    public Pass() {
        this.passNumber = new PassNumber();
    }

    // TODO : Only used by tests...
    public Pass(PassNumber passNumber) {
        this.passNumber = passNumber;
    }

    public Pass(EventDate eventDate) {
        this.passNumber = new PassNumber();
        this.eventDate = eventDate;
    }

    public Id getId() {
        return passNumber.getId();
    }

    public void setId(Id id) {
        passNumber.setId(id);
    }

    public Money getPrice() {
        return this.price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }
}
