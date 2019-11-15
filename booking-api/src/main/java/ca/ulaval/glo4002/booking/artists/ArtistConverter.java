package ca.ulaval.glo4002.booking.artists;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.events.EventDate;
import ca.ulaval.glo4002.booking.profits.Money;

public class ArtistConverter {

	private final ArtistRepository artistRepository;

	public ArtistConverter(ArtistRepository artistRepository) {
		this.artistRepository = artistRepository;
	}

	public void convert() {
		List<ExternalArtist> externalArtists = ArtistClient.getArtists();
		List<BookingArtist> bookingArtists = new ArrayList<>();
		for (ExternalArtist externalArtist : externalArtists) {
			Money cost = new Money(new BigDecimal(externalArtist.getPrice()));

			List<Availability> convertedAvailabilities = convertAvailabilities(externalArtist.getAvailabilities());
			BookingArtist bookingArtist = new BookingArtist(externalArtist.getId(), externalArtist.getName(), cost,
					externalArtist.getNbPeople(), externalArtist.getMusicStyle(), externalArtist.getPopularityRank(),
					convertedAvailabilities);
			bookingArtists.add(bookingArtist);
		}

		artistRepository.saveAll(bookingArtists);
	}

	private List<Availability> convertAvailabilities(List<ExternalArtistAvailability> externalAvailabilities) {
		List<Availability> availabilities = new ArrayList<>();
		for (ExternalArtistAvailability externalArtistAvailability : externalAvailabilities) {
			String textDate = externalArtistAvailability.getDate();
			LocalDate availabilityDate = LocalDate.parse(textDate);
			if (availabilityDateIsDuringFestival(availabilityDate)) {
				EventDate eventAvailabilityDate = new EventDate(availabilityDate);
				Availability availability = new Availability(eventAvailabilityDate);
				availabilities.add(availability);
			}
		}
		return availabilities;
	}

	private boolean availabilityDateIsDuringFestival(LocalDate date) {
		LocalDate startMinusOneDay = EventDate.START_DATE.minusDays(1);
		LocalDate endPlusOneDay = EventDate.END_DATE.plusDays(1);
		return date.isAfter(startMinusOneDay) && date.isBefore(endPlusOneDay);
	}

}
