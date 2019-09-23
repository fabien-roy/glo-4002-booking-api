package ca.ulaval.glo4002.booking.entities.oxygen;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

@Entity
public class OxygenTank {
	
	@Id
	protected Long id;

	private Double price;
	private OxygenCategory category;
}
