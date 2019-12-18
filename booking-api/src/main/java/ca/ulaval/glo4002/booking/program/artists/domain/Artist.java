package ca.ulaval.glo4002.booking.program.artists.domain;

import ca.ulaval.glo4002.booking.profits.domain.Money;

public class Artist {

    private Integer id;
    private String name;
    private Money cost;
    private Integer numberOfPeople;
    private Integer popularityRank;

    public Artist(Integer id, String name, Money cost, Integer numberOfPeople, Integer popularityRank) {
    	this.id = id;
		this.name = name;
		this.cost = cost;
		this.numberOfPeople = numberOfPeople;
		this.popularityRank = popularityRank;
	}

	public Integer getId() {
		return id;
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

    public Integer getPopularityRank() {
        return popularityRank;
    }
}
