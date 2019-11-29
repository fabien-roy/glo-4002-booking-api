package ca.ulaval.glo4002.booking.passes.domain;

import ca.ulaval.glo4002.booking.passes.rest.PassBundleRequest;
import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.passes.domain.pricecalculationstrategy.NebulaPriceCalculationStrategy;
import ca.ulaval.glo4002.booking.passes.domain.pricecalculationstrategy.NoDiscountPriceCalculationStrategy;
import ca.ulaval.glo4002.booking.passes.domain.pricecalculationstrategy.PriceCalculationStrategy;
import ca.ulaval.glo4002.booking.passes.domain.pricecalculationstrategy.SupergiantPriceCalculationStrategy;
import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;

public class PassBundleFactory {

    public static final Money SUPERNOVA_PACKAGE_PRICE = new Money(new BigDecimal(700000));
    public static final Money SUPERNOVA_SINGLE_PASS_PRICE = new Money(new BigDecimal(150000));
    public static final Money SUPERGIANT_PACKAGE_PRICE = new Money(new BigDecimal(500000));
    public static final Money SUPERGIANT_SINGLE_PASS_PRICE = new Money(new BigDecimal(100000));
    public static final Money NEBULA_PACKAGE_PRICE = new Money(new BigDecimal(250000));
    public static final Money NEBULA_SINGLE_PASS_PRICE = new Money(new BigDecimal(50000));

    private final PassFactory passFactory;

    @Inject
    public PassBundleFactory(PassFactory passFactory) {
        this.passFactory = passFactory;
    }

    public PassBundle build(PassBundleRequest passBundleRequest) {
        PassCategories parsedPassCategory = parsePassCategory(passBundleRequest);
        PassOptions parsedPassOption = parsePassOption(passBundleRequest);

        validateEventDates(passBundleRequest.getEventDates(), parsedPassOption);

        PassCategory passCategory = buildCategory(parsedPassCategory, parsedPassOption);
        PriceCalculationStrategy priceCalculationStrategy = buildPriceCalculationStrategy(parsedPassCategory, parsedPassOption);

        Money passPrice = passCategory.getPricePerOption(parsedPassOption);
        passPrice = calculatePassPrice(passBundleRequest.getEventDates(), passPrice, priceCalculationStrategy);

        List<Pass> passes = passFactory.buildAll(passBundleRequest.getEventDates(), passPrice);

        return new PassBundle(passes, passCategory, parsedPassOption);
    }

    private Money calculatePassPrice(List<String> eventDates, Money passPrice, PriceCalculationStrategy priceCalculationStrategy) {
        int passQuantity;
        if (eventDates == null) {
            passQuantity = 1;
        } else {
            passQuantity = eventDates.size();
        }

        return priceCalculationStrategy.calculatePassPrice(passQuantity, passPrice);
    }

    private PassOptions parsePassOption(PassBundleRequest passBundleRequest) {
        return PassOptions.get(passBundleRequest.getPassOption());
    }

    private PassCategories parsePassCategory(PassBundleRequest passBundleRequest) {
        return PassCategories.get(passBundleRequest.getPassCategory());
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
                passCategory = new PassCategory(PassCategories.SUPERNOVA, pricePerOption);
                break;
            case SUPERGIANT:
                pricePerOption.put(PassOptions.PACKAGE, SUPERGIANT_PACKAGE_PRICE);
                pricePerOption.put(PassOptions.SINGLE_PASS, SUPERGIANT_SINGLE_PASS_PRICE);
                passCategory = new PassCategory(PassCategories.SUPERGIANT, pricePerOption);
                break;
            default:
            case NEBULA:
                pricePerOption.put(PassOptions.PACKAGE, NEBULA_PACKAGE_PRICE);
                pricePerOption.put(PassOptions.SINGLE_PASS, NEBULA_SINGLE_PASS_PRICE);
                passCategory = new PassCategory(PassCategories.NEBULA, pricePerOption);
                break;
        }

        return passCategory;
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
