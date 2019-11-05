package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.events.EventDate;
import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.mappers.ShuttleManifestMapper;
import ca.ulaval.glo4002.booking.repositories.TripRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ShuttleManifestServiceTest {

    private ShuttleManifestService service;
    private TripRepository tripRepository;
    private ShuttleManifestMapper shuttleManifestMapper;

    @BeforeEach
    void setUpService() {
        tripRepository = mock(TripRepository.class);

        shuttleManifestMapper = mock(ShuttleManifestMapper.class);

        this.service = new ShuttleManifestService(tripRepository, shuttleManifestMapper);
    }

    @Test
    void getWithDate_shouldCallMapper() {
        String aDate = EventDate.START_DATE.toString();

        service.getTripsForDate(aDate);

        verify(shuttleManifestMapper).toDto(any(), any());
    }

    @Test
    void getWithDate_shouldThrowInvalidFormatException_whenDateIsInvalid() {
        String anInvalidDate = "anInvalidDate";

        assertThrows(InvalidFormatException.class, () -> service.getTripsForDate(anInvalidDate));
    }

    @Test
    void getWithDate_shouldCallRepositoryForArrivals() {
        EventDate aDate = new EventDate(EventDate.START_DATE);

        service.getTripsForDate(aDate.toString());

        verify(tripRepository).getArrivalsForDate(aDate);
    }

    @Test
    void getWithDate_shouldCallRepositoryForDepartures() {
        EventDate aDate = new EventDate(EventDate.START_DATE);

        service.getTripsForDate(aDate.toString());

        verify(tripRepository).getDeparturesForDate(aDate);
    }
    
    @Test
    void getWithoutDate_shouldCallMapper() {
        service.getTrips();

        verify(shuttleManifestMapper).toDto(any(), any());
    }

    @Test
    void getWithoutDate_shouldCallRepositoryForArrivals() {
        service.getTrips();

        verify(tripRepository).getArrivals();
    }

    @Test
    void getWithoutDate_shouldCallRepositoryForDepartures() {
        service.getTrips();

        verify(tripRepository).getDepartures();
    }
}
