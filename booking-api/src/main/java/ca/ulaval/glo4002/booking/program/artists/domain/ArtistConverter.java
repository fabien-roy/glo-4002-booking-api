package ca.ulaval.glo4002.booking.program.artists.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.program.artists.infrastructure.ArtistClient;
import ca.ulaval.glo4002.booking.program.artists.infrastructure.ArtistRepository;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.profits.domain.Money;

import javax.inject.Inject;

public class ArtistConverter {

	private final FestivalConfiguration festivalConfiguration;
	private final ArtistRepository artistRepository;

	@Inject
	public ArtistConverter(FestivalConfiguration festivalConfiguration, ArtistRepository artistRepository) {
		this.festivalConfiguration = festivalConfiguration;
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
			LocalDate parsedAvailabilityDate = LocalDate.parse(externalArtistAvailability.getDate());
			EventDate availabilityDate = new EventDate(parsedAvailabilityDate);

			if (isAvailabilityDateDuringFestival(availabilityDate)) {
				Availability availability = new Availability(availabilityDate);

				availabilities.add(availability);
			}
		}

		return availabilities;
	}

	// TODO : Test ArtistConverter.isAvailabilityDateDuringFestival
	private boolean isAvailabilityDateDuringFestival(EventDate date) {
		EventDate startDate = festivalConfiguration.getStartEventDate();
		EventDate endDate = festivalConfiguration.getEndEventDate();

		return date.isBetweenOrEquals(startDate, endDate);
	}
}
