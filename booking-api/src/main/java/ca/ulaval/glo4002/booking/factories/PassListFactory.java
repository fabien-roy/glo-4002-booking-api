package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.NumberGenerator;
import ca.ulaval.glo4002.booking.domain.money.Money;
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
import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PassListFactory {

    private static final Money SUPERNOVA_PACKAGE_PRICE = new Money(new BigDecimal(700000));
    private static final Money SUPERNOVA_SINGLE_PASS_PRICE = new Money(new BigDecimal(150000));
    private static final Money SUPERGIANT_PACKAGE_PRICE = new Money(new BigDecimal(500000));
    private static final Money SUPERGIANT_SINGLE_PASS_PRICE = new Money(new BigDecimal(100000));
    private static final Money NEBULA_PACKAGE_PRICE = new Money(new BigDecimal(250000));
    private static final Money NEBULA_SINGLE_PASS_PRICE = new Money(new BigDecimal(50000));

    private final PassFactory passFactory;

    @Inject
    public PassListFactory(PassFactory passFactory) {
        this.passFactory = passFactory;
    }

    public PassList build(PassListDto passListDto) {
        PassCategories parsedPassCategory = parsePassCategory(passListDto);
        PassOptions parsedPassOption = parsePassOption(passListDto);

        validateEventDates(passListDto.getEventDates(), parsedPassOption);

        PassCategory passCategory = buildCategory(parsedPassCategory, parsedPassOption);
        PassOption passOption = buildOption(parsedPassOption);
        PriceCalculationStrategy priceCalculationStrategy = buildPriceCalculationStrategy(parsedPassCategory, parsedPassOption);

        // TODO : Move this
        int passQuantity;
        if (passListDto.getEventDates() == null) {
            passQuantity = 1;
        } else {
            passQuantity = passListDto.getEventDates().size();
        }

        Money passPrice = passCategory.getPricePerOption(parsedPassOption);
        passPrice = priceCalculationStrategy.calculatePassPrice(passQuantity, passPrice);

        List<Pass> passes = passFactory.buildAll(passListDto.getEventDates(), passPrice);

        return new PassList(passes, passCategory, passOption);
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

    private PassCategory buildCategory(PassCategories category, PassOptions option) {
        PassCategory passCategory;
        EnumMap<PassOptions, Money> pricePerOption = new EnumMap<>(PassOptions.class);

        switch(category) {
            case SUPERNOVA:
                pricePerOption.put(PassOptions.PACKAGE, SUPERNOVA_PACKAGE_PRICE);
                pricePerOption.put(PassOptions.SINGLE_PASS, SUPERNOVA_SINGLE_PASS_PRICE);
                passCategory = new PassCategory(PassCategories.SUPERNOVA.toString(), pricePerOption);
                break;
            case SUPERGIANT:
                pricePerOption.put(PassOptions.PACKAGE, SUPERGIANT_PACKAGE_PRICE);
                pricePerOption.put(PassOptions.SINGLE_PASS, SUPERGIANT_SINGLE_PASS_PRICE);
                passCategory = new PassCategory(PassCategories.SUPERGIANT.toString(), pricePerOption);
                break;
            default:
            case NEBULA:
                pricePerOption.put(PassOptions.PACKAGE, NEBULA_PACKAGE_PRICE);
                pricePerOption.put(PassOptions.SINGLE_PASS, NEBULA_SINGLE_PASS_PRICE);
                passCategory = new PassCategory(PassCategories.NEBULA.toString(), pricePerOption);
                break;
        }

        return passCategory;
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
