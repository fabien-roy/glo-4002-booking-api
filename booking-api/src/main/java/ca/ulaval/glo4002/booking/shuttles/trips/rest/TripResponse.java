package ca.ulaval.glo4002.booking.shuttles.trips.rest;

public class TripResponse {

	private String date;
	private String shuttleName;
	private long[] passengers;
	
	public TripResponse(String date, String shuttleName, long[] passengers) {
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

	public long[] getPassengers() {
		return passengers;
	}
}
