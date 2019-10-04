package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.qualities.Quality;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.ShuttleInventory;

import java.time.LocalDate;

public interface ShuttleInventoryService {

    ShuttleInventory get();

    ShuttleInventory order(Quality quality, LocalDate arrivalDate, LocalDate departureDate);
}
