package ca.ulaval.glo4002.booking.repositories;

import java.util.List;

import ca.ulaval.glo4002.booking.ArtistConverter;
import ca.ulaval.glo4002.booking.domain.Artist;
import ca.ulaval.glo4002.booking.exceptions.InvalidProgramException;

public class InMemoryArtistRepository implements ArtistRepository {
	
    private List<Artist> artists;
    private ArtistConverter artistConverter;

    public InMemoryArtistRepository() {
    	
    }

    @Override
    public Artist getByName(String name) {
        return artists.stream().
        		filter(artist -> artist.getName().equals(name)).findAny().
        		orElseThrow(InvalidProgramException::new);
    }

    @Override
    public List<Artist> getAll() {
        return artists;
    }

}
