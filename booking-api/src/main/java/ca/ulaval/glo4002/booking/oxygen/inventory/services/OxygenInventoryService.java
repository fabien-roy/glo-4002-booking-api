package ca.ulaval.glo4002.booking.oxygen.inventory.services;

import ca.ulaval.glo4002.booking.orders.domain.OrderDate;
import ca.ulaval.glo4002.booking.oxygen.domain.OxygenCategories;
import ca.ulaval.glo4002.booking.oxygen.domain.OxygenFactory;
import ca.ulaval.glo4002.booking.oxygen.domain.OxygenProduction;
import ca.ulaval.glo4002.booking.oxygen.domain.OxygenTankProducer;
import ca.ulaval.glo4002.booking.passes.domain.PassRefactored;
import ca.ulaval.glo4002.booking.program.artists.domain.Artist;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

public class OxygenInventoryService {

	static final OxygenCategories OXYGEN_CATEGORY_FOR_ARTIST = OxygenCategories.E;
	static final Integer OXYGEN_TANKS_NEEDED_PER_ARTIST = 6;

	private final OxygenFactory factory;
	private final OxygenTankProducer producer;

	@Inject
	public OxygenInventoryService(OxygenFactory factory, OxygenTankProducer producer) {
		this.factory = factory;
		this.producer = producer;
	}

	public void orderForPasses(List<PassRefactored> passes, OrderDate orderDate) {
		passes.forEach(pass -> {
			OxygenCategories oxygenCategory = factory.createCategory(pass.getCategory()); // TODO : This should be a mapper

			pass.getEventDates().forEach(eventDate -> orderForEventDate(oxygenCategory, orderDate.toLocalDate()));
		});
	}

	private void orderForEventDate(OxygenCategories oxygenCategory, LocalDate orderDate) {
		producer.produceOxygenForOrder(oxygenCategory, orderDate);
	}

    public void orderForArtist(Artist artist, EventDate orderDate) {
		Integer amountOfOxygenTanksNeeded = artist.getNumberOfPeople() * OXYGEN_TANKS_NEEDED_PER_ARTIST;

	    producer.produceOxygenByQuantity(OXYGEN_CATEGORY_FOR_ARTIST, orderDate.getValue(), amountOfOxygenTanksNeeded);
    }
}
