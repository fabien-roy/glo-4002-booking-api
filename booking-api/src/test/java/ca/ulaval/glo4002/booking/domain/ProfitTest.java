package ca.ulaval.glo4002.booking.domain;

import ca.ulaval.glo4002.booking.profits.Money;
import ca.ulaval.glo4002.booking.profits.Profit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfitTest {

    private Profit profit;

    // TODO : test need refactoring

    @BeforeEach
    void setupProfit() {
        profit = new Profit();
        profit.addExpense(new Money(new BigDecimal(1)));
        profit.addRevenue(new Money(new BigDecimal(1)));
    }

    @Test
    void addExpense_shouldAddExpense() {
        BigDecimal amount = new BigDecimal(2000);
        Money expense = new Money(amount);

        profit.addExpense(expense);

        assertEquals(amount.add(BigDecimal.valueOf(1)), profit.getExpense().getValue());
    }

    @Test
    void addRevenue_shouldAddRevenue() {
        BigDecimal amount = new BigDecimal(2000);
        Money revenue = new Money(amount);

        profit.addRevenue(revenue);

        assertEquals(amount.add(BigDecimal.valueOf(1)), profit.getRevenue().getValue());
    }

    @Test
    void calculateProfit_shouldSetProfitAsTheDifferenceBetweenRevenueAndExpense() {
        BigDecimal amountRevenue = new BigDecimal(3000);
        BigDecimal amountExpense = new BigDecimal(2000);
        Money revenue = new Money(amountRevenue);
        Money expense = new Money(amountExpense);

        profit.addRevenue(revenue);
        profit.addExpense(expense);
        profit.calculateProfit();

        assertEquals(amountRevenue.subtract(amountExpense), profit.getProfit().getValue());
    }
}
