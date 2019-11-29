package ca.ulaval.glo4002.booking.festival;

import ca.ulaval.glo4002.booking.program.events.EventDate;
import ca.ulaval.glo4002.booking.program.events.EventDateFactory;
import ca.ulaval.glo4002.booking.program.events.EventDatesDto;

import javax.inject.Inject;

public class FestivalService {

    private final Festival festival;
    private final EventDateFactory eventDateFactory;

    // TODO : Remove @Injection from domain
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
