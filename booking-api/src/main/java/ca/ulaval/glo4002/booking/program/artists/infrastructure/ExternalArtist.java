package ca.ulaval.glo4002.booking.program.artists.infrastructure;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ExternalArtist {

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
