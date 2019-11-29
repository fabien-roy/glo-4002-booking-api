package ca.ulaval.glo4002.booking.oxygen.domain;

import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.profits.domain.ProfitReport;

public class OxygenTank {

    // TODO : Those numbers should not be here...
    public static final int CATEGORY_A_NUMBER_OF_TANKS_CREATED = 5;
    public static final int CATEGORY_A_NUMBER_OF_RESOURCES_NEEDED = 15;
    public static final int CATEGORY_A_RESOURCE_PRICE = 650;
    public static final int CATEGORY_B_NUMBER_OF_TANKS_CREATED = 3;
    public static final int CATEGORY_B_NUMBER_OF_RESOURCES_NEEDED = 8;
    public static final int CATEGORY_B_RESOURCE_PRICE = 600;
    public static final int CATEGORY_E_NUMBER_OF_TANKS_CREATED = 1;
    public static final int CATEGORY_E_NUMBER_OF_RESOURCES_NEEDED = 1;
    public static final int CATEGORY_E_RESOURCE_PRICE = 5000;

    private OxygenCategory category;
    private OxygenDate requestDate;
    private OxygenDate readyDate;
    private Money price;

    public OxygenTank(OxygenCategory category, OxygenDate requestDate) {
        this.category = category;
        this.requestDate = requestDate;

        this.price = category.calculatePriceForCategory();
        this.readyDate = category.calculateReadyDateForCategory(requestDate.getValue());
    }

    public OxygenCategory getCategory() {
        return this.category;
    }

    public OxygenDate getRequestDate() {
        return this.requestDate;
    }

    public OxygenDate getReadyDate() {
        return this.readyDate;
    }

    public Money getPrice() {
        return this.price;
    }

    public void updateProfit(ProfitReport profitReport) {
        profitReport.addExpense(price);
    }
}