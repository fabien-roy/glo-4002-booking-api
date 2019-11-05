package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.Artist;
import ca.ulaval.glo4002.booking.domain.events.Event;

import java.util.ArrayList;
import java.util.List;

public class InMemoryArtistRepository implements ArtistRepository {

    private List<Artist> artists;

    public InMemoryArtistRepository() {
        artists = new ArrayList<>();
    }

    @Override
    public Artist getByName(String name) {
        // TODO
        return null;
    }
}
