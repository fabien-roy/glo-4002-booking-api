package ca.ulaval.glo4002.booking.passes.rest.mappers;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.passes.domain.PassCategories;
import ca.ulaval.glo4002.booking.passes.domain.PassOptions;
import ca.ulaval.glo4002.booking.passes.domain.PassList;
import ca.ulaval.glo4002.booking.passes.domain.pricediscountstrategy.NebulaPriceDiscountStrategy;
import ca.ulaval.glo4002.booking.passes.domain.pricediscountstrategy.NoPriceDiscountStrategy;
import ca.ulaval.glo4002.booking.passes.domain.pricediscountstrategy.PriceDiscountStrategy;
import ca.ulaval.glo4002.booking.passes.domain.pricediscountstrategy.SupergiantPriceDiscountStrategy;
import ca.ulaval.glo4002.booking.passes.rest.PassRefactoredRequest;
import ca.ulaval.glo4002.booking.passes.rest.PassResponse;
import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.program.events.rest.mappers.EventDateMapper;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PassRefactoredMapper {

    private final FestivalConfiguration festivalConfiguration;
    private final EventDateMapper eventDateMapper;

    @Inject
    public PassRefactoredMapper(FestivalConfiguration festivalConfiguration, EventDateMapper eventDateMapper) {
        this.festivalConfiguration = festivalConfiguration;
        this.eventDateMapper = eventDateMapper;
    }

    public PassList fromRequest(PassRefactoredRequest request) {
        PassCategories category = parsePassCategory(request.getPassCategory());
        PassOptions option = parsePassOption(request.getPassOption());

        List<EventDate> eventDates = buildEventDates(request.getEventDates(), option);
        List<EventDate> arrivalDates = buildArrivalDates(eventDates, option);
        List<EventDate> departureDates = buildDepartureDates(eventDates, option);

        Money price = calculatePrice(request.getEventDates(), category, option);

        return new PassList(category, option, price, eventDates, arrivalDates, departureDates);
    }

    public List<PassResponse> toResponse(PassList pass) {
        String passCategory = pass.getCategory().toString();
        String passOption = pass.getOption().toString();

        List<PassResponse> passResponses = new ArrayList<>();

        switch (pass.getOption()) {
            case PACKAGE:
                passResponses.add(new PassResponse(
                        pass.getNumber(),
                        passCategory,
                        passOption
                ));

                break;
            default:
            case SINGLE_PASS:
                pass.getEventDates().forEach(eventDate -> passResponses.add(
                        new PassResponse(
                            pass.getNumber(),
                            passCategory,
                            passOption,
                            eventDate.toString()
                        ))
                );

                break;
        }

        return passResponses;
    }

    private PassCategories parsePassCategory(String category) {
        return PassCategories.get(category);
    }

    private PassOptions parsePassOption(String option) {
        return PassOptions.get(option);
    }

    private Money calculatePrice(List<String> eventDates, PassCategories category, PassOptions option) {
        switch (option) {
            case PACKAGE:
                switch (category) {
                    case SUPERNOVA:
                        return new Money(BigDecimal.valueOf(700000));
                    case SUPERGIANT:
                        return new Money(BigDecimal.valueOf(500000));
                    default:
                    case NEBULA:
                        return new Money(BigDecimal.valueOf(250000));
                }

            default:
            case SINGLE_PASS:
                PriceDiscountStrategy priceDiscountStrategy;
                Money singlePassPrice;

                switch (category) {
                    case SUPERNOVA:
                        priceDiscountStrategy = new NoPriceDiscountStrategy();
                        singlePassPrice = new Money(BigDecimal.valueOf(150000));
                        break;
                    case SUPERGIANT:
                        priceDiscountStrategy = new SupergiantPriceDiscountStrategy();
                        singlePassPrice = new Money(BigDecimal.valueOf(100000));
                        break;
                    default:
                    case NEBULA:
                        priceDiscountStrategy = new NebulaPriceDiscountStrategy();
                        singlePassPrice = new Money(BigDecimal.valueOf(50000));
                        break;
                }

                singlePassPrice = priceDiscountStrategy.calculateDiscount(eventDates.size(), singlePassPrice);

                return singlePassPrice.multiply(BigDecimal.valueOf(eventDates.size()));
        }
    }

    private List<EventDate> buildEventDates(List<String> eventDates, PassOptions option) {
        List<EventDate> builtEventDates;

        switch (option) {
            case PACKAGE:
                if (eventDates != null) {
                    throw new InvalidFormatException();
                }

                builtEventDates = festivalConfiguration.getAllEventDates();

                break;
            default:
            case SINGLE_PASS:
                if (eventDates == null) {
                    throw new InvalidFormatException();
                }

                builtEventDates = eventDateMapper.fromString(eventDates);

                break;
        }

        return builtEventDates;
    }

    private List<EventDate> buildArrivalDates(List<EventDate> eventDates, PassOptions option) {
        switch (option) {
            case PACKAGE:
                return Collections.singletonList(festivalConfiguration.getStartEventDate());
            default:
            case SINGLE_PASS:
                return eventDates;
        }
    }

    private List<EventDate> buildDepartureDates(List<EventDate> eventDates, PassOptions option) {
        switch (option) {
            case PACKAGE:
                return Collections.singletonList(festivalConfiguration.getEndEventDate());
            default:
            case SINGLE_PASS:
                return eventDates;
        }
    }
}
