package ca.ulaval.glo4002.booking.dto;

import java.util.List;

public class ShuttleManifestDto {

	private List<TripDto> arrivals;
	private List<TripDto> departures;
	
	public ShuttleManifestDto(List<TripDto> arrivals, List<TripDto> departures) {
		this.arrivals = arrivals;
		this.departures = departures;
	}

	public List<TripDto> getArrivals() {
		return arrivals;
	}

	public List<TripDto> getDepartures() {
		return departures;
	}
}
