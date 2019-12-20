package ca.ulaval.glo4002.booking.program.artists.domain;

import java.util.List;

public interface ArtistRepository {
	
	Artist findByName(String name);

	List<Artist> findAll();
}
