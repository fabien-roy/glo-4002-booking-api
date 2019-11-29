package ca.ulaval.glo4002.booking.oxygen.domain;

import ca.ulaval.glo4002.booking.oxygen.history.domain.OxygenHistory;
import ca.ulaval.glo4002.booking.profits.domain.Money;

import java.math.BigDecimal;
import java.time.LocalDate;

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
                          Integer numberOfTanksByBundle, Money productionItemPrice) {
        this.category = category;
        this.tanksNeededPerDay = tanksNeededPerDay;
        this.produceTimeInDays = produceTimeInDays;
        this.numberOfTanksByBundle = numberOfTanksByBundle;
        this.numberOfProductionItem = 0;
        this.productionItemPrice = productionItemPrice;
    }

    public OxygenCategories getCategory() {
        return category;
    }

    public Integer getTanksNeededPerDay() {
        return tanksNeededPerDay;
    }

    public Integer getNumberOfTanksByBundle() {
        return numberOfTanksByBundle;
    }

    public Money calculatePriceForCategory() {
        BigDecimal tankPrice = new BigDecimal(String.valueOf(productionItemPrice.getValue()));;

        if(category != OxygenCategories.E) {
            tankPrice = tankPrice.multiply(BigDecimal.valueOf(numberOfProductionItem));
            tankPrice = tankPrice.divide(BigDecimal.valueOf(numberOfTanksByBundle));
        }

        return new Money(tankPrice);
    }

    public OxygenDate calculateReadyDateForCategory(LocalDate requestDate) {
        OxygenDate readyDate = new OxygenDate(requestDate);

        readyDate.addDays(produceTimeInDays);

        return readyDate;
    }

    public void addCategoryProductionInformationToHistory(LocalDate requestDate, OxygenHistory history, Integer numberOfTanks) {
        if(category == OxygenCategories.A) {
            history.addCandlesUsed(requestDate,numberOfProductionItem / numberOfTanksByBundle * numberOfTanks);
        } else if(category == OxygenCategories.B) {
            history.addWaterUsed(requestDate, (double) numberOfProductionItem / numberOfTanksByBundle * numberOfTanks);
        }
    }

}