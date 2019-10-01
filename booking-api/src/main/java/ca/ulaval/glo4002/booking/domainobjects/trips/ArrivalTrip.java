package ca.ulaval.glo4002.booking.domainobjects.trips;

import java.time.LocalDate;
import java.util.List;

import ca.ulaval.glo4002.booking.domainobjects.shuttles.Passenger;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.domainobjects.trips.types.ArrivalTripType;

public class ArrivalTrip extends Trip {

    public ArrivalTrip(LocalDate date, List<Passenger> passengers, Shuttle shuttle) {
        this.date = date;
        this.passengers = passengers;
        this.type = new ArrivalTripType();
        this.shuttle = shuttle;
    }

	public ArrivalTrip(Long id, LocalDate date, List<Passenger> passengers, Shuttle shuttle) {
	    this.id = id;
		this.date = date;
		this.passengers = passengers;
		this.type = new ArrivalTripType();
		this.shuttle = shuttle;
	}
}
