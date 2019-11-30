package ca.ulaval.glo4002.booking.program.artists.domain;

import java.util.List;

import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.profits.domain.ProfitReport;

public class Artist {

    private Integer id;
    private String name;
    private Money cost;
    private Integer numberOfPeople;
    private String musicStyle;
    private Integer popularityRank;
    private List<Availability> availabilities;

    public Artist(Integer id, String name, Money cost, Integer numberOfPeople, String musicStyle, Integer popularityRank, List<Availability> availabilities) {
    	this.id = id;
		this.name = name;
		this.cost = cost;
		this.numberOfPeople = numberOfPeople;
		this.musicStyle = musicStyle;
		this.popularityRank = popularityRank;
		this.availabilities = availabilities;
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

    public String getMusicStyle() {
        return musicStyle;
    }

    public Integer getPopularityRank() {
        return popularityRank;
    }

	public List<Availability> getAvailabilities() {
		return availabilities;
	}

	public void updateProfit(ProfitReport profitReport) {
		profitReport.addExpense(cost);
	}
}
