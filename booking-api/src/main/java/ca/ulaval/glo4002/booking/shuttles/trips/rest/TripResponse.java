package ca.ulaval.glo4002.booking.shuttles.trips.rest;

import java.util.List;

public class TripResponse {

	private String date;
	private String shuttleName;
	private List<Long> passengers;
	
	public TripResponse(String date, String shuttleName, List<Long> passengers) {
		this.date = date;
		this.shuttleName = shuttleName;
		this.passengers = passengers;
	}
	
	public String getDate() {
		return date;
	}

	public String getShuttleName() {
		return shuttleName;
	}

	public List<Long> getPassengers() {
		return passengers;
	}
}
