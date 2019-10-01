package ca.ulaval.glo4002.booking.domainobjects.trips;

import ca.ulaval.glo4002.booking.domainobjects.shuttles.Passenger;
import ca.ulaval.glo4002.booking.domainobjects.trips.types.ArrivalTripType;

import java.time.LocalDate;
import java.util.List;

public class ArrivalTrip extends Trip {

    public ArrivalTrip(LocalDate date, List<Passenger> passengers) {
        this.date = date;
        this.passengers = passengers;
        this.type = new ArrivalTripType();
    }

	public ArrivalTrip(Long id, LocalDate date, List<Passenger> passengers) {
	    this.id = id;
		this.date = date;
		this.passengers = passengers;
		this.type = new ArrivalTripType();
	}
}
