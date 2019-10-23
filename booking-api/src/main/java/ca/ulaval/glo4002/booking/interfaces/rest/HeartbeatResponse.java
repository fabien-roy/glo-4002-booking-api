package ca.ulaval.glo4002.booking.interfaces.rest;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HeartbeatResponse {
    private final String token;
    private final OffsetDateTime time;

    @JsonCreator
    public HeartbeatResponse(@JsonProperty(value = "token", required = true) String token) {
        this.token = token;
        this.time = OffsetDateTime.now();
    }
}
