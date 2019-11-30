package ca.ulaval.glo4002.booking.program.artists.infrastructure;

import ca.ulaval.glo4002.booking.program.artists.domain.Artist;

import java.util.List;

public interface ArtistRepository {
	
	Artist findByName(String name);

	List<Artist> findAll();
}
