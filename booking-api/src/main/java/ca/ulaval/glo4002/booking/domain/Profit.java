package ca.ulaval.glo4002.booking.domain;

import ca.ulaval.glo4002.booking.domain.money.Money;

import java.math.BigDecimal;

public class Profit {

    private Money expense;
    private Money revenue;
    private Money profit;

    public Profit() {
        this.expense = new Money(new BigDecimal(0));
        this.revenue = new Money(new BigDecimal(0));
    }

    public void addRevenue(Money revenue){
        this.revenue.add(revenue);
    }

    public void addExpense(Money expense) {
        this.expense.add(expense);
    }

    public void calculateProfit() {
        profit = new Money(revenue.getValue());
        
        profit.subtract(expense);
    }

    public Money getExpense() {
        return this.expense;
    }

    public Money getRevenue() {
        return this.revenue;
    }

    public Money getProfit() {
        return this.profit;
    }
}
