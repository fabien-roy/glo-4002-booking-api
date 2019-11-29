package ca.ulaval.glo4002.booking.festival.rest;

import ca.ulaval.glo4002.booking.festival.services.FestivalService;
import ca.ulaval.glo4002.booking.program.events.EventDatesDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// TODO : Rename Controllers Resource

@Path("/configuration")
public class ConfigurationController {

    private final FestivalService festivalService;

	@Inject
	public ConfigurationController(FestivalService festivalService) {
		this.festivalService = festivalService;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setConfiguration(EventDatesDto eventDatesDto) {
		festivalService.setEventDates(eventDatesDto);

		return Response.ok().build();
	}
}
