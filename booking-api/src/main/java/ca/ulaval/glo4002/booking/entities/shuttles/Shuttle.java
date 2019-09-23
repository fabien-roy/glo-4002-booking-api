package ca.ulaval.glo4002.booking.entities.shuttles;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Shuttle {
	
	@Id
	protected Long id;

	private ShuttleCategory category;
    private ShuttleType type;
	private List<Passenger> passengers;
	// TODO : private <?> date;
}
