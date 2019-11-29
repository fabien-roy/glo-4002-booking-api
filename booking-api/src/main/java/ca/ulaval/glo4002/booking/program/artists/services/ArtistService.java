package ca.ulaval.glo4002.booking.program.artists.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.inject.Inject;

import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.program.artists.domain.ArtistConverter;
import ca.ulaval.glo4002.booking.program.artists.domain.ArtistOrderings;
import ca.ulaval.glo4002.booking.program.artists.infrastructure.ArtistRepository;
import ca.ulaval.glo4002.booking.program.artists.domain.BookingArtist;
import ca.ulaval.glo4002.booking.program.artists.rest.ArtistListDto;
import ca.ulaval.glo4002.booking.program.rest.exceptions.InvalidProgramException;

public class ArtistService {

	private final ArtistRepository repository;

	@Inject
	public ArtistService(ArtistRepository repository, ArtistConverter converter) {
		this.repository = repository;

		converter.convert();
	}

	public BookingArtist getByName(String artistName) {
		List<BookingArtist> bookingArtists = repository.findAll();

		return bookingArtists
				.stream()
				.filter(artist -> artist.getName().equals(artistName))
				.findAny()
				.orElseThrow(InvalidProgramException::new);
	}

	public ArtistListDto getAllOrdered(String orderBy) {
		List<BookingArtist> bookingArtists = repository.findAll();
		List<String> artistNames = new ArrayList<>();

		if (orderBy.equalsIgnoreCase(ArtistOrderings.LOW_COSTS.toString())) {
			orderByLowCost(bookingArtists);
			artistNames.addAll(getArtistNames(bookingArtists));
		} else if (orderBy.equalsIgnoreCase(ArtistOrderings.MOST_POPULAR.toString())) {
			orderByMostPopular(bookingArtists);
			artistNames.addAll(getArtistNames(bookingArtists));
		} else {
			artistNames.addAll(getArtistNames(bookingArtists));
		}

		return new ArtistListDto(artistNames);
	}

	public ArtistListDto getAllUnordered() {
		List<BookingArtist> bookingArtists = repository.findAll();
		List<String> artistNames = getArtistNames(bookingArtists);
		return new ArtistListDto(artistNames);

	}

	private List<String> getArtistNames(List<BookingArtist> artists) {
		return artists.stream().map(BookingArtist::getName).collect(Collectors.toList());
	}

	private List<BookingArtist> orderByMostPopular(List<BookingArtist> artists) {
		artists.sort(Comparator.comparingInt(BookingArtist::getPopularityRank));

		return artists;
	}

	private List<BookingArtist> orderByLowCost(List<BookingArtist> artists) {
		artists.sort(
				Comparator.comparing(((Function<BookingArtist, Money>) BookingArtist::getCost).andThen(Money::getValue))
						.thenComparing(BookingArtist::getPopularityRank));
		return artists;
	}
}
