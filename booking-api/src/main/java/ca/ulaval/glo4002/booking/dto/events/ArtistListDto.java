package ca.ulaval.glo4002.booking.dto.events;

import java.util.List;

public class ArtistListDto {

    private List<String> artists;

    public ArtistListDto(List<String> artists) {
        this.artists = artists;
    }

    public List<String> getArtists() {
        return artists;
    }
}
