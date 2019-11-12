package ca.ulaval.glo4002.booking.domain.artist;

import java.util.List;

public class ExternalArtist {

	private Integer id;
	
    private String name;

    private Integer nbPeople;

    private String musicStyle;

    private Integer price;

    private Integer popularityRank;

    private List<ExternalArtistAvailability> artistAvailabilities;
    
    public ExternalArtist(Integer id,String name, Integer nbPeople, String musicStyle, Integer price,
			Integer popularityRank, List<ExternalArtistAvailability> artistAvailabilities) {
    	this.id = id;
		this.name = name;
		this.nbPeople = nbPeople;
		this.musicStyle = musicStyle;
		this.price = price;
		this.popularityRank = popularityRank;
		this.artistAvailabilities = artistAvailabilities;
	}

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
    	return popularityRank; }

    public List<ExternalArtistAvailability> getAvailabilities() {
        return artistAvailabilities;
    }

}
