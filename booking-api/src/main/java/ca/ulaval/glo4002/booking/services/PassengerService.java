package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.shuttles.Passenger;
import ca.ulaval.glo4002.booking.entities.ShuttleEntity;

public interface PassengerService {

    Passenger order(ShuttleEntity shuttle);
}
