package ca.ulaval.glo4002.booking.configuration;

import ca.ulaval.glo4002.booking.events.EventDateService;
import ca.ulaval.glo4002.booking.events.EventDatesDto;
import ca.ulaval.glo4002.booking.errors.ExceptionMapper;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/configuration")
public class ConfigurationController {

    private final ExceptionMapper exceptionMapper;
    private final EventDateService eventDateService;

	@Inject
	public ConfigurationController(ExceptionMapper exceptionMapper, EventDateService eventDateService) {
		this.exceptionMapper = exceptionMapper;
		this.eventDateService = eventDateService;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> setConfiguration(EventDatesDto eventDatesDto) {
		try {
			eventDateService.setConfiguration(eventDatesDto);
		} catch (Exception exception) {
			return exceptionMapper.mapError(exception);
		}

		return ResponseEntity.ok().build();
	}
}
