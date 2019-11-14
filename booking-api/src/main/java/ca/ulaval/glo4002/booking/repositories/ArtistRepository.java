package ca.ulaval.glo4002.booking.repositories;

import java.util.List;

import ca.ulaval.glo4002.booking.domain.artist.BookingArtist;

public interface ArtistRepository {
	
	void saveAll(List<BookingArtist> bookingArtists);
	
	List<BookingArtist> findAll();
}
