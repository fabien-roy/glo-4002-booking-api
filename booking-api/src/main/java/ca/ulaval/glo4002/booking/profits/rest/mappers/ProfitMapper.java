package ca.ulaval.glo4002.booking.profits.rest.mappers;

import ca.ulaval.glo4002.booking.profits.domain.Profit;
import ca.ulaval.glo4002.booking.profits.rest.ProfitsDto;

public class ProfitMapper {

    public ProfitsDto toDto(Profit profitReport) {
        Float in = profitReport.getRevenue().getValue().floatValue();
        Float out = profitReport.getExpense().getValue().floatValue();
        Float profit = profitReport.getTotalProfit().getValue().floatValue();

        return new ProfitsDto(in, out, profit);
    }
}
