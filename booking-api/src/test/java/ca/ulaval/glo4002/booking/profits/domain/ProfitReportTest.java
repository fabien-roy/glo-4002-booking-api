package ca.ulaval.glo4002.booking.profits.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfitReportTest {

    private ProfitReport profitReport;

    @BeforeEach
    void setupProfit() {
        profitReport = new ProfitReport();
    }

    @Test
    void addExpense_shouldAddExpense() {
        BigDecimal amount = new BigDecimal(2000);
        Money expense = new Money(amount);

        profitReport.addExpense(expense);

        assertEquals(expense, profitReport.getExpense());
    }

    @Test
    void addRevenue_shouldAddRevenue() {
        BigDecimal amount = new BigDecimal(2000);
        Money revenue = new Money(amount);

        profitReport.addRevenue(revenue);

        assertEquals(revenue, profitReport.getRevenue());
    }

    @Test
    void calculateProfit_shouldSetProfitAsTheDifferenceBetweenRevenueAndExpense() {
        BigDecimal amountRevenue = new BigDecimal(3000);
        BigDecimal amountExpense = new BigDecimal(2000);
        Money revenue = new Money(amountRevenue);
        Money expense = new Money(amountExpense);

        profitReport.addRevenue(revenue);
        profitReport.addExpense(expense);

        assertEquals(revenue.subtract(expense), profitReport.getTotalProfit());
    }
}
