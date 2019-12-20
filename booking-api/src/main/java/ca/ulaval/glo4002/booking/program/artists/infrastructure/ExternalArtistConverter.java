package ca.ulaval.glo4002.booking.program.artists.infrastructure;

import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.program.artists.domain.Artist;
import ca.ulaval.glo4002.booking.program.artists.domain.ArtistId;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ExternalArtistConverter {

	public List<Artist> convert(List<ExternalArtist> externalArtists) {
		List<Artist> artists = new ArrayList<>();

		for (ExternalArtist externalArtist : externalArtists) {
			ArtistId id = new ArtistId(externalArtist.getId());
			Money cost = new Money(new BigDecimal(externalArtist.getPrice()));

			Artist artist = new Artist(
					id,
					externalArtist.getName(),
					cost,
					externalArtist.getNbPeople(),
					externalArtist.getPopularityRank()
			);

			artists.add(artist);
		}

		return artists;
	}
}
