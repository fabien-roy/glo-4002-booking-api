package ca.ulaval.glo4002.booking.oxygen.inventory.services;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.orders.domain.OrderDate;
import ca.ulaval.glo4002.booking.program.artists.domain.Artist;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.oxygen.domain.OxygenCategories;
import ca.ulaval.glo4002.booking.oxygen.domain.OxygenProduction;
import ca.ulaval.glo4002.booking.oxygen.domain.OxygenFactory;
import ca.ulaval.glo4002.booking.oxygen.domain.OxygenTankProducer;
import ca.ulaval.glo4002.booking.passes.domain.Pass;
import ca.ulaval.glo4002.booking.passes.domain.PassCategories;

public class OxygenInventoryService {

	static final OxygenCategories OXYGEN_CATEGORY_FOR_ARTIST = OxygenCategories.E;
	static final Integer OXYGEN_TANKS_NEEDED_PER_ARTIST = 6;

	private final FestivalConfiguration festivalConfiguration;
	private final OxygenFactory factory;
	private final OxygenTankProducer producer;

	@Inject
	public OxygenInventoryService(FestivalConfiguration festivalConfiguration, OxygenFactory factory, OxygenTankProducer producer) {
		this.festivalConfiguration = festivalConfiguration;
		this.factory = factory;
		this.producer = producer;
	}

	public void orderForPasses(PassCategories passCategory, List<Pass> passes, OrderDate orderDate) {
		OxygenProduction oxygenProduction = factory.createCategory(passCategory);

		passes.forEach(pass -> {
			if (pass.getEventDate() == null) {
				orderForFullFestival(oxygenProduction.getCategory(), orderDate.toLocalDate());
			} else {
				orderForEventDate(oxygenProduction.getCategory(), orderDate.toLocalDate());
			}
		});
	}

	private void orderForFullFestival(OxygenCategories oxygenCategory, LocalDate orderDate) {
		festivalConfiguration.getAllEventDates().forEach(eventDate -> orderForEventDate(oxygenCategory, orderDate));
	}

	private void orderForEventDate(OxygenCategories oxygenCategory, LocalDate orderDate) {
		producer.produceOxygenForOrder(oxygenCategory, orderDate);
	}

    public void orderForArtist(Artist artist, EventDate orderDate) {
		OxygenProduction category = factory.createCategory(OXYGEN_CATEGORY_FOR_ARTIST);
		Integer amountOfOxygenTanksNeeded = artist.getNumberOfPeople() * OXYGEN_TANKS_NEEDED_PER_ARTIST;

	    producer.produceOxygenByQuantity(category, orderDate.getValue(), amountOfOxygenTanksNeeded);
    }
}
