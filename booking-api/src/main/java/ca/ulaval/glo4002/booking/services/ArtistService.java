package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.Artist;
import ca.ulaval.glo4002.booking.dto.events.ArtistListDto;
import ca.ulaval.glo4002.booking.enums.ArtistOrderings;
import ca.ulaval.glo4002.booking.repositories.ArtistRepository;
import ca.ulaval.glo4002.booking.repositories.EventRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArtistService {

    private final ArtistRepository artistRepository;
    private final EventRepository eventRepository;

    @Inject
    public ArtistService(ArtistRepository artistRepository, EventRepository eventRepository) {
        this.artistRepository = artistRepository;
        this.eventRepository = eventRepository;
    }

    public ArtistListDto getAll(String orderBy) {
        List<String> artistNames = new ArrayList<>();

        if (orderBy == null) {
            artistNames = artistRepository.getAll().stream().map(Artist::getName).collect(Collectors.toList());
        } else {
            ArtistOrderings ordering = ArtistOrderings.get(orderBy);

            // TODO : With orderBy lowCost
            // TODO : With orderBy mostPopular
        }

        return new ArtistListDto(artistNames);
    }
}
