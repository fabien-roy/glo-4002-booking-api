package ca.ulaval.glo4002.booking.builders.oxygen;

import ca.ulaval.glo4002.booking.builders.Builder;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.categories.AOxygenCategory;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.categories.BOxygenCategory;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.categories.EOxygenCategory;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.categories.OxygenCategory;
import ca.ulaval.glo4002.booking.exceptions.oxygen.OxygenCategoryNotFoundException;

public class OxygenCategoryBuilder implements Builder<OxygenCategory> {

    private OxygenProductionBuilder productionBuilder = new OxygenProductionBuilder();

    public OxygenCategory buildById(Long id) {
        if (id.equals(OxygenConstants.Categories.E_ID)) {
            return buildEOxygenCategory();
        } else if (id.equals(OxygenConstants.Categories.B_ID)) {
            return buildBOxygenCategory();
        } else if (id.equals(OxygenConstants.Categories.A_ID)) {
            return buildAOxygenCategory();
        } else {
            throw new OxygenCategoryNotFoundException();
        }
    }

    public OxygenCategory buildByName(String name) {
        switch (name) {
            case OxygenConstants.Categories.E_NAME:
                return buildEOxygenCategory();
            case OxygenConstants.Categories.B_NAME:
                return buildBOxygenCategory();
            case OxygenConstants.Categories.A_NAME:
                return buildAOxygenCategory();
            default:
                throw new OxygenCategoryNotFoundException();
        }
    }

    private OxygenCategory buildEOxygenCategory() {
        return new EOxygenCategory(productionBuilder.buildById(OxygenConstants.Categories.E_PRODUCTION_ID));
    }

    private OxygenCategory buildBOxygenCategory() {
        return new BOxygenCategory(productionBuilder.buildById(OxygenConstants.Categories.B_PRODUCTION_ID));
    }

    private OxygenCategory buildAOxygenCategory() {
        return new AOxygenCategory(productionBuilder.buildById(OxygenConstants.Categories.A_PRODUCTION_ID));
    }
}
