package ca.ulaval.glo4002.booking;

import org.glassfish.jersey.server.ResourceConfig;

public class BookingResourceConfig extends ResourceConfig {

    public BookingResourceConfig() {
        packages("ca.ulaval.glo4002.booking");

        register(new BookingBinder());
    }
}
