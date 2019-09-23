package ca.ulaval.glo4002.booking.entities.oxygen;

import ca.ulaval.glo4002.booking.entities.shuttles.Passenger;
import ca.ulaval.glo4002.booking.entities.shuttles.ShuttleCategory;
import ca.ulaval.glo4002.booking.entities.shuttles.ShuttleType;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class OxygenTank {
	
	@Id
	protected Long id;

	private OxygenCategory category;
}
