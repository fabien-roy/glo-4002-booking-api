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
		artists.addAll(bookingArtists);
		
	}

	@Override
	public List<BookingArtist> findAll() {
		return artists;
	}

	
}
