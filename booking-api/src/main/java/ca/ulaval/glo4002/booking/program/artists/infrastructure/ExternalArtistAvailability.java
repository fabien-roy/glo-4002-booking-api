package ca.ulaval.glo4002.booking.program.artists.infrastructure;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExternalArtistAvailability {
	
    @JsonProperty("availability")
    private String date;
    
    public String getDate() {
    	return date;
    }
}
