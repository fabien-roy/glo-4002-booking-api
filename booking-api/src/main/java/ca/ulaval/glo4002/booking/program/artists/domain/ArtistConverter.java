package ca.ulaval.glo4002.booking.program.artists.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.program.artists.infrastructure.ExternalArtistClient;
import ca.ulaval.glo4002.booking.program.artists.infrastructure.ArtistRepository;
import ca.ulaval.glo4002.booking.program.artists.infrastructure.ExternalArtist;
import ca.ulaval.glo4002.booking.program.artists.infrastructure.ExternalArtistAvailability;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.profits.domain.Money;

import javax.inject.Inject;

// TODO : Remove outdated ArtistConverter
public class ArtistConverter {

	private final FestivalConfiguration festivalConfiguration;
	private final ArtistRepository artistRepository;

	@Inject
	public ArtistConverter(FestivalConfiguration festivalConfiguration, ArtistRepository artistRepository) {
		this.festivalConfiguration = festivalConfiguration;
		this.artistRepository = artistRepository;
	}

	public void convert() {
		List<ExternalArtist> externalArtists = new ExternalArtistClient().getArtists();
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

		// artistRepository.saveAll(artists);
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
