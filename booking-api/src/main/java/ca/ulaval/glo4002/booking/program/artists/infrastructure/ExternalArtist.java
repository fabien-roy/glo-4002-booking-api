package ca.ulaval.glo4002.booking.program.artists.infrastructure;

import java.util.List;

public class ExternalArtist {

	private int id;
    private String name;
    private int nbPeople;
    private String musicStyle;
    private int price;
    private int popularityRank;
	private List<ExternalArtistAvailability> availabilities;

    public int getId() {
    	return id;
    }
    
    public String getName() {
        return name;
    }

    public Integer getNbPeople() {
        return nbPeople;
    }

    public int getPrice() {
        return price;
    }

    public int getPopularityRank() {
    	return popularityRank;
    }
}
