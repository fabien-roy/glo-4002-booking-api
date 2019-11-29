package ca.ulaval.glo4002.booking.program.artists.rest;

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
