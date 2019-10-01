package ca.ulaval.glo4002.booking.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domainobjects.shuttles.ShuttleManifest;
import ca.ulaval.glo4002.booking.domainobjects.trips.ArrivalTrip;
import ca.ulaval.glo4002.booking.domainobjects.trips.DepartureTrip;

public class ShuttleManifestServiceTest {

    private ShuttleManifestServiceImpl subject;
    private ShuttleManifestServiceContext context;

    @BeforeEach
    public void setUp() {
        context = new ShuttleManifestServiceContext();
        subject = new ShuttleManifestServiceImpl(context.repository);
    }

    @Test
    public void findByDate_shouldReturnCorrectShuttleManifest() {
        ShuttleManifest shuttleManifest = subject.findByDate(ShuttleManifestServiceContext.A_DATE);

        assertEquals(2, shuttleManifest.getDepartures().size());
        assertEquals(1, shuttleManifest.getArrivals().size());
        assertTrue(shuttleManifest.getDepartures().stream().allMatch(departureTrip -> departureTrip.getDate().equals(ShuttleManifestServiceContext.A_DATE)));
        assertTrue(shuttleManifest.getArrivals().stream().allMatch(arrivalTrip -> arrivalTrip.getDate().equals(ShuttleManifestServiceContext.A_DATE)));
        assertFalse(shuttleManifest.getDepartures().stream().anyMatch(departureTrip -> departureTrip.getDate().equals(ShuttleManifestServiceContext.ANOTHER_DATE)));
        assertFalse(shuttleManifest.getArrivals().stream().anyMatch(arrivalTrip -> arrivalTrip.getDate().equals(ShuttleManifestServiceContext.ANOTHER_DATE)));
    }
    
    @Test
    public void findAll_shouldReturnAllTripsInManifest() {
    	ShuttleManifest shuttleManifest = subject.findAll();	
    	
    	assertEquals(2, shuttleManifest.getDepartures().size());
    	assertEquals(2, shuttleManifest.getArrivals().size());
    	}
}
