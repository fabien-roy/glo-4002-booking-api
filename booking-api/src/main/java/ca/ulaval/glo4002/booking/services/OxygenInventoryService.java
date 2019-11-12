package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.passes.Pass;
import ca.ulaval.glo4002.booking.enums.OxygenCategories;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.factories.OxygenFactory;
import ca.ulaval.glo4002.booking.producers.OxygenTankProducer;

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

	void orderForPasses(PassCategories passCategory, List<Pass> passes) {
		OxygenCategories oxygenCategory = factory.buildCategory(passCategory);

		passes.forEach(pass -> {
		    // TODO : PassBundle could return a list of required dates (if null -> all festival dates)
			if (pass.getEventDate() == null) {
			    orderForFullFestival();
			} else {
			    orderForEventDate();
			}
		});
	}

	private void orderForFullFestival() {
		// TODO
	}

	private void orderForEventDate() {
		// TODO
	}
}
