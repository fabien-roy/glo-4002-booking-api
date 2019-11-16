package ca.ulaval.glo4002.booking.oxygen.inventory;

import ca.ulaval.glo4002.booking.artists.BookingArtist;
import ca.ulaval.glo4002.booking.events.EventDate;
import ca.ulaval.glo4002.booking.oxygen.OxygenCategories;
import ca.ulaval.glo4002.booking.oxygen.OxygenCategory;
import ca.ulaval.glo4002.booking.oxygen.OxygenFactory;
import ca.ulaval.glo4002.booking.oxygen.OxygenTankProducer;
import ca.ulaval.glo4002.booking.passes.Pass;
import ca.ulaval.glo4002.booking.passes.PassCategories;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

	public void orderForPasses(PassCategories passCategory, List<Pass> passes, LocalDateTime orderDate) {
		OxygenCategory oxygenCategory = factory.buildCategory(passCategory);

		passes.forEach(pass -> {
		    // TODO : PassBundle could return a list of required dates (if null -> all festival dates)
			if (pass.getEventDate() == null) {
			    orderForFullFestival(oxygenCategory.getCategory(), orderDate.toLocalDate());
			} else {
			    orderForEventDate(oxygenCategory.getCategory(), orderDate.toLocalDate());
			}
		});
	}

	private void orderForFullFestival(OxygenCategories oxygenCategory, LocalDate orderDate) {
		EventDate.getFullFestivalEventDates().forEach(eventDate -> orderForEventDate(oxygenCategory, orderDate));
	}

	private void orderForEventDate(OxygenCategories oxygenCategory, LocalDate orderDate) {
		producer.produceOxygenForOrder(oxygenCategory, orderDate);
		// TODO : Order for activity
	}

    public void orderForArtist(BookingArtist artist, EventDate orderDate) {
		OxygenCategory category = factory.buildCategory(OXYGEN_CATEGORY_FOR_ARTIST);
		Integer amountOfOxygenTanksNeeded = artist.getNumberOfPeople() * OXYGEN_TANKS_NEEDED_PER_ARTIST;

	    producer.produceOxygenByQuantity(category, orderDate.getValue(), amountOfOxygenTanksNeeded);
    }
}
