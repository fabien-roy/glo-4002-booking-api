package ca.ulaval.glo4002.booking.builders.passes;

import ca.ulaval.glo4002.booking.builders.Builder;
import ca.ulaval.glo4002.booking.constants.PassConstants;
import ca.ulaval.glo4002.booking.domainobjects.passes.categories.NebulaPassCategory;
import ca.ulaval.glo4002.booking.domainobjects.passes.categories.PassCategory;
import ca.ulaval.glo4002.booking.domainobjects.passes.categories.SupergiantPassCategory;
import ca.ulaval.glo4002.booking.domainobjects.passes.categories.SupernovaPassCategory;
import ca.ulaval.glo4002.booking.domainobjects.passes.options.PassOption;
import ca.ulaval.glo4002.booking.exceptions.passes.PassCategoryNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class PassCategoryBuilder implements Builder<PassCategory> {

    private PassOptionBuilder optionBuilder = new PassOptionBuilder();

    public PassCategory buildById(Long id) {
        if (id.equals(PassConstants.Categories.SUPERNOVA_ID)) {
            return buildSupernovaPassCategory();
        } else if (id.equals(PassConstants.Categories.SUPERGIANT_ID)) {
            return buildSupergiantPassCategory();
        } else if (id.equals(PassConstants.Categories.NEBULA_ID)) {
            return buildNebulaPassCategory();
        } else {
            throw new PassCategoryNotFoundException(id.toString());
        }
    }

    public PassCategory buildByName(String name) {
        switch (name) {
            case PassConstants.Categories.SUPERNOVA_NAME:
                return buildSupernovaPassCategory();
            case PassConstants.Categories.SUPERGIANT_NAME:
                return buildSupergiantPassCategory();
            case PassConstants.Categories.NEBULA_NAME:
                return buildNebulaPassCategory();
            default:
                throw new PassCategoryNotFoundException(name);
        }
    }

    private PassCategory buildSupernovaPassCategory() {
        Map<PassOption, Double> pricePerOption = new HashMap<>();
        pricePerOption.put(optionBuilder.buildById(PassConstants.Options.PACKAGE_ID), PassConstants.Categories.SUPERNOVA_PACKAGE_PRICE);
        pricePerOption.put(optionBuilder.buildById(PassConstants.Options.SINGLE_ID), PassConstants.Categories.SUPERNOVA_SINGLE_PASS_PRICE);

        return new SupernovaPassCategory(pricePerOption);
    }

    private PassCategory buildSupergiantPassCategory() {
        Map<PassOption, Double> pricePerOption = new HashMap<>();
        pricePerOption.put(optionBuilder.buildById(PassConstants.Options.PACKAGE_ID), PassConstants.Categories.SUPERGIANT_PACKAGE_PRICE);
        pricePerOption.put(optionBuilder.buildById(PassConstants.Options.SINGLE_ID), PassConstants.Categories.SUPERGIANT_SINGLE_PASS_PRICE);

        return new SupergiantPassCategory(pricePerOption);
    }

    private PassCategory buildNebulaPassCategory() {
        Map<PassOption, Double> pricePerOption = new HashMap<>();
        pricePerOption.put(optionBuilder.buildById(PassConstants.Options.PACKAGE_ID), PassConstants.Categories.NEBULA_PACKAGE_PRICE);
        pricePerOption.put(optionBuilder.buildById(PassConstants.Options.SINGLE_ID), PassConstants.Categories.NEBULA_SINGLE_PASS_PRICE);

        return new NebulaPassCategory(pricePerOption);
    }
}
