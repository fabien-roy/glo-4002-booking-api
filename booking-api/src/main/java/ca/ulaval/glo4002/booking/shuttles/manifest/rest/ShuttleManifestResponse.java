package ca.ulaval.glo4002.booking.shuttles.manifest.rest;

import ca.ulaval.glo4002.booking.shuttles.trips.rest.TripDto;

import java.util.List;

public class ShuttleManifestResponse {

	private List<TripDto> arrivals;
	private List<TripDto> departures;
	
	public ShuttleManifestResponse(List<TripDto> arrivals, List<TripDto> departures) {
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
