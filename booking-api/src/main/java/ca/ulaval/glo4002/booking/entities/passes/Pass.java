package ca.ulaval.glo4002.booking.entities.passes;

import ca.ulaval.glo4002.booking.entities.Orderable;
import ca.ulaval.glo4002.booking.entities.passes.categories.PassCategory;
import ca.ulaval.glo4002.booking.entities.passes.options.PassOption;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

@Entity
public class Pass implements Orderable {
	
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
