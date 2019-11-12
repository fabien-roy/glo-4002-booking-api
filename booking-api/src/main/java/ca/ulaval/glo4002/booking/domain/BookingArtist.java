package ca.ulaval.glo4002.booking.domain;

import ca.ulaval.glo4002.booking.domain.money.Money;

public class BookingArtist {

    private Number id;
    private String name;
    private Money cost;
    private Integer numberOfPeople;
    private String musicStyle;
    private Integer popularityRank;

    public BookingArtist(Number id, String name, Money cost, Integer numberOfPeople, String musicStyle, Integer popularityRank) {
        this.id = id;
		this.name = name;
		this.cost = cost;
		this.numberOfPeople = numberOfPeople;
		this.musicStyle = musicStyle;
		this.popularityRank = popularityRank;
	}

    public Number getId() {
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

    public String getMusicStyle() {
        return musicStyle;
    }

    public Integer getPopularityRank() {
        return popularityRank;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof BookingArtist)) return false;

        BookingArtist otherBookingArtist = (BookingArtist) other;

        return id.equals(otherBookingArtist.getId())
                && name.equals(otherBookingArtist.getName())
                && cost.equals(otherBookingArtist.getCost())
                && numberOfPeople.equals(otherBookingArtist.getNumberOfPeople())
                && musicStyle.equals(otherBookingArtist.getMusicStyle())
                && popularityRank.equals(otherBookingArtist.getPopularityRank());
    }

    @Override
    public int hashCode() {
        return id.hashCode() + name.hashCode() + cost.hashCode() + numberOfPeople.hashCode() + musicStyle.hashCode() + popularityRank.hashCode();
    }
}
