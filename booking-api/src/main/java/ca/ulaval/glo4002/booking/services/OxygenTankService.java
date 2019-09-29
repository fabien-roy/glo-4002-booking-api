package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.builders.oxygen.OxygenCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.entities.oxygen.categories.OxygenCategory;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class OxygenTankService {

    public OxygenCategory getOxygenCategoryForTimeTable(OxygenCategory category, LocalDate timeRequested) {
        Long timeToProduce = category.getProduction().getProductionTime().toDays();
        LocalDate timeReady = timeRequested.plusDays(timeToProduce);
        OxygenCategoryBuilder oxygenCategoryBuilder = new OxygenCategoryBuilder();

        if(timeReady.isBefore(FestivalConstants.Dates.START_DATE)) {
            return category;
        } else if(timeRequested.plus(10, DAYS).isBefore(FestivalConstants.Dates.START_DATE)){
            return  oxygenCategoryBuilder.buildById(OxygenConstants.Categories.B_ID);
        } else {
        	return oxygenCategoryBuilder.buildById(OxygenConstants.Categories.E_ID);
        }
    }
}
