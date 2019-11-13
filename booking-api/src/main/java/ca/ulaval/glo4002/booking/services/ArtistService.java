package ca.ulaval.glo4002.booking.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.inject.Inject;

import ca.ulaval.glo4002.booking.domain.artist.BookingArtist;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.dto.events.ArtistListDto;
import ca.ulaval.glo4002.booking.enums.ArtistOrderings;
import ca.ulaval.glo4002.booking.repositories.ArtistRepository;

public class ArtistService {
	
    private final ArtistRepository artistRepository;

    @Inject
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public ArtistListDto getAll(String orderBy) {

    	List<BookingArtist> bookingArtists = artistRepository.findAll();
    	
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
    	Collections.
    	sort(artists, (artist1, artist2) -> artist1.getPopularityRank() - artist2.getPopularityRank());
        return artists;
    }

    private List<BookingArtist> orderByLowCost(List<BookingArtist> artists) {
    	artists.sort(Comparator.comparing(((Function<BookingArtist, Money>)BookingArtist::getCost).andThen(Money::getValue)).reversed());
        return artists;
    }
}
