package ca.ulaval.glo4002.booking.festival.rest;

import ca.ulaval.glo4002.booking.festival.services.FestivalService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/configuration")
@Produces(MediaType.APPLICATION_JSON)
public class ConfigurationResource {

    private final FestivalService festivalService;

	@Inject
	public ConfigurationResource(FestivalService festivalService) {
		this.festivalService = festivalService;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setConfiguration(EventDatesRequest eventDatesRequest) {
		festivalService.setEventDates(eventDatesRequest);

		return Response.ok().build();
	}
}
