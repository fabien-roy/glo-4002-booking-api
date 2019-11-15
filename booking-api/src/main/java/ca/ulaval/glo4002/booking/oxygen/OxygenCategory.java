package ca.ulaval.glo4002.booking.oxygen;

import ca.ulaval.glo4002.booking.profits.Money;

import java.math.BigDecimal;

public class OxygenCategory {

    private OxygenCategories category;
    private Integer tanksNeededPerDay;
    private Integer produceTimeInDays;
    private Integer numberOfTanksByBundle;
    private Integer numberOfProductionItem;
    private Money productionItemPrice;

    public OxygenCategory(OxygenCategories category, Integer tanksNeededPerDay, Integer produceTimeInDays,
                          Integer numberOfTanksByBundle, Integer numberOfProductionItem, Money productionItemPrice) {
        this.category = category;
        this.tanksNeededPerDay = tanksNeededPerDay;
        this.produceTimeInDays = produceTimeInDays;
        this.numberOfTanksByBundle = numberOfTanksByBundle;
        this.numberOfProductionItem = numberOfProductionItem;
        this.productionItemPrice = productionItemPrice;
    }

    public OxygenCategory(OxygenCategories category, Integer tanksNeededPerDay, Integer produceTimeInDays,
                          Integer numberOfTanksByBundle) {
        this.category = category;
        this.tanksNeededPerDay = tanksNeededPerDay;
        this.produceTimeInDays = produceTimeInDays;
        this.numberOfTanksByBundle = numberOfTanksByBundle;
        this.numberOfProductionItem = 0;
        this.productionItemPrice = null;
    }

    public OxygenCategories getCategory() {
        return category;
    }

    public Integer getTanksNeededPerDay() {
        return tanksNeededPerDay;
    }

    public Integer getProduceTimeInDays() {
        return produceTimeInDays;
    }

    public Integer getNumberOfTanksByBundle() {
        return numberOfTanksByBundle;
    }

    public Integer getNumberOfProductionItem() {
        return numberOfProductionItem;
    }

    public Money calculatePriceForCategory() {
        BigDecimal tankPrice;

        if(category == OxygenCategories.E) {
            tankPrice = new BigDecimal(5000);
        } else {
            tankPrice = new BigDecimal(String.valueOf(productionItemPrice.getValue()));

            tankPrice = tankPrice.multiply(BigDecimal.valueOf(numberOfProductionItem));
            tankPrice = tankPrice.divide(BigDecimal.valueOf(numberOfTanksByBundle));
        }

        return new Money(tankPrice);
    }

    public OxygenDate calculateReadyDateForCategory(OxygenDate requestDate) {
        OxygenDate readyDate = new OxygenDate(requestDate.getValue());

        if (this.category == OxygenCategories.A) {
            readyDate.addDays(20);
        } else if (this.category == OxygenCategories.B) {
            readyDate.addDays(10);
        }

        return readyDate;
    }

}
