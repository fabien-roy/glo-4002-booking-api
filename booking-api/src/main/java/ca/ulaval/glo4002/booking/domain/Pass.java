package ca.ulaval.glo4002.booking.domain;

import java.math.BigDecimal;
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

    public void setPrice(BigDecimal price) {
        this.price = new Money(price);
    }
}
