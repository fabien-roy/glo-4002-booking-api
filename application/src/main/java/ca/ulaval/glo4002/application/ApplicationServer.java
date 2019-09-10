package ca.ulaval.glo4002.application;

import ca.ulaval.glo4002.booking.BookingServer;
import ca.ulaval.glo4002.organisation.OrganisationServer;

public class ApplicationServer {
    public static void main(String[] args) throws InterruptedException {
        Thread organisation = new Thread(new OrganisationServer(args));
        Thread booking = new Thread(new BookingServer());

        booking.start();
        organisation.start();

        booking.join();
        organisation.join();
    }
}
