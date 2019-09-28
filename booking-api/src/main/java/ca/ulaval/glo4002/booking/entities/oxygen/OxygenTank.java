package ca.ulaval.glo4002.booking.entities.oxygen;

import ca.ulaval.glo4002.booking.entities.orders.OrderItem;
import ca.ulaval.glo4002.booking.entities.oxygen.categories.OxygenCategory;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class OxygenTank extends OrderItem {
	
	@Id
	protected Long id;

	private OxygenCategory category;
	private LocalDate producedTime;

	@Override
	public Double getPrice() {
	    return 0.0; // TODO : Oxygen tank price calculation
	}

	public OxygenCategory getOxygenTankCategory() { return category; }
}
