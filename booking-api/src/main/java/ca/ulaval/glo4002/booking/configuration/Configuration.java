package ca.ulaval.glo4002.booking.configuration;

import ca.ulaval.glo4002.booking.events.EventDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Configuration {

    private EventDate startEventDate;
    private EventDate endEventDate;

    public Configuration() {
        startEventDate = EventDate.getDefaultStartEventDate();
        endEventDate = EventDate.getDefaultEndEventDate();
    }

    public EventDate getStartEventDate() {
        return startEventDate;
    }

    public void setStartEventDate(EventDate startEventDate) {
        this.startEventDate = startEventDate;
    }

    public EventDate getEndEventDate() {
        return endEventDate;
    }

    public void setEndEventDate(EventDate endEventDate) {
        this.endEventDate = endEventDate;
    }

    public List<EventDate> getAllEventDates() {
        List<EventDate> allEventDates = new ArrayList<>();

        for (EventDate date = startEventDate; date.isBefore(endEventDate); date = date.plusDays(1)) {
            allEventDates.add(date);
        }

        return allEventDates;
    }
}
