package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.dto.ShuttleManifestDto;
import ca.ulaval.glo4002.booking.exceptions.BookingException;
import ca.ulaval.glo4002.booking.services.ShuttleManifestService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/shuttle-manifests")
public class ShuttleManifestController {

    private final ShuttleManifestService service;

    @Inject
    public ShuttleManifestController(ShuttleManifestService service) {
        this.service = service;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> get(@QueryParam("date") String date) {
        ShuttleManifestDto shuttleManifestDto;

        try {
            shuttleManifestDto = service.get(date);
        } catch (BookingException exception) {
            return ResponseEntity.status(exception.getStatus()).body(exception.toErrorDto());
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(shuttleManifestDto);
    }
}
