package ca.ulaval.glo4002.booking.domain.artist;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import ca.ulaval.glo4002.organisation.domain.Artist;

public class ExternalArtistAvailability {
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonPropertyOrder({
		"availability"
	})
	
    private Integer id;

    private Artist artist;
    
    @JsonProperty("availability")
    private String date;

}
