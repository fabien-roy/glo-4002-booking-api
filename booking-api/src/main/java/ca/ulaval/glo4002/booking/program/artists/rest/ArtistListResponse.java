package ca.ulaval.glo4002.booking.program.artists.rest;

import java.util.List;

public class ArtistListResponse {

    private List<String> artists;

    public ArtistListResponse(List<String> artists) {
        this.artists = artists;
    }

    public List<String> getArtists() {
        return artists;
    }
}
