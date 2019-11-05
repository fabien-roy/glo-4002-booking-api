package ca.ulaval.glo4002.booking.domain;

import ca.ulaval.glo4002.booking.domain.money.Money;

public class Artist {

    private String name;
    private Money cost;
    private int membersAmount;

    public Artist(String name, Money cost, int membersAmount) {
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

    public int getMembersAmount() {
        return membersAmount;
    }
}
