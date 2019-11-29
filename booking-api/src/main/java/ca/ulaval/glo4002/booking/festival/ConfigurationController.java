package ca.ulaval.glo4002.booking.festival;

import ca.ulaval.glo4002.booking.program.events.EventDatesDto;
import ca.ulaval.glo4002.booking.errors.ExceptionMapper;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

// TODO : Rename Controllers Resource

@Path("/configuration")
public class ConfigurationController {

	// TODO : Remove ExceptionMapper in Controllers
    private final ExceptionMapper exceptionMapper;
    private final FestivalService festivalService;

	@Inject
	public ConfigurationController(ExceptionMapper exceptionMapper, FestivalService festivalService) {
		this.exceptionMapper = exceptionMapper;
		this.festivalService = festivalService;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> setConfiguration(EventDatesDto eventDatesDto) {
		try {
			festivalService.setEventDates(eventDatesDto);
		} catch (Exception exception) {
			return exceptionMapper.mapError(exception);
		}

		return ResponseEntity.ok().build();
	}
}
