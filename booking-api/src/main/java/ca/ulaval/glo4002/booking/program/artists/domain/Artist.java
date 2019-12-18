package ca.ulaval.glo4002.booking.program.artists.domain;

import ca.ulaval.glo4002.booking.profits.domain.Money;

public class Artist {

	// TODO : Maybe add value objects for id and popularityRank

    private int id;
    private String name;
    private Money cost;
    private int numberOfPeople;
    private int popularityRank;

    public Artist(int id, String name, Money cost, int numberOfPeople, int popularityRank) {
    	this.id = id;
		this.name = name;
		this.cost = cost;
		this.numberOfPeople = numberOfPeople;
		this.popularityRank = popularityRank;
	}

	public int getId() {
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
