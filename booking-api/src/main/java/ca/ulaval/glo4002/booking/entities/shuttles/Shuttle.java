package ca.ulaval.glo4002.booking.entities.shuttles;

import ca.ulaval.glo4002.booking.entities.Orderable;
import ca.ulaval.glo4002.booking.entities.shuttles.categories.ShuttleCategory;
import ca.ulaval.glo4002.booking.entities.shuttles.types.ShuttleType;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Shuttle implements Orderable {
	
	@Id
	protected Long id;

	private Double price;
	private ShuttleCategory category;
    private ShuttleType type;
	private List<Passenger> passengers;
    // TODO : private <?> date;

    @Override
    public Double getPrice() {
        return price;
    }
}
