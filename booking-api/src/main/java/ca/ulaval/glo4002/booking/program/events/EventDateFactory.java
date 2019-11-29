package ca.ulaval.glo4002.booking.program.events;

import ca.ulaval.glo4002.booking.configuration.Configuration;
import ca.ulaval.glo4002.booking.errors.InvalidFormatException;

import javax.inject.Inject;
import java.time.LocalDate;

public class EventDateFactory {

    private final Configuration configuration;

    @Inject
    public EventDateFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    public EventDate build(String eventDate) {
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
            throw new InvalidFormatException(); // TODO : If EventDate is invalid in DEV, it should throw InvalidProgramException
        }

        return parsedEventDate;
    }

    private void validateEventDate(EventDate eventDate) {
        EventDate startEventDate = configuration.getStartEventDate();
        EventDate endEventDate = configuration.getEndEventDate();

        if (!eventDate.isBetweenOrEquals(startEventDate, endEventDate)) {
            throw new InvalidEventDateException();
        }
    }
}
