package ca.ulaval.glo4002.booking.festival.services;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.festival.rest.EventDatesRequest;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.program.events.rest.mappers.EventDateMapper;

import javax.inject.Inject;

public class FestivalService {

    private final FestivalConfiguration festivalConfiguration;
    private final EventDateMapper eventDateMapper;

    @Inject
    public FestivalService(FestivalConfiguration festivalConfiguration, EventDateMapper eventDateMapper) {
        this.festivalConfiguration = festivalConfiguration;
        this.eventDateMapper = eventDateMapper;
    }

    public void setEventDates(EventDatesRequest eventDatesRequest) {
        EventDate startEventDate = eventDateMapper.parse(eventDatesRequest.getBeginDate());
        EventDate endEventDate = eventDateMapper.parse(eventDatesRequest.getEndDate());

        festivalConfiguration.setStartEventDate(startEventDate);
        festivalConfiguration.setEndEventDate(endEventDate);
    }
}
