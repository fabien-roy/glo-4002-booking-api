package ca.ulaval.glo4002.booking.festival.services;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.program.events.domain.EventDateFactory;
import ca.ulaval.glo4002.booking.festival.rest.EventDatesRequest;

import javax.inject.Inject;

public class FestivalService {

    private final FestivalConfiguration festivalConfiguration;
    private final EventDateFactory eventDateFactory;

    @Inject
    public FestivalService(FestivalConfiguration festivalConfiguration, EventDateFactory eventDateFactory) {
        this.festivalConfiguration = festivalConfiguration;
        this.eventDateFactory = eventDateFactory;
    }

    public void setEventDates(EventDatesRequest eventDatesRequest) {
        EventDate startEventDate = eventDateFactory.parse(eventDatesRequest.getBeginDate());
        EventDate endEventDate = eventDateFactory.parse(eventDatesRequest.getEndDate());

        festivalConfiguration.setStartEventDate(startEventDate);
        festivalConfiguration.setEndEventDate(endEventDate);
    }
}
