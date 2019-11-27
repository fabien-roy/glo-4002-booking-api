package ca.ulaval.glo4002.booking.artists;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.configuration.Configuration;
import ca.ulaval.glo4002.booking.events.EventDate;
import ca.ulaval.glo4002.booking.profits.Money;

import javax.inject.Inject;

public class ArtistConverter {

	private final Configuration configuration;
	private final ArtistRepository artistRepository;

	@Inject
	public ArtistConverter(Configuration configuration, ArtistRepository artistRepository) {
		this.configuration = configuration;
		this.artistRepository = artistRepository;
	}

	public void convert() {
		List<ExternalArtist> externalArtists = ArtistClient.getArtists();
		List<BookingArtist> bookingArtists = new ArrayList<>();

		for (ExternalArtist externalArtist : externalArtists) {
			Money cost = new Money(new BigDecimal(externalArtist.getPrice()));

			List<Availability> convertedAvailabilities = convertAvailabilities(externalArtist.getAvailabilities());

			BookingArtist bookingArtist = new BookingArtist(
					externalArtist.getId(),
					externalArtist.getName(),
					cost,
					externalArtist.getNbPeople(),
					externalArtist.getMusicStyle(),
					externalArtist.getPopularityRank(),
					convertedAvailabilities
			);

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

	// TODO : Use EventDate in availabilityDateIsDuringFestival
	private boolean availabilityDateIsDuringFestival(LocalDate date) {
		LocalDate startMinusOneDay = configuration.getStartEventDate().minusDays(1).getValue();
		LocalDate endPlusOneDay = configuration.getEndEventDate().plusDays(1).getValue();

		return date.isAfter(startMinusOneDay) && date.isBefore(endPlusOneDay);
	}
}
