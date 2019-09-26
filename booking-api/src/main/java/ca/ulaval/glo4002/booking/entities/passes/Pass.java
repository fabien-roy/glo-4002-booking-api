package ca.ulaval.glo4002.booking.entities.passes;

import ca.ulaval.glo4002.booking.entities.orders.OrderItem;
import ca.ulaval.glo4002.booking.entities.passes.categories.PassCategory;
import ca.ulaval.glo4002.booking.entities.passes.options.PassOption;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

@Entity
public class Pass extends OrderItem {
	
	@Id
	protected Long id;

	private Double price;
	private PassCategory category;
    private PassOption option;
	// TODO : private List<?> eventDates;

	@Override
	public Double getPrice() {
		return price;
	}
}
