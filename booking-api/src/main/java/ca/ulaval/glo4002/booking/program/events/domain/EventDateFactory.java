package ca.ulaval.glo4002.booking.program.events.domain;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.program.events.rest.exceptions.InvalidEventDateException;

import javax.inject.Inject;
import java.time.LocalDate;

public class EventDateFactory {

    private final FestivalConfiguration festivalConfiguration;

    @Inject
    public EventDateFactory(FestivalConfiguration festivalConfiguration) {
        this.festivalConfiguration = festivalConfiguration;
    }

    public EventDate create(String eventDate) {
        EventDate parsedEventDate = parse(eventDate);

        validateEventDate(parsedEventDate);

        return parsedEventDate;
    }

    public EventDate parse(String eventDate) {
        EventDate parsedEventDate;

        try {
            LocalDate localDate = LocalDate.parse(eventDate);
            parsedEventDate = new EventDate(localDate);
        } catch (Exception exception) {
            throw new InvalidFormatException();
        }

        return parsedEventDate;
    }

    private void validateEventDate(EventDate eventDate) {
        EventDate startEventDate = festivalConfiguration.getStartEventDate();
        EventDate endEventDate = festivalConfiguration.getEndEventDate();

        if (!eventDate.isBetweenOrEquals(startEventDate, endEventDate)) {
            throw new InvalidEventDateException();
        }
    }
}
