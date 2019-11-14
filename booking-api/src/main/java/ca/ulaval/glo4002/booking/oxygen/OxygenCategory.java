package ca.ulaval.glo4002.booking.oxygen;

public class OxygenCategory {

    private OxygenCategories category;
    private Integer tanksNeededPerDay;
    private Integer produceTimeInDays;

    public OxygenCategory(OxygenCategories category, Integer tanksNeededPerDay, Integer produceTimeInDays) {
        this.category = category;
        this.tanksNeededPerDay = tanksNeededPerDay;
        this.produceTimeInDays = produceTimeInDays;
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
}
