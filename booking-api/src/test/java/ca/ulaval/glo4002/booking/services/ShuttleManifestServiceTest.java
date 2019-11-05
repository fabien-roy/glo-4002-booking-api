package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.EventDate;
import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.mappers.ShuttleManifestMapper;
import ca.ulaval.glo4002.booking.repositories.TripRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
    void get_shouldCallMapper() {
        String aDate = EventDate.START_DATE.toString();

        service.get(aDate);

        verify(shuttleManifestMapper).toDto(any(), any());
    }

    @Test
    void get_shouldThrowInvalidFormatException_whenDateIsInvalid() {
        String anInvalidDate = "anInvalidDate";

        assertThrows(InvalidFormatException.class, () -> service.get(anInvalidDate));
    }

    @Test
    void get_shouldCallRepositoryForArrivals() {
        LocalDate aDate = EventDate.START_DATE;

        service.get(aDate.toString());

        verify(tripRepository).getArrivalsForDate(aDate);
    }

    @Test
    void get_shouldCallRepositoryForDepartures() {
        LocalDate aDate = EventDate.START_DATE;

        service.get(aDate.toString());

        verify(tripRepository).getDeparturesForDate(aDate);
    }
}
