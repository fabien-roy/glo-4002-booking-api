package ca.ulaval.glo4002.booking.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domain.EventDate;
import ca.ulaval.glo4002.booking.domain.trip.Trip;
import ca.ulaval.glo4002.booking.dto.TripDto;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;

class TripMapperTest {

    private List<Trip> someTrips;
    private static TripMapper subject;
    private Trip aTrip;
    private Trip anotherTrip;
    private static LocalDate aTripDate;
    private static LocalDate anotherTripDate;
    private static ShuttleCategories aShuttleCategory;
    
    @BeforeAll
    public static void setUpSuject() {
    	subject = new TripMapper();
    	aTripDate = EventDate.START_DATE;
    	anotherTripDate = EventDate.START_DATE.plusDays(1);
    	aShuttleCategory = ShuttleCategories.ET_SPACESHIP;
    }
    
    @BeforeEach
    public void testSetUp() {
    	aTrip = mock(Trip.class);
    	when(aTrip.getTripDate()).thenReturn(aTripDate);
    	when(aTrip.getShuttleCategory()).thenReturn(aShuttleCategory);
    	anotherTrip = mock(Trip.class);
    	when(anotherTrip.getTripDate()).thenReturn(anotherTripDate);
    	when(anotherTrip.getShuttleCategory()).thenReturn(aShuttleCategory);
    	someTrips = new ArrayList<>();
    }
    
    @Test
    public void toDto_shouldReturnEmptyList_whenListHasNoTrips() {
    	List<TripDto> tripDtos = subject.toDto(someTrips);
    	
    	assertEquals(0, tripDtos.size());
    }
    
    @Test
    public void toDto_shouldBuildCorrectDto_whenListHasOneTrip() {
    	someTrips.add(aTrip);
    	List<TripDto> tripDtos = subject.toDto(someTrips);
    	
    	assertEquals(someTrips.size(), tripDtos.size());
    	assertEquals(aTrip.getTripDate().toString(), tripDtos.get(0).getTripDate());
    	assertEquals(aTrip.getShuttleCategory().toString(), aShuttleCategory.toString());
    }
    
    @Test
    public void toDto_shouldBuildCorrectDtos_whenListHasManyTrips() {
    	someTrips.add(aTrip);
    	someTrips.add(anotherTrip);
    	
    	List<TripDto> tripDtos = subject.toDto(someTrips);
    	
    	assertEquals(someTrips.size(), tripDtos.size());
    	assertEquals(aTrip.getTripDate().toString(), tripDtos.get(0).getTripDate());
    	assertEquals(aTrip.getShuttleCategory().toString(), aShuttleCategory.toString());
    	assertEquals(anotherTrip.getTripDate().toString(), tripDtos.get(1).getTripDate());
    	assertEquals(anotherTrip.getShuttleCategory().toString(), aShuttleCategory.toString());
    }
}