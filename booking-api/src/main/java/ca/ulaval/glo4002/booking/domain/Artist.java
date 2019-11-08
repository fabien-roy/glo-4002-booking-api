package ca.ulaval.glo4002.booking.domain;

import ca.ulaval.glo4002.booking.domain.money.Money;

public class Artist {

    private String name;
    private Money cost;
    private Integer numberOfPeople;
    private String musicStyle;
    private Integer popularityRank;

    public Artist(String name, Money cost, Integer numberOfPeople, String musicStyle, Integer popularityRank) {
		this.name = name;
		this.cost = cost;
		this.numberOfPeople = numberOfPeople;
		this.musicStyle = musicStyle;
		this.popularityRank = popularityRank;
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

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Artist)) return false;

        Artist otherArtist = (Artist) other;

        return name.equals(otherArtist.getName())
                && cost.equals(otherArtist.getCost())
                && numberOfPeople.equals(otherArtist.getNumberOfPeople())
                && musicStyle.equals(otherArtist.getMusicStyle())
                && popularityRank.equals(otherArtist.getPopularityRank());
    }

    @Override
    public int hashCode() {
        return name.hashCode() + cost.hashCode() + numberOfPeople.hashCode() + musicStyle.hashCode() + popularityRank.hashCode();
    }
}
