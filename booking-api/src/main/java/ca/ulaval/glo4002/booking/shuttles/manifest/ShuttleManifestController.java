package ca.ulaval.glo4002.booking.shuttles.manifest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;

import ca.ulaval.glo4002.booking.exceptions.ExceptionMapper;

@Path("/shuttle-manifests")
public class ShuttleManifestController {

    private final ExceptionMapper exceptionMapper;
	private final ShuttleManifestService service;

	@Inject
	public ShuttleManifestController(ExceptionMapper exceptionMapper, ShuttleManifestService service) {
		this.exceptionMapper = exceptionMapper;
		this.service = service;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> get(@QueryParam("date") String date) {
		ShuttleManifestDto shuttleManifestDto;

		if (date == null) {
			try {
				shuttleManifestDto = service.getTrips();
			} catch (Exception exception) {
				return exceptionMapper.mapError(exception);
			}
		} else {
			try {
				shuttleManifestDto = service.getTripsForDate(date);
			} catch (Exception exception) {
				return exceptionMapper.mapError(exception);
			}
		}

		return ResponseEntity.ok().body(shuttleManifestDto);
	}
}
