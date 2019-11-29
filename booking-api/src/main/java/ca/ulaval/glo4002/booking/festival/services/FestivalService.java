package ca.ulaval.glo4002.booking.festival.services;

import ca.ulaval.glo4002.booking.festival.domain.Festival;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.program.events.domain.EventDateFactory;
import ca.ulaval.glo4002.booking.festival.rest.EventDatesRequest;

import javax.inject.Inject;

public class FestivalService {

    private final Festival festival;
    private final EventDateFactory eventDateFactory;

    @Inject
    public FestivalService(Festival festival, EventDateFactory eventDateFactory) {
        this.festival = festival;
        this.eventDateFactory = eventDateFactory;
    }

    public void setEventDates(EventDatesRequest eventDatesRequest) {
        EventDate startEventDate = eventDateFactory.parse(eventDatesRequest.getBeginDate());
        EventDate endEventDate = eventDateFactory.parse(eventDatesRequest.getEndDate());

        festival.setStartEventDate(startEventDate);
        festival.setEndEventDate(endEventDate);
    }
}
