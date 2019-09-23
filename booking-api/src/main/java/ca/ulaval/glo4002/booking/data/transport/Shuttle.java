package ca.ulaval.glo4002.booking.data.transport;

import javax.persistence.Entity;

import org.springframework.data.annotation.Id;

@Entity
public abstract class Shuttle {
	
	@Id
	protected Long id;
	
	protected Integer maxCapacity;
	protected Integer price;
	//TODO: List<ACP>
	
}
