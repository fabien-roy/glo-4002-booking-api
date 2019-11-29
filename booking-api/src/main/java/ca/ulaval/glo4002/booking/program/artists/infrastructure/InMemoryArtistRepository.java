package ca.ulaval.glo4002.booking.program.artists.infrastructure;

import ca.ulaval.glo4002.booking.program.artists.domain.BookingArtist;

import java.util.ArrayList;
import java.util.List;

public class InMemoryArtistRepository implements ArtistRepository {
	
	private List<BookingArtist> artists;
	
	public InMemoryArtistRepository() {
		artists = new ArrayList<>();
	}

	@Override
	public void saveAll(List<BookingArtist> bookingArtists) {
		List<String> artistNames = new ArrayList<>();
		artists.stream().map(BookingArtist::getName).forEach(artistNames::add);

		for(BookingArtist bookingArtist : bookingArtists) {
			if (!artistNames.contains(bookingArtist.getName())) {
				artists.add(bookingArtist);
			}
		}
	}

	@Override
	public List<BookingArtist> findAll() {
		return artists;
	}
}
