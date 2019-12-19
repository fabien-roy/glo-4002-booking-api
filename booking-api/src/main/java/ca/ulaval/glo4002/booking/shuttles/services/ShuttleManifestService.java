package ca.ulaval.glo4002.booking.shuttles.services;

import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.program.events.domain.EventDateFactory;
import ca.ulaval.glo4002.booking.shuttles.rest.ShuttleManifestResponse;
import ca.ulaval.glo4002.booking.shuttles.rest.mappers.ShuttleManifestMapper;
import ca.ulaval.glo4002.booking.shuttles.trips.domain.Trip;
import ca.ulaval.glo4002.booking.shuttles.trips.domain.TripRepository;

import javax.inject.Inject;
import java.util.List;

public class ShuttleManifestService {

    private final TripRepository tripRepository;
    private final ShuttleManifestMapper shuttleManifestMapper;
    private final EventDateFactory eventDateFactory;

    @Inject
    public ShuttleManifestService(TripRepository tripRepository, ShuttleManifestMapper shuttleManifestMapper, EventDateFactory eventDateFactory) {
        this.tripRepository = tripRepository;
        this.shuttleManifestMapper = shuttleManifestMapper;
        this.eventDateFactory = eventDateFactory;
    }

    public ShuttleManifestResponse getTrips() {
        List<Trip> arrivals = tripRepository.getArrivals();
        List<Trip> departures = tripRepository.getDepartures();

        return shuttleManifestMapper.toResponse(arrivals, departures);
    }

    public ShuttleManifestResponse getTripsForDate(String date) {
        EventDate tripDate = eventDateFactory.create(date);

        List<Trip> arrivalsForDate = tripRepository.getArrivalsForDate(tripDate);
        List<Trip> departuresForDate = tripRepository.getDeparturesForDate(tripDate);

        return shuttleManifestMapper.toResponse(arrivalsForDate, departuresForDate);
    }
}
