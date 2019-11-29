package ca.ulaval.glo4002.booking.program.events;

import ca.ulaval.glo4002.booking.festival.Festival;
import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.program.events.rest.exceptions.InvalidEventDateException;

import javax.inject.Inject;
import java.time.LocalDate;

public class EventDateFactory {

    private final Festival festival;

    @Inject
    public EventDateFactory(Festival festival) {
        this.festival = festival;
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
        EventDate startEventDate = festival.getStartEventDate();
        EventDate endEventDate = festival.getEndEventDate();

        if (!eventDate.isBetweenOrEquals(startEventDate, endEventDate)) {
            throw new InvalidEventDateException();
        }
    }
}
