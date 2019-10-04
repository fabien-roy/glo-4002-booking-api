package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.qualities.Quality;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.Shuttle;

public interface ShuttleService {

    Shuttle findById(Long id);

    Iterable<Shuttle> findAll();

    Iterable<Shuttle> order(Quality quality);
}
