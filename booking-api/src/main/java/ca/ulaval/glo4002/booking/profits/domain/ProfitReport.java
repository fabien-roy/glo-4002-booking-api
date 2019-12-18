package ca.ulaval.glo4002.booking.profits.domain;

import java.math.BigDecimal;

public class ProfitReport {

    private Money expense;
    private Money revenue;

    // TODO : Remove empty constructor
    public ProfitReport() {
        this.expense = new Money(new BigDecimal(0));
        this.revenue = new Money(new BigDecimal(0));
    }

    public ProfitReport(Money expense, Money revenue) {
        this.expense = expense;
        this.revenue = revenue;
    }

    public void addRevenue(Money revenue){
        this.revenue = this.revenue.add(revenue);
    }

    public void addExpense(Money expense) {
        this.expense = this.expense.add(expense);
    }

    public Money getExpense() {
        return this.expense;
    }

    public Money getRevenue() {
        return this.revenue;
    }

    public Money getTotalProfit() {
        return revenue.subtract(expense);
    }
}
