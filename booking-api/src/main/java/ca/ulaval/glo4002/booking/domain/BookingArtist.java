package ca.ulaval.glo4002.booking.domain;

import ca.ulaval.glo4002.booking.domain.money.Money;

public class Artist {

    private String name;
    private Money cost;
    private Integer membersAmount;

    public Artist(String name, Money cost, Integer membersAmount) {
        this.name = name;
        this.cost = cost;
        this.membersAmount = membersAmount;
    }

    public String getName() {
        return name;
    }

    public Money getCost() {
        return cost;
    }

    public Integer getMembersAmount() {
        return membersAmount;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Artist)) return false;

        Artist otherArtist = (Artist) other;

        return name.equals(otherArtist.getName())
                && cost.equals(otherArtist.getCost())
                && membersAmount.equals(otherArtist.getMembersAmount());
    }

    @Override
    public int hashCode() {
        return name.hashCode() + cost.hashCode() + membersAmount.hashCode();
    }
}
