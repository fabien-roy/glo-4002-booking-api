package ca.ulaval.glo4002.booking.repositories;

import java.util.List;

import ca.ulaval.glo4002.booking.ArtistConverter;
import ca.ulaval.glo4002.booking.domain.BookingArtist;
import ca.ulaval.glo4002.booking.exceptions.InvalidProgramException;

public class InMemoryArtistRepository implements ArtistRepository {
	
    private List<BookingArtist> artists;
    private ArtistConverter artistConverter;

    public InMemoryArtistRepository() {
    	
    }

    @Override
    public BookingArtist getByName(String name) {
        return artists.stream().
        		filter(artist -> artist.getName().equals(name)).findAny().
        		orElseThrow(InvalidProgramException::new);
    }

    @Override
    public List<BookingArtist> getAll() {
        return artists;
    }

}
