package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.NumberGenerator;
import ca.ulaval.glo4002.booking.domain.passes.*;
import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.NebulaPriceCalculationStrategy;
import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.NoDiscountPriceCalculationStrategy;
import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.PriceCalculationStrategy;
import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.SupergiantPriceCalculationStrategy;
import ca.ulaval.glo4002.booking.dto.PassListDto;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.PassOptions;
import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

public class PassListFactory {

    private final PassFactory passFactory;

    @Inject
    public PassListFactory(PassFactory passFactory) {
        this.passFactory = passFactory;
    }

    public PassList build(PassListDto passListDto) {
        PassCategories parsedPassCategory = parsePassCategory(passListDto);
        PassOptions parsedPassOption = parsePassOption(passListDto);

        validateEventDates(passListDto.getEventDates(), parsedPassOption);

        PassCategory passCategory = buildCategory(parsedPassCategory);
        PassOption passOption = buildOption(parsedPassOption);
        PriceCalculationStrategy priceCalculationStrategy = buildPriceCalculationStrategy(parsedPassCategory, parsedPassOption);

        List<Pass> passes = passFactory.buildAll(passListDto.getEventDates());

        return new PassList(passes, passCategory, passOption, priceCalculationStrategy);
    }

    private PassOptions parsePassOption(PassListDto passListDto) {
        return PassOptions.get(passListDto.getPassOption());
    }

    private PassCategories parsePassCategory(PassListDto passListDto) {
        return PassCategories.get(passListDto.getPassCategory());
    }

    private void validateEventDates(List<String> eventDates, PassOptions passOption) {
        if (eventDates == null) {
            if (passOption.equals(PassOptions.SINGLE_PASS)) {
                throw new InvalidFormatException();
            }
        } else {
            if (passOption.equals(PassOptions.PACKAGE)) {
                throw new InvalidFormatException();
            }
        }
    }

    private PassCategory buildCategory(PassCategories category) {
        switch(category) {
            case SUPERNOVA:
                return new PassCategory(PassCategories.SUPERNOVA.toString());
            case SUPERGIANT:
                return new PassCategory(PassCategories.SUPERGIANT.toString());
            default:
            case NEBULA:
                return new PassCategory(PassCategories.NEBULA.toString());
        }
    }

    private PassOption buildOption(PassOptions option) {
        switch (option) {
            case PACKAGE:
                return new PassOption(PassOptions.PACKAGE.toString());
            default:
            case SINGLE_PASS:
                return new PassOption(PassOptions.SINGLE_PASS.toString());
        }
    }

    private PriceCalculationStrategy buildPriceCalculationStrategy(PassCategories category, PassOptions option) {
        switch (option) {
            case PACKAGE:
                return new NoDiscountPriceCalculationStrategy();
            default:
            case SINGLE_PASS:
                switch(category) {
                    case SUPERNOVA:
                        return new NoDiscountPriceCalculationStrategy();
                    case SUPERGIANT:
                        return new SupergiantPriceCalculationStrategy();
                    default:
                    case NEBULA:
                        return new NebulaPriceCalculationStrategy();
                }
        }
    }
}
