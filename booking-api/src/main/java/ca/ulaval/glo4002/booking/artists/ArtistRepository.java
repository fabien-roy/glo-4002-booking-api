package ca.ulaval.glo4002.booking.artists;

import java.util.List;

public interface ArtistRepository {
	
	void saveAll(List<BookingArtist> bookingArtists);
	
	List<BookingArtist> findAll();
}
