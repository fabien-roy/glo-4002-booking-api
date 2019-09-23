package ca.ulaval.glo4002.booking.entities.oxygen;

import ca.ulaval.glo4002.booking.entities.Orderable;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

@Entity
public class OxygenTank implements Orderable {
	
	@Id
	protected Long id;

	private OxygenCategory category;

	@Override
	public Double getPrice() {
	    return 0.0; // TODO : Oxygen tank price calculation
	}
}
