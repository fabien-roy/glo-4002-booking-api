package ca.ulaval.glo4002.booking.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity(name = "OxygenTanks")
public class OxygenTankEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public Long categoryId;
	public LocalDate requestDate;

	public OxygenTankEntity() {

	}

	public OxygenTankEntity(Long categoryId, LocalDate requestDate) {
		this.categoryId = categoryId;
		this.requestDate = requestDate;
	}

	public OxygenTankEntity(Long id, Long categoryId, LocalDate requestDate) {
		this.id = id;
		this.categoryId = categoryId;
		this.requestDate = requestDate;
	}
}
