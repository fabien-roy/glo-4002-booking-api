package ca.ulaval.glo4002.booking.domainObjects.shuttles;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

@Entity
public class Passenger {
	
	@Id
	protected Long id;
}
