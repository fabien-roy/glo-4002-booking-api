package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.Artist;

public interface ArtistRepository {

    Artist getByName(String name);
}
