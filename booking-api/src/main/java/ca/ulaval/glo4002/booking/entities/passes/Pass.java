package ca.ulaval.glo4002.booking.entities.passes;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

@Entity
public class Pass {
	
	@Id
	protected Long id;

	private Double price;
	private PassCategory category;
    private PassOption option;
	// TODO : private List<?> eventDates;
}
