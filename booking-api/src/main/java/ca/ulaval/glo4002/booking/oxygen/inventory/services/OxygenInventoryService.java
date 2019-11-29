package ca.ulaval.glo4002.booking.oxygen.inventory.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import ca.ulaval.glo4002.booking.festival.domain.Festival;
import ca.ulaval.glo4002.booking.program.artists.BookingArtist;
import ca.ulaval.glo4002.booking.program.events.EventDate;
import ca.ulaval.glo4002.booking.oxygen.domain.OxygenCategories;
import ca.ulaval.glo4002.booking.oxygen.domain.OxygenCategory;
import ca.ulaval.glo4002.booking.oxygen.domain.OxygenFactory;
import ca.ulaval.glo4002.booking.oxygen.domain.OxygenTankProducer;
import ca.ulaval.glo4002.booking.passes.domain.Pass;
import ca.ulaval.glo4002.booking.passes.domain.PassCategories;

public class OxygenInventoryService {

	static final OxygenCategories OXYGEN_CATEGORY_FOR_ARTIST = OxygenCategories.E;
	static final Integer OXYGEN_TANKS_NEEDED_PER_ARTIST = 6;

	private final Festival festival;
	private final OxygenFactory factory;
	private final OxygenTankProducer producer;

	@Inject
	public OxygenInventoryService(Festival festival, OxygenFactory factory, OxygenTankProducer producer) {
		this.festival = festival;
		this.factory = factory;
		this.producer = producer;
	}

	public void orderForPasses(PassCategories passCategory, List<Pass> passes, LocalDateTime orderDate) {
		OxygenCategory oxygenCategory = factory.buildCategory(passCategory);

		passes.forEach(pass -> {
			if (pass.getEventDate() == null) {
				orderForFullFestival(oxygenCategory.getCategory(), orderDate.toLocalDate());
			} else {
				orderForEventDate(oxygenCategory.getCategory(), orderDate.toLocalDate());
			}
		});
	}

	private void orderForFullFestival(OxygenCategories oxygenCategory, LocalDate orderDate) {
		festival.getAllEventDates().forEach(eventDate -> orderForEventDate(oxygenCategory, orderDate));
	}

	private void orderForEventDate(OxygenCategories oxygenCategory, LocalDate orderDate) {
		producer.produceOxygenForOrder(oxygenCategory, orderDate);
	}

    public void orderForArtist(BookingArtist artist, EventDate orderDate) {
		OxygenCategory category = factory.buildCategory(OXYGEN_CATEGORY_FOR_ARTIST);
		Integer amountOfOxygenTanksNeeded = artist.getNumberOfPeople() * OXYGEN_TANKS_NEEDED_PER_ARTIST;

	    producer.produceOxygenByQuantity(category, orderDate.getValue(), amountOfOxygenTanksNeeded);
    }
}
