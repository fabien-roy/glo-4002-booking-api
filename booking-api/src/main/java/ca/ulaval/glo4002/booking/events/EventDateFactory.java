package ca.ulaval.glo4002.booking.events;

import ca.ulaval.glo4002.booking.BookingConfiguration;
import ca.ulaval.glo4002.booking.program.InvalidProgramException;

import javax.inject.Inject;
import java.time.LocalDate;

public class EventDateFactory {

    private final BookingConfiguration configuration;

    @Inject
    public EventDateFactory(BookingConfiguration configuration) {
        this.configuration = configuration;
    }

    public EventDate build(String eventDate) {
        EventDate parsedEventDate = parseEventDate(eventDate);

        validateEventDate(parsedEventDate);

        return parsedEventDate;
    }

    private EventDate parseEventDate(String eventDate) {
        EventDate parsedEventDate;

        try {
            LocalDate localDate = LocalDate.parse(eventDate);
            parsedEventDate = new EventDate(localDate);
        } catch (Exception exception) {
            throw new InvalidProgramException();
        }

        return parsedEventDate;
    }

    private void validateEventDate(EventDate eventDate) {
        EventDate startEventDate = configuration.getStartEventDate();
        EventDate endEventDate = configuration.getEndEventDate();

        if (eventDate.isBefore(startEventDate) || eventDate.isAfter(endEventDate)) {
            throw new InvalidEventDateException();
        }
    }
}
