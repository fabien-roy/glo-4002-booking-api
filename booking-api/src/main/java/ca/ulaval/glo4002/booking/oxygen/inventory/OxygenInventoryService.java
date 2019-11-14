package ca.ulaval.glo4002.booking.oxygen.inventory;

import ca.ulaval.glo4002.booking.events.EventDate;
import ca.ulaval.glo4002.booking.passes.Pass;
import ca.ulaval.glo4002.booking.oxygen.OxygenCategories;
import ca.ulaval.glo4002.booking.passes.PassCategories;
import ca.ulaval.glo4002.booking.oxygen.OxygenFactory;
import ca.ulaval.glo4002.booking.oxygen.OxygenTankProducer;

import javax.inject.Inject;
import java.util.List;

public class OxygenInventoryService {

    private final OxygenFactory factory;
	private final OxygenTankProducer producer;

	@Inject
	public OxygenInventoryService(OxygenFactory factory, OxygenTankProducer producer) {
		this.factory = factory;
		this.producer = producer;
	}

	public void orderForPasses(PassCategories passCategory, List<Pass> passes) {
		OxygenCategories oxygenCategory = factory.buildCategory(passCategory);

		passes.forEach(pass -> {
		    // TODO : PassBundle could return a list of required dates (if null -> all festival dates)
			if (pass.getEventDate() == null) {
			    orderForFullFestival(oxygenCategory);
			} else {
			    orderForEventDate(oxygenCategory, pass.getEventDate());
			}
		});
	}

	private void orderForFullFestival(OxygenCategories oxygenCategory) {
		EventDate.getFullFestivalEventDates().forEach(eventDate -> orderForEventDate(oxygenCategory, eventDate));
	}

	private void orderForEventDate(OxygenCategories oxygenCategory, EventDate eventDate) {
		producer.produceForDay(oxygenCategory, eventDate.getValue());
	}
}
