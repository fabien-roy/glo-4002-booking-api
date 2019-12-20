package ca.ulaval.glo4002.booking.profits.domain;

public class ProfitReport {

    private Money expense;
    private Money revenue;

    public ProfitReport(Money expense, Money revenue) {
        this.expense = expense;
        this.revenue = revenue;
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
