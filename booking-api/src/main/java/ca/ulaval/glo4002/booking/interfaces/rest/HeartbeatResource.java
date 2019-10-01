package ca.ulaval.glo4002.booking.interfaces.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/heartbeat")
@Produces(MediaType.APPLICATION_JSON)
public class HeartbeatResource {

    @GET
    public HeartbeatResponse heartbeat(@QueryParam("token") String token) {
        return new HeartbeatResponse(token);
    }

    @POST
    public Response blabla(HeartbeatResponse r) {
        return Response.ok().entity(r).build();
    }
}
