package ca.ulaval.glo4002.booking.entities.shuttles;

import ca.ulaval.glo4002.booking.entities.User;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

@Entity
public class Passenger {
	
	@Id
	protected Long id;

	private User user;
}
