package ca.ulaval.glo4002.booking.entities.orders;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

@Entity
public class Order {
	
	@Id
	protected Long id;

}
