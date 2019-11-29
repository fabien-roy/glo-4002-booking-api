package ca.ulaval.glo4002.booking.profits.rest.mappers;

import ca.ulaval.glo4002.booking.profits.domain.ProfitReport;
import ca.ulaval.glo4002.booking.profits.rest.ProfitResponse;

public class ProfitMapper {

    public ProfitResponse toResponse(ProfitReport profitReport) {
        Float in = profitReport.getRevenue().getValue().floatValue();
        Float out = profitReport.getExpense().getValue().floatValue();
        Float profit = profitReport.getTotalProfit().getValue().floatValue();

        return new ProfitResponse(in, out, profit);
    }
}
