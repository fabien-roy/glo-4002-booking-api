package ca.ulaval.glo4002.booking.profits;

import java.math.BigDecimal;

public class Profit {

    private Money expense;
    private Money revenue;
    private Money totalProfit;

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
        totalProfit = new Money(revenue.getValue());
        
        totalProfit.subtract(expense);
    }

    public Money getExpense() {
        return this.expense;
    }

    public Money getRevenue() {
        return this.revenue;
    }

    public Money getTotalProfit() {
        return this.totalProfit;
    }
}
