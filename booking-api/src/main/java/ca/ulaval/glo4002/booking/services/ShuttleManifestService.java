package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.shuttles.Trip;
import ca.ulaval.glo4002.booking.dto.ShuttleManifestDto;
import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.mappers.ShuttleManifestMapper;
import ca.ulaval.glo4002.booking.repositories.TripRepository;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ShuttleManifestService {

    private final TripRepository tripRepository;
    private final ShuttleManifestMapper shuttleManifestMapper;

    @Inject
    public ShuttleManifestService(TripRepository tripRepository, ShuttleManifestMapper shuttleManifestMapper) {
        this.tripRepository = tripRepository;
        this.shuttleManifestMapper = shuttleManifestMapper;
    }

    public ShuttleManifestDto get(String date) {
        LocalDate tripDate = buildTripDate(date);

        List<Trip> arrivalsForDate = tripRepository.getArrivalsForDate(tripDate);
        List<Trip> departuresForDate = tripRepository.getDeparturesForDate(tripDate);

        return shuttleManifestMapper.toDto(arrivalsForDate, departuresForDate);
    }

    private LocalDate buildTripDate(String tripDate) {
        try {
            return LocalDate.parse(tripDate);
        } catch (Exception exception) {
            throw new InvalidFormatException();
        }
    }

    private List<Trip> getTripsForDate(List<Trip> trips, LocalDate tripDate) {
        return trips.stream().filter(trip -> trip.getTripDate().equals(tripDate)).collect(Collectors.toList());
    }
}
