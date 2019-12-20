package ca.ulaval.glo4002.booking.profits.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfitReportTest {

    @Test
    void calculateProfit_shouldSetProfitAsTheDifferenceBetweenRevenueAndExpense() {
        Money expectedTotalProfit = new Money(BigDecimal.valueOf(1000));
        Money expense = new Money(BigDecimal.valueOf(2000));
        Money revenue = new Money(BigDecimal.valueOf(3000));

        ProfitReport report = new ProfitReport(expense, revenue);

        assertEquals(expectedTotalProfit, report.getTotalProfit());
    }
}
