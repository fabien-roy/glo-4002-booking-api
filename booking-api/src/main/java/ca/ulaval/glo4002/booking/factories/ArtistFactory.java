package ca.ulaval.glo4002.booking.factories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.domain.BookingArtist;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.organisation.domain.Artist;


public class ArtistFactory {

	public List<BookingArtist> buildAll(List<Artist> artists) {
		List<BookingArtist> bookingArtists = new ArrayList<>();

		for (ca.ulaval.glo4002.organisation.domain.Artist artist : artists) {
			BookingArtist bookingArtist = build(artist);

			bookingArtists.add(bookingArtist);
		}

		return bookingArtists;
	}

	private BookingArtist build(Artist artist) {
		return new BookingArtist(
				artist.getName(),
				new Money(new BigDecimal(artist.getPrice())),
				artist.getNbPeople(),
				artist.getMusicStyle(),
				artist.getPopularityRank()
		);
	}
}
