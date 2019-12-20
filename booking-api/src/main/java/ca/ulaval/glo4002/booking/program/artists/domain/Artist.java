package ca.ulaval.glo4002.booking.program.artists.domain;

import ca.ulaval.glo4002.booking.profits.domain.Money;

public class Artist {

    private ArtistId id;
    private String name;
    private Money cost;
    private int numberOfPeople;
    private int popularityRank;

    public Artist(ArtistId id, String name, Money cost, int numberOfPeople, int popularityRank) {
    	this.id = id;
		this.name = name;
		this.cost = cost;
		this.numberOfPeople = numberOfPeople;
		this.popularityRank = popularityRank;
	}

	public ArtistId getId() {
		return id;
	}

    public String getName() {
		return name;
	}

	public Money getCost() {
		return cost;
	}

	public int getNumberOfPeople() {
		return numberOfPeople;
	}

    public int getPopularityRank() {
        return popularityRank;
    }
}
