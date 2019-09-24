package ca.ulaval.glo4002.booking.entities.oxygen;

import ca.ulaval.glo4002.booking.entities.Orderable;
import ca.ulaval.glo4002.booking.entities.oxygen.categories.OxygenCategory;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class OxygenTank implements Orderable {
	
	@Id
	protected Long id;

	private OxygenCategory category;
	private LocalDate requestedTime;
	private LocalDate producedTime;

	@Override
	public Double getPrice() {
	    return 0.0; // TODO : Oxygen tank price calculation
	}

	public OxygenCategory getOxygenTankCategory() { return category; }
}
