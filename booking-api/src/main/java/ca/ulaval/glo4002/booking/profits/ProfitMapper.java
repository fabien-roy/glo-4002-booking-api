package ca.ulaval.glo4002.booking.profits;

public class ProfitMapper {

    public ProfitsDto toDto(Profit profitReport) {
        Float in = profitReport.getRevenue().getValue().floatValue();
        Float out = profitReport.getExpense().getValue().floatValue();
        Float profit = profitReport.getTotalProfit().getValue().floatValue();

        return new ProfitsDto(in, out, profit);
    }
}
