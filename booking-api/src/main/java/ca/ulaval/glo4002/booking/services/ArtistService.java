package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.BookingArtist;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.factories.ArtistFactory;
import ca.ulaval.glo4002.booking.dto.events.ArtistListDto;
import ca.ulaval.glo4002.booking.enums.ArtistOrderings;
import ca.ulaval.glo4002.organisation.domain.Artist;
import ca.ulaval.glo4002.organisation.repositories.ArtistRepository;
import com.google.common.collect.Lists;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

public class ArtistService {

    private final ArtistRepository artistRepository;
    private final ArtistFactory artistFactory;

    @Inject
    public ArtistService(ArtistRepository artistRepository, ArtistFactory artistFactory) {
        this.artistRepository = artistRepository;
        this.artistFactory = artistFactory;
    }

    public ArtistListDto getAll(String orderBy) {
        List<Artist> artists = artistRepository.findAll();
        List<BookingArtist> bookingArtists = artistFactory.buildAll(artists);


        if (orderBy == null) {
            bookingArtists = orderByMostPopular(bookingArtists);
        } else {
            ArtistOrderings ordering = ArtistOrderings.get(orderBy);

            switch (ordering) {
                case MOST_POPULAR:
                    bookingArtists = orderByMostPopular(bookingArtists);
                    break;
                default:
                case LOW_COSTS:
                    bookingArtists = orderByLowCost(bookingArtists);
                    break;
            }
        }

        List<String> artistNames = getArtistNames(bookingArtists);

        return new ArtistListDto(artistNames);
    }

    private List<String> getArtistNames(List<BookingArtist> artists) {
        return artists.stream().map(BookingArtist::getName).collect(Collectors.toList());
    }

    private List<BookingArtist> orderByMostPopular(List<BookingArtist> artists) {
        return artists
                .stream()
                .sorted(Comparator.comparing(BookingArtist::getPopularityRank))
                .collect(Collectors.toList());
    }

    private List<BookingArtist> orderByLowCost(List<BookingArtist> artists) {
        return artists
                   .stream()
                   .sorted(Comparator.comparing(BookingArtist::getCost).reversed().thenComparing(BookingArtist::getPopularityRank))
                   .collect(Collectors.toList());
    }
}
