package ca.ulaval.glo4002.booking.program.artists.services;

import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.program.artists.domain.Artist;
import ca.ulaval.glo4002.booking.program.artists.domain.ArtistOrderings;
import ca.ulaval.glo4002.booking.program.artists.infrastructure.ArtistRepository;
import ca.ulaval.glo4002.booking.program.artists.rest.ArtistListResponse;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ArtistService {

	private final ArtistRepository repository;

	@Inject
	public ArtistService(ArtistRepository repository) {
		this.repository = repository;
	}

	public Artist getByName(String name) {
		return repository.findByName(name);
	}

	public ArtistListResponse getAllOrdered(String orderBy) {
		List<Artist> artists = repository.findAll();

		switch (ArtistOrderings.get(orderBy)) {
			case LOW_COSTS:
				sortByLowCost(artists);
				break;
			default:
			case MOST_POPULAR:
				sortByMostPopular(artists);
				break;
		}

		List<String> artistNames = new ArrayList<>(getArtistNames(artists));

		return new ArtistListResponse(artistNames);
	}

	public ArtistListResponse getAllUnordered() {
		List<Artist> artists = repository.findAll();
		List<String> artistNames = getArtistNames(artists);

		return new ArtistListResponse(artistNames);
	}

	private List<String> getArtistNames(List<Artist> artists) {
		return artists.stream().map(Artist::getName).collect(Collectors.toList());
	}

	private void sortByMostPopular(List<Artist> artists) {
		artists.sort(Comparator.comparingInt(Artist::getPopularityRank));
	}

	private void sortByLowCost(List<Artist> artists) {
		artists.sort(Comparator
				.comparing(((Function<Artist, Money>) Artist::getCost).andThen(Money::getValue))
				.thenComparing(Artist::getPopularityRank)
                .reversed()
		);
	}
}
