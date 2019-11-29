package ca.ulaval.glo4002.booking.shuttles.manifest;

import ca.ulaval.glo4002.booking.program.events.EventDate;
import ca.ulaval.glo4002.booking.shuttles.trips.Trip;
import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.shuttles.trips.TripRepository;

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

    public ShuttleManifestDto getTrips() {
        List<Trip> arrivals = tripRepository.getArrivals();
        List<Trip> departures = tripRepository.getDepartures();

        return shuttleManifestMapper.toDto(arrivals, departures);
    }

    public ShuttleManifestDto getTripsForDate(String date) {
        EventDate tripDate = buildTripDate(date);

        List<Trip> arrivalsForDate = tripRepository.getArrivalsForDate(tripDate);
        List<Trip> departuresForDate = tripRepository.getDeparturesForDate(tripDate);

        return shuttleManifestMapper.toDto(arrivalsForDate, departuresForDate);
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
