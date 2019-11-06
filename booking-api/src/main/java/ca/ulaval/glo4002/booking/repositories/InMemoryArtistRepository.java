package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.Artist;
import ca.ulaval.glo4002.booking.exceptions.InvalidProgramException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryArtistRepository implements ArtistRepository {

    private List<Artist> artists;

    public InMemoryArtistRepository() {
        artists = new ArrayList<>();
    }

    @Override
    public void addAll(List<Artist> artists) {
        this.artists.addAll(artists);
    }

    @Override
    public Artist getByName(String name) {
        Optional<Artist> foundArtist = artists.stream().filter(artist -> artist.getName().equals(name)).findAny();

        if (!foundArtist.isPresent()) {
            throw new InvalidProgramException();
        }

        return foundArtist.get();
    }
}
