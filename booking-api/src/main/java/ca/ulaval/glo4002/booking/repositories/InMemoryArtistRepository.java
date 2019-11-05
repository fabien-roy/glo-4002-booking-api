package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.Artist;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.exceptions.InvalidProgramException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryArtistRepository implements ArtistRepository {

    private List<Artist> artists;

    public InMemoryArtistRepository() {
        artists = getPreparedArtists();
    }

    @Override
    public Artist getByName(String name) {
        Optional<Artist> foundArtist = artists.stream().filter(artist -> artist.getName().equals(name)).findAny();

        if (!foundArtist.isPresent()) {
            throw new InvalidProgramException();
        }

        return foundArtist.get();
    }

    // TODO : Shouldn't this be done at server start? (jersey)
    public static List<Artist> getPreparedArtists() {
        List<Artist> preparedArtists = new ArrayList<>();

        preparedArtists.add(new Artist("Sun 41", new Money(new BigDecimal(100000)), 5));
        preparedArtists.add(new Artist("Black Earth Peas", new Money(new BigDecimal(150000)), 4));
        preparedArtists.add(new Artist("Bruno Mars", new Money(new BigDecimal(200000)), 1));
        preparedArtists.add(new Artist("Mumford and Suns", new Money(new BigDecimal(100000)), 5));
        preparedArtists.add(new Artist("Kid Rocket", new Money(new BigDecimal(300000)), 1));
        preparedArtists.add(new Artist("Lady Gamma", new Money(new BigDecimal(500000)), 1));
        preparedArtists.add(new Artist("Cyndi Dauppler", new Money(new BigDecimal(200000)), 1));
        preparedArtists.add(new Artist("Kelvin Harris", new Money(new BigDecimal(150000)), 1));
        preparedArtists.add(new Artist("Suns N’ Roses", new Money(new BigDecimal(200000)), 5));
        preparedArtists.add(new Artist("Eclipse Persley", new Money(new BigDecimal(300000)), 1));
        preparedArtists.add(new Artist("30 Seconds to Mars", new Money(new BigDecimal(200000)), 4));
        preparedArtists.add(new Artist("Coldray", new Money(new BigDecimal(500000)), 4));
        preparedArtists.add(new Artist("Megadearth", new Money(new BigDecimal(200000)), 6));
        preparedArtists.add(new Artist("David Glowie", new Money(new BigDecimal(150000)), 1));
        preparedArtists.add(new Artist("XRay Charles", new Money(new BigDecimal(100000)), 1));
        preparedArtists.add(new Artist("Freddie Mercury", new Money(new BigDecimal(250000)), 1));
        preparedArtists.add(new Artist("Rolling Stars", new Money(new BigDecimal(400000)), 5));
        preparedArtists.add(new Artist("Simple Planet", new Money(new BigDecimal(450000)), 5));
        preparedArtists.add(new Artist("Novana", new Money(new BigDecimal(150000)), 6));

        return preparedArtists;
    }
}
