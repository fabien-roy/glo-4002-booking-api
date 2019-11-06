package ca.ulaval.glo4002.booking.domain.oxygen;

import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.enums.OxygenCategories;
import ca.ulaval.glo4002.booking.exceptions.oxygen.InvalidOxygenCategoryException;

import java.math.BigDecimal;

public class OxygenTank {

    public static final int CATEGORY_A_NUMBER_OF_TANKS_CREATED = 5;
    public static final int CATEGORY_A_NUMBER_OF_RESOURCES_NEEDED = 15;
    public static final int CATEGORY_A_RESOURCE_PRICE = 650;
    public static final int CATEGORY_B_NUMBER_OF_TANKS_CREATED = 3;
    public static final int CATEGORY_B_NUMBER_OF_RESOURCES_NEEDED = 8;
    public static final int CATEGORY_B_RESOURCE_PRICE = 600;
    public static final int CATEGORY_E_NUMBER_OF_TANKS_CREATED = 1;
    public static final int CATEGORY_E_NUMBER_OF_RESOURCES_NEEDED = 1;
    public static final int CATEGORY_E_RESOURCE_PRICE = 5000;

    private OxygenCategories category;
    private OxygenDate requestDate;
    private OxygenDate readyDate;
    private Money price;

    public OxygenTank(OxygenCategories category, OxygenDate requestDate) {
        this.category = category;
        this.requestDate = requestDate;

        calculatePrice();
        calculateReadyDate();
    }

    public OxygenCategories getCategory() {
        return this.category;
    }

    public OxygenDate getReadyDate() {
        return this.readyDate;
    }

    public Money getPrice() {
        return this.price;
    }

    private void calculatePrice() {
        int nbTankCreated;
        int nbResources;
        int resourcesPrice;

        switch (this.category) {
            case A:
                nbTankCreated = CATEGORY_A_NUMBER_OF_TANKS_CREATED;
                nbResources = CATEGORY_A_NUMBER_OF_RESOURCES_NEEDED;
                resourcesPrice = CATEGORY_A_RESOURCE_PRICE;
                break;
            case B:
                nbTankCreated = CATEGORY_B_NUMBER_OF_TANKS_CREATED;
                nbResources = CATEGORY_B_NUMBER_OF_RESOURCES_NEEDED;
                resourcesPrice = CATEGORY_B_RESOURCE_PRICE;
                break;
            case E:
                nbTankCreated = CATEGORY_E_NUMBER_OF_TANKS_CREATED;
                nbResources = CATEGORY_E_NUMBER_OF_RESOURCES_NEEDED;
                resourcesPrice = CATEGORY_E_RESOURCE_PRICE;
                break;
            default:
                throw new InvalidOxygenCategoryException(category);
        }

        BigDecimal tankPrice = new BigDecimal((nbResources * resourcesPrice) / nbTankCreated);

        this.price = new Money(tankPrice);
    }

    private void calculateReadyDate() {
        OxygenDate readyDate = this.requestDate;

        if (this.category == OxygenCategories.A) {
            readyDate.addDays(20);
        } else if (this.category == OxygenCategories.B) {
            readyDate.addDays(10);
        }

        this.readyDate = readyDate;
    }
}
