package ca.ulaval.glo4002.booking.program.artists;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class ExternalArtist {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonPropertyOrder({
		"id",
		"name",
		"nbPeople", 
		"musicStyle",
		"price",
		"popularityRank"
	})
	
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("name")
    private String name;

	@JsonProperty("nbPeople")
    private Integer nbPeople;

	@JsonProperty("musicStyle")
    private String musicStyle;

	@JsonProperty("price")
    private Integer price;

	@JsonProperty("popularityRank")
    private Integer popularityRank;
	
	private List<ExternalArtistAvailability> availabilities;

    public Integer getId() {
    	return id;
    }
    
    public String getName() {
        return name;
    }

    public Integer getNbPeople() {
        return nbPeople;
    }

    public String getMusicStyle()  {
        return musicStyle;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getPopularityRank() {
    	return popularityRank;
    }

    public List<ExternalArtistAvailability> getAvailabilities() {
    	return availabilities;
    }
}
