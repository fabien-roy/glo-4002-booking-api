package ca.ulaval.glo4002.booking.entities.passes;

import ca.ulaval.glo4002.booking.entities.orders.OrderItem;
import ca.ulaval.glo4002.booking.entities.passes.categories.PassCategory;
import ca.ulaval.glo4002.booking.entities.passes.options.PassOption;
import ca.ulaval.glo4002.booking.parsers.ParsableEntity;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Pass extends OrderItem implements ParsableEntity {

	@Id
	protected Long id;

    private PassCategory category;
    private PassOption option;
    private LocalDate eventDate;

    public Pass(PassCategory passCategory, PassOption passOption, Long id, LocalDate eventDate) {
        this.id = id;
        this.category = passCategory;
        this.option = passOption;
        this.eventDate = eventDate;
    }

    public PassCategory getCategory() {
        return category;
    }

    public PassOption getOption() {
        return option;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

	@Override
	public Double getPrice() {
		return this.getCategory().getPricePerOption().get(this.option);
	}
}
