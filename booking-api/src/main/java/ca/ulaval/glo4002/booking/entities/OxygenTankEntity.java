package ca.ulaval.glo4002.booking.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "OxygenTank")
public class OxygenTankEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public Long catId;
	public LocalDate requestDate;

	public OxygenTankEntity() {

	}

	public OxygenTankEntity(Long catId, LocalDate requestDate) {
		this.catId = catId;
		this.requestDate = requestDate;
	}

	public OxygenTankEntity(Long id, Long catId, LocalDate requestDate) {
		this.id = id;
		this.catId = catId;
		this.requestDate = requestDate;
	}
}
