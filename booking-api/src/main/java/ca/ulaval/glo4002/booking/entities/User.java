package ca.ulaval.glo4002.booking.entities;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

@Entity
public class User {
	
	@Id
	protected Long id;
}
