package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.events.EventDate;
import ca.ulaval.glo4002.booking.domain.NumberGenerator;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.domain.passes.*;
import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PassFactory {

    private final NumberGenerator numberGenerator;

    @Inject
    public PassFactory(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public List<Pass> buildAll(List<String> eventDates, Money passPrice) {
        List<Pass> passes = new ArrayList<>();

        if (eventDates == null) {
            passes.add(new Pass(numberGenerator.generate(), passPrice));
        } else {
            eventDates.forEach(eventDate -> {
                EventDate builtEventDate = buildEventDate(eventDate);
                Pass pass = new Pass(numberGenerator.generate(), passPrice, builtEventDate);
                passes.add(pass);
            });
        }

        return passes;
    }

    // TODO : ACP : This is duplicate in EventFactory
    private EventDate buildEventDate(String eventDate) {
        LocalDate parsedEventDate;

        try {
            parsedEventDate = LocalDate.parse(eventDate);
        } catch (Exception exception) {
            throw new InvalidFormatException();
        }

        return new EventDate(parsedEventDate);
    }
}
