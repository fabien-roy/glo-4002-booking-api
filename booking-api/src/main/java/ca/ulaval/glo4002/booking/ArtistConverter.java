package ca.ulaval.glo4002.booking;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.domain.Artist;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.organisation.repositories.ArtistRepository;

public class ArtistConverter {

	private final ArtistRepository artistRepository;
	
	public ArtistConverter(ArtistRepository artistRepository) {
		this.artistRepository = artistRepository;
	}
	
	public List<Artist> convertExternalArtists() {
		List<Artist> artists = new ArrayList<>();
		for (ca.ulaval.glo4002.organisation.domain.Artist artist : artistRepository.findAll()) {
			Artist bookingArtist = new Artist(artist.getName(),
					new Money(new BigDecimal(artist.getPrice())), artist.getNbPeople(),
					artist.getMusicStyle(), artist.getPopularityRank());
			artists.add(bookingArtist);
		}
		return artists;
	}

}
