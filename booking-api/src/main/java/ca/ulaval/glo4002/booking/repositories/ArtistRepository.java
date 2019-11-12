package ca.ulaval.glo4002.booking.repositories;

import java.util.List;

import ca.ulaval.glo4002.booking.domain.artist.BookingArtist;

public interface ArtistRepository {
	
	public void saveAll(List<BookingArtist> artists);
	
	public List<BookingArtist> findAll();

}
