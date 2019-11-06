package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.BookingArtist;

import java.util.List;

public interface ArtistRepository {

    BookingArtist getByName(String name);

    List<BookingArtist> getAll();
}
