package ca.ulaval.glo4002.booking.dto.shuttles;

import java.util.List;

public class TripDto {

	private String date;
	private String shuttleName;
	private List<Long> passengers;
	
	public TripDto(String date, String shuttleName, List<Long> passengers) {
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
