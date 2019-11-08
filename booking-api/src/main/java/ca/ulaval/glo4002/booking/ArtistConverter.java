package ca.ulaval.glo4002.booking;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.domain.BookingArtist;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.organisation.repositories.ArtistRepository;

import javax.inject.Inject;

public class ArtistConverter {

	private final ArtistRepository artistRepository;

	@Inject
	public ArtistConverter(ArtistRepository artistRepository) {
		this.artistRepository = artistRepository;
	}

	// TODO : This should receive a list of external artists, it should not know the repository
	public List<BookingArtist> convertExternalArtists() {
		List<BookingArtist> bookingArtists = new ArrayList<>();

		for (ca.ulaval.glo4002.organisation.domain.Artist artist : artistRepository.findAll()) {
			BookingArtist bookingArtist = new BookingArtist(
					artist.getName(),
					new Money(new BigDecimal(artist.getPrice())),
					artist.getNbPeople(),
					artist.getMusicStyle(),
					artist.getPopularityRank()
			);

			bookingArtists.add(bookingArtist);
		}

		return bookingArtists;
	}
}
