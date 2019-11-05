package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.services.ShuttleManifestService;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/shuttle-manifests")
public class ShuttleManifestController {

    private final ShuttleManifestService service;

    @Inject
    public ShuttleManifestController(ShuttleManifestService service) {
        this.service = service;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> get(){
        // TODO

        return ResponseEntity.ok().body(null);
    }
}
