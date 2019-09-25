package ca.ulaval.glo4002.booking.entities.passes;

import ca.ulaval.glo4002.booking.entities.Orderable;
import ca.ulaval.glo4002.booking.entities.passes.categories.PassCategory;
import ca.ulaval.glo4002.booking.entities.passes.options.PassOption;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import java.util.concurrent.atomic.AtomicLong;

@Entity
public class Pass implements Orderable {
	
	@Id
	protected Long id;

    public PassCategory getCategory() {
        return category;
    }

    public PassOption getOption() {
        return option;
    }

    private Double price;
	private PassCategory category;
    private PassOption option;
	// TODO : private List<?> eventDates;

	@Override
	public Double getPrice() {
		return price;
	}

	public Pass(PassCategory passCategory, PassOption passOption, Long id){
		this.id = id;
		this.category = passCategory;
		this.option = passOption;
	}
    public Pass(PassCategory passCategory, PassOption passOption, Long id, Double price){
        this.id = id;
        this.category = passCategory;
        this.option = passOption;
        this.price = price;
    }

}
