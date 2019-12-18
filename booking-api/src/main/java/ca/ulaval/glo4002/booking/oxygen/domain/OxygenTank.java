package ca.ulaval.glo4002.booking.oxygen.domain;

import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.profits.domain.ProfitReport;

public class OxygenTank {

    // TODO : Those numbers should not be here...
    public static final int CATEGORY_A_NUMBER_OF_RESOURCES_NEEDED = 15;
    public static final int CATEGORY_B_NUMBER_OF_RESOURCES_NEEDED = 8;

    private OxygenProduction production;
    private Money price;

    public OxygenTank(OxygenProduction production) {
        this.production = production;

        this.price = production.calculatePriceForCategory();
    }

    public OxygenProduction getProduction() {
        return production;
    }

    public Money getPrice() {
        return price;
    }

    public void updateProfit(ProfitReport profitReport) {
        profitReport.addExpense(price);
    }
}