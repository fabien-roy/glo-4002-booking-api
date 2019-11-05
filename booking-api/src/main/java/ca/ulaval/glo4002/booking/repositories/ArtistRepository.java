package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.Artist;

import java.util.List;

public interface ArtistRepository {

    void addAll(List<Artist> artists);

    Artist getByName(String name);
}
