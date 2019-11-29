package ca.ulaval.glo4002.booking.shuttles.manifest.rest;

import ca.ulaval.glo4002.booking.shuttles.trips.rest.TripResponse;

import java.util.List;

public class ShuttleManifestResponse {

	private List<TripResponse> arrivals;
	private List<TripResponse> departures;
	
	public ShuttleManifestResponse(List<TripResponse> arrivals, List<TripResponse> departures) {
		this.arrivals = arrivals;
		this.departures = departures;
	}

	public List<TripResponse> getArrivals() {
		return arrivals;
	}

	public List<TripResponse> getDepartures() {
		return departures;
	}
}
