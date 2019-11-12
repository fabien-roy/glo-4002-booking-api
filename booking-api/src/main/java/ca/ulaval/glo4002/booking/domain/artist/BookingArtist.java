package ca.ulaval.glo4002.booking.domain.artist;

import java.util.List;

import ca.ulaval.glo4002.booking.domain.money.Money;

public class BookingArtist {

    private String name;
    private Money cost;
    private Integer numberOfPeople;
    private String musicStyle;
    private Integer popularityRank;
    private List<Availability> availabilities;

    public BookingArtist(String name, Money cost, Integer numberOfPeople,
    		String musicStyle, Integer popularityRank, List<Availability> availabilities) {
		this.name = name;
		this.cost = cost;
		this.numberOfPeople = numberOfPeople;
		this.musicStyle = musicStyle;
		this.popularityRank = popularityRank;
		this.availabilities = availabilities;
	}
    
    public String getName() {
		return name;
	}

	public Money getCost() {
		return cost;
	}

	public Integer getNumberOfPeople() {
		return numberOfPeople;
	}

    public String getMusicStyle() {
        return musicStyle;
    }

    public Integer getPopularityRank() {
        return popularityRank;
    }

	public List<Availability> getAvailabilities() {
		return availabilities;
	}

}
