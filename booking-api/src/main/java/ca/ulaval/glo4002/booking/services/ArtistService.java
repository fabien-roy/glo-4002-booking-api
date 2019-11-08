package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.dto.events.ArtistListDto;
import ca.ulaval.glo4002.booking.enums.ArtistOrderings;
import ca.ulaval.glo4002.organisation.domain.Artist;
import ca.ulaval.glo4002.organisation.repositories.ArtistRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class ArtistService {

    private final ArtistRepository artistRepository;

    @Inject
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public ArtistListDto getAll(String orderBy) {
        List<Artist> artists = artistRepository.findAll();

        if (orderBy != null) {
            ArtistOrderings ordering = ArtistOrderings.get(orderBy);

            switch (ordering) {
                case LOW_COSTS:
                    artists = orderByLowCost(artists);
                    break;
                default:
                case MOST_POPULAR:
                    artists = orderByMostPopular(artists);
                    break;
            }
        }

        List<String> artistNames = getArtistNames(artists);

        return new ArtistListDto(artistNames);
    }

    private List<String> getArtistNames(List<Artist> artists) {
        return artists.stream().map(Artist::getName).collect(Collectors.toList());
    }

    private List<Artist> orderByMostPopular(List<Artist> artists) {
        return artists;
    }

    private List<Artist> orderByLowCost(List<Artist> artists) {
        return artists;
    }
}
