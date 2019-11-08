package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.BookingArtist;
import ca.ulaval.glo4002.booking.dto.events.ArtistListDto;
import ca.ulaval.glo4002.booking.enums.ArtistOrderings;
import ca.ulaval.glo4002.organisation.domain.Artist;
import ca.ulaval.glo4002.organisation.repositories.ArtistRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArtistService {

    private final ArtistRepository artistRepository;

    @Inject
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    // TODO : ArtistService.getAll()
    public ArtistListDto getAll(String orderBy) {
        List<Artist> artists = artistRepository.findAll();
        List<String> artistNames = new ArrayList<>();

        if (orderBy == null) {
            artistNames = artists.stream().map(Artist::getName).collect(Collectors.toList());
        } else {
            ArtistOrderings ordering = ArtistOrderings.get(orderBy);

            // TODO : Order
        }

        return new ArtistListDto(artistNames);
    }
}
