package ca.ulaval.glo4002.booking.domain.passes;

import ca.ulaval.glo4002.booking.domain.Money;

import java.time.LocalDate;

public class Pass {

    private LocalDate eventDate;
    private Money price;

    public Pass() {

    }

    public Pass(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public Pass(LocalDate eventDate, Money price) {
        this.eventDate = eventDate;
        this.price = price;
    }

    public Money getPrice() {
        return this.price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }
}
