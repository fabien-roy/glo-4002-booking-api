package ca.ulaval.glo4002.booking.domain.artist;

public class ExternalArtistAvailability {

    private ExternalArtist artist;

    private String availability;
    
	public ExternalArtistAvailability(ExternalArtist artist, String availability) {
		this.artist = artist;
		this.availability = availability;
	}

    public String getAvailability() {
        return availability;
    }
    
}
