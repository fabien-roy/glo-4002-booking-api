package ca.ulaval.glo4002.booking.converters;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.domain.artist.Availability;
import ca.ulaval.glo4002.booking.domain.artist.BookingArtist;
import ca.ulaval.glo4002.booking.domain.artist.ExternalArtist;
import ca.ulaval.glo4002.booking.domain.artist.ExternalArtistAvailability;
import ca.ulaval.glo4002.booking.domain.events.EventDate;
import ca.ulaval.glo4002.booking.domain.money.Money;

public class ArtistConverter {
	
	public List<BookingArtist> convert(List<ExternalArtist> externalArtists) {
		List<BookingArtist> bookingArtists = new ArrayList<>();
		for (ExternalArtist externalArtist : externalArtists) {
			Money cost = new Money(new BigDecimal(externalArtist.getPrice()));
			
			
			bookingArtists.add(new BookingArtist(externalArtist.getName(),
					cost,
					externalArtist.getNbPeople(),
					externalArtist.getMusicStyle(),
					externalArtist.getPopularityRank(),
					convertAvailabilities(externalArtist.getAvailabilities())));
			
		}
		
		return bookingArtists;
	}
	
	private List<Availability> convertAvailabilities(List<ExternalArtistAvailability> externalAvailabilities) {
		List<Availability> availabilities = new ArrayList<>();
		for(ExternalArtistAvailability externalArtistAvailability : externalAvailabilities) {
			LocalDate availabilityDate = LocalDate.parse(externalArtistAvailability.getDate());
			if(availabilityDateIsDuringFestival(availabilityDate)) {
				availabilities.add(new Availability
						(new EventDate(availabilityDate)));
			}
		}
		return availabilities;
	}
	
	private boolean availabilityDateIsDuringFestival(LocalDate date) {
		return date.isAfter(EventDate.START_DATE) && date.isBefore(EventDate.END_DATE);
	}

}
