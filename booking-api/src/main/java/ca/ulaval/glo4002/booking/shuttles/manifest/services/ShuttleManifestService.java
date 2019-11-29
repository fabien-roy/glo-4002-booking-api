package ca.ulaval.glo4002.booking.shuttles.manifest.services;

import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.shuttles.manifest.rest.ShuttleManifestResponse;
import ca.ulaval.glo4002.booking.shuttles.manifest.rest.mappers.ShuttleManifestMapper;
import ca.ulaval.glo4002.booking.shuttles.trips.domain.Trip;
import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.shuttles.trips.infrastructure.TripRepository;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

public class ShuttleManifestService {

    private final TripRepository tripRepository;
    private final ShuttleManifestMapper shuttleManifestMapper;

    @Inject
    public ShuttleManifestService(TripRepository tripRepository, ShuttleManifestMapper shuttleManifestMapper) {
        this.tripRepository = tripRepository;
        this.shuttleManifestMapper = shuttleManifestMapper;
    }

    public ShuttleManifestResponse getTrips() {
        List<Trip> arrivals = tripRepository.getArrivals();
        List<Trip> departures = tripRepository.getDepartures();

        return shuttleManifestMapper.toResponse(arrivals, departures);
    }

    public ShuttleManifestResponse getTripsForDate(String date) {
        EventDate tripDate = buildTripDate(date);

        List<Trip> arrivalsForDate = tripRepository.getArrivalsForDate(tripDate);
        List<Trip> departuresForDate = tripRepository.getDeparturesForDate(tripDate);

        return shuttleManifestMapper.toResponse(arrivalsForDate, departuresForDate);
    }

    private EventDate buildTripDate(String tripDate) {
        LocalDate parsedTripDate;

        try {
            parsedTripDate = LocalDate.parse(tripDate);
        } catch (Exception exception) {
            throw new InvalidFormatException();
        }

        return new EventDate(parsedTripDate);
    }
}
