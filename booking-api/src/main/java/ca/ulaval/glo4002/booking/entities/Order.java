package ca.ulaval.glo4002.booking.entities;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Order {
	
	@Id
	protected Long id;

	private Vendor vendor;
	private List<Orderable> orderables;
}
