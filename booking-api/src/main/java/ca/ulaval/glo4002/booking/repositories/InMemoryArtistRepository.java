package ca.ulaval.glo4002.booking.repositories;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.domain.artist.BookingArtist;

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

	@Override
	public boolean isEmpty() {
		return artists.isEmpty();
	}
	
	@Override
	public boolean isPresent(BookingArtist bookingArtist) {
		return artists.contains(bookingArtist);
	}
}
