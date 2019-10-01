package ca.ulaval.glo4002.booking.domainObjects.passes;

import ca.ulaval.glo4002.booking.domainObjects.orders.OrderItem;
import ca.ulaval.glo4002.booking.domainObjects.passes.categories.PassCategory;
import ca.ulaval.glo4002.booking.domainObjects.passes.options.PassOption;

import java.time.LocalDate;

public class Pass extends OrderItem {

	private Long id;
    private PassCategory category;
    private PassOption option;
    private LocalDate eventDate;

    public Pass(Long id, PassCategory category, PassOption option) {
        this.id = id;
        this.category = category;
        this.option = option;
    }

    public Pass(Long id, PassCategory category, PassOption option, LocalDate eventDate) {
        this.id = id;
        this.category = category;
        this.option = option;
        this.eventDate = eventDate;
    }

    public Long getId() {
        return id;
    }

    public PassCategory getCategory() {
        return category;
    }

    public PassOption getOption() {
        return option;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

	@Override
	public Double getPrice() {
		return this.category.getPricePerOption().get(option);
	}
}
