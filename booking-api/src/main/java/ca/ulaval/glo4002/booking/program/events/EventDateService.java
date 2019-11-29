package ca.ulaval.glo4002.booking.program.events;

import ca.ulaval.glo4002.booking.configuration.Configuration;

import javax.inject.Inject;

public class EventDateService {

    private final Configuration configuration;
    private final EventDateFactory eventDateFactory;

	@Inject
	public EventDateService(Configuration configuration, EventDateFactory eventDateFactory) {
		this.configuration = configuration;
		this.eventDateFactory = eventDateFactory;
	}

	public void setConfiguration(EventDatesDto eventDatesDto) {
	    EventDate startEventDate = eventDateFactory.parse(eventDatesDto.getBeginDate());
		EventDate endEventDate = eventDateFactory.parse(eventDatesDto.getEndDate());

		configuration.setStartEventDate(startEventDate);
		configuration.setEndEventDate(endEventDate);
	}
}
