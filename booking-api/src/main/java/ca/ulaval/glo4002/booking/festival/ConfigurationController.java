package ca.ulaval.glo4002.booking.festival;

import ca.ulaval.glo4002.booking.program.events.EventDatesDto;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

// TODO : Rename Controllers Resource
// TODO : Use ResponseEntity in Controllers

@Path("/configuration")
public class ConfigurationController {

    private final FestivalService festivalService;

	@Inject
	public ConfigurationController(FestivalService festivalService) {
		this.festivalService = festivalService;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> setConfiguration(EventDatesDto eventDatesDto) {
		festivalService.setEventDates(eventDatesDto);

		return ResponseEntity.ok().build();
	}
}
