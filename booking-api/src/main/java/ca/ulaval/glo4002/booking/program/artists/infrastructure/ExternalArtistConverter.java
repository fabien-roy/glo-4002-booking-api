package ca.ulaval.glo4002.booking.program.artists.infrastructure;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.program.artists.domain.Availability;
import ca.ulaval.glo4002.booking.program.artists.domain.Artist;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExternalArtistConverter {

	private final FestivalConfiguration festivalConfiguration;

	@Inject
	public ExternalArtistConverter(FestivalConfiguration festivalConfiguration) {
		this.festivalConfiguration = festivalConfiguration;
	}

	public List<Artist> convert(List<ExternalArtist> externalArtists) {
		List<Artist> artists = new ArrayList<>();

		for (ExternalArtist externalArtist : externalArtists) {
			Money cost = new Money(new BigDecimal(externalArtist.getPrice()));

			List<Availability> convertedAvailabilities = convertAvailabilities(externalArtist.getAvailabilities());

			Artist artist = new Artist(
					externalArtist.getId(),
					externalArtist.getName(),
					cost,
					externalArtist.getNbPeople(),
					externalArtist.getMusicStyle(),
					externalArtist.getPopularityRank(),
					convertedAvailabilities
			);

			artists.add(artist);
		}

		return artists;
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
