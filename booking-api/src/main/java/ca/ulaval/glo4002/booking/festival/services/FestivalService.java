package ca.ulaval.glo4002.booking.festival.services;

import ca.ulaval.glo4002.booking.festival.domain.Festival;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.program.events.domain.EventDateFactory;
import ca.ulaval.glo4002.booking.program.events.rest.EventDatesDto;

import javax.inject.Inject;

public class FestivalService {

    private final Festival festival;
    private final EventDateFactory eventDateFactory;

    @Inject
    public FestivalService(Festival festival, EventDateFactory eventDateFactory) {
        this.festival = festival;
        this.eventDateFactory = eventDateFactory;
    }

    public void setEventDates(EventDatesDto eventDatesDto) {
        EventDate startEventDate = eventDateFactory.parse(eventDatesDto.getBeginDate());
        EventDate endEventDate = eventDateFactory.parse(eventDatesDto.getEndDate());

        festival.setStartEventDate(startEventDate);
        festival.setEndEventDate(endEventDate);
    }
}
