package ca.ulaval.glo4002.booking.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import ca.ulaval.glo4002.booking.clients.ArtistClient;
import ca.ulaval.glo4002.booking.converters.ArtistConverter;
import ca.ulaval.glo4002.booking.domain.artist.BookingArtist;
import ca.ulaval.glo4002.booking.domain.artist.ExternalArtist;
import ca.ulaval.glo4002.booking.dto.events.ArtistListDto;
import ca.ulaval.glo4002.booking.enums.ArtistOrderings;

public class ArtistService {

	private final ArtistClient artistClient;
    private final ArtistConverter artistConverter;

    @Inject
    public ArtistService(ArtistConverter artistConverter, ArtistClient artistClient) {
        this.artistConverter = artistConverter;
        this.artistClient = artistClient;
    }

    public ArtistListDto getAll(String orderBy) {
        List<ExternalArtist> externalArtists = artistClient.getArtists();
        
        List<BookingArtist> bookingArtists = artistConverter.convert(externalArtists);


        if(orderBy.equalsIgnoreCase(ArtistOrderings.LOW_COSTS.toString())) {
        	orderByLowCost(bookingArtists);
        } else if (orderBy.equalsIgnoreCase(ArtistOrderings.MOST_POPULAR.toString())) {
        	orderByMostPopular(bookingArtists);
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
