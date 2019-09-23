package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.constants.PassConstants;
import ca.ulaval.glo4002.booking.entities.passes.PassCategory;
import ca.ulaval.glo4002.booking.entities.passes.PassOption;

import java.util.HashMap;

public class PassFactory {
    // TODO : How do we pass shuttle and oxygen factories?
    ShuttleFactory shuttleFactory = new ShuttleFactory();
    OxygenFactory oxygenFactory = new OxygenFactory();

    public PassCategory getPassCategoryById(Long categoryId) {
        // TODO : I wanted to do a switch-case, but apparently static final isn't const?
        if (categoryId.equals(PassConstants.Categories.SUPERNOVA_ID)) {
            return buildPassCategorySupernova();
        } else if (categoryId.equals(PassConstants.Categories.SUPERGIANT_ID)) {
            return buildPassCategorySupergiant();
        } else if (categoryId.equals(PassConstants.Categories.NEBULA_ID)) {
            return buildPassCategoryNebula();
        }

        return null; // TODO : Throw exception?
    }

    public PassOption getPassOptionById(Long optionId) {
        // TODO : I wanted to do a switch-case, but apparently static final isn't const?
        if (optionId.equals(PassConstants.Options.PACKAGE_ID)) {
            return buildPassOptionPackage();
        } else if (optionId.equals(PassConstants.Options.SINGLE_PASS_ID)) {
            return buildPassOptionSinglePass();
        }

        return null; // TODO : Throw exception?
    }

    private PassCategory buildPassCategorySupernova() {
        HashMap<PassOption, Double> pricePerOption = new HashMap<>();
        pricePerOption.put(getPassOptionById(PassConstants.Options.PACKAGE_ID), PassConstants.Categories.SUPERNOVA_PACKAGE_PRICE);
        pricePerOption.put(getPassOptionById(PassConstants.Options.SINGLE_PASS_ID), PassConstants.Categories.SUPERNOVA_SINGLE_PASS_PRICE);

        return new PassCategory(
                PassConstants.Categories.SUPERNOVA_ID,
                PassConstants.Categories.SUPERNOVA_NAME,
                pricePerOption,
                shuttleFactory.getShuttleCategoryById(PassConstants.Categories.SUPERNOVA_SHUTTLE_CATEGORY_ID),
                oxygenFactory.getOxygenCategoryById(PassConstants.Categories.SUPERNOVA_OXYGEN_CATEGORY_ID)
        );
    }

    private PassCategory buildPassCategorySupergiant() {
        HashMap<PassOption, Double> pricePerOption = new HashMap<>();
        pricePerOption.put(getPassOptionById(PassConstants.Options.PACKAGE_ID), PassConstants.Categories.SUPERGIANT_PACKAGE_PRICE);
        pricePerOption.put(getPassOptionById(PassConstants.Options.SINGLE_PASS_ID), PassConstants.Categories.SUPERGIANT_SINGLE_PASS_PRICE);

        return new PassCategory(
                PassConstants.Categories.SUPERGIANT_ID,
                PassConstants.Categories.SUPERGIANT_NAME,
                pricePerOption,
                shuttleFactory.getShuttleCategoryById(PassConstants.Categories.SUPERGIANT_SHUTTLE_CATEGORY_ID),
                oxygenFactory.getOxygenCategoryById(PassConstants.Categories.SUPERGIANT_OXYGEN_CATEGORY_ID)
        );
    }

    private PassCategory buildPassCategoryNebula() {
        HashMap<PassOption, Double> pricePerOption = new HashMap<>();
        pricePerOption.put(getPassOptionById(PassConstants.Options.PACKAGE_ID), PassConstants.Categories.NEBULA_PACKAGE_PRICE);
        pricePerOption.put(getPassOptionById(PassConstants.Options.SINGLE_PASS_ID), PassConstants.Categories.NEBULA_SINGLE_PASS_PRICE);

        return new PassCategory(
                PassConstants.Categories.NEBULA_ID,
                PassConstants.Categories.NEBULA_NAME,
                pricePerOption,
                shuttleFactory.getShuttleCategoryById(PassConstants.Categories.NEBULA_SHUTTLE_CATEGORY_ID),
                oxygenFactory.getOxygenCategoryById(PassConstants.Categories.NEBULA_OXYGEN_CATEGORY_ID)
        );
    }

    private PassOption buildPassOptionPackage() {
        return new PassOption(
                PassConstants.Options.PACKAGE_ID,
                PassConstants.Options.PACKAGE_NAME
        );
    }

    private PassOption buildPassOptionSinglePass() {
        return new PassOption(
                PassConstants.Options.SINGLE_PASS_ID,
                PassConstants.Options.SINGLE_PASS_NAME
        );
    }
}
