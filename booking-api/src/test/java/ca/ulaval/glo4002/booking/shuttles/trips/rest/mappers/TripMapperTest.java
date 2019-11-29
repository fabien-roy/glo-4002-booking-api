package ca.ulaval.glo4002.booking.shuttles.trips.rest.mappers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.shuttles.trips.domain.Trip;
import ca.ulaval.glo4002.booking.shuttles.trips.rest.TripResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.shuttles.domain.ShuttleCategories;

class TripMapperTest {

	private List<Trip> someTrips;
	private static TripMapper mapper;
	private Trip aTrip;
	private Trip anotherTrip;
	private static EventDate aTripDate;
	private static EventDate anotherTripDate;
	private static ShuttleCategories aShuttleCategory;

	@BeforeAll
	public static void setUpMapper() {
		mapper = new TripMapper();
		aTripDate = EventDate.getDefaultStartEventDate();
		anotherTripDate = EventDate.getDefaultStartEventDate().plusDays(1);
		aShuttleCategory = ShuttleCategories.ET_SPACESHIP;
	}

	@BeforeEach
	public void setUpTrips() {
		aTrip = mock(Trip.class);
		when(aTrip.getTripDate()).thenReturn(aTripDate);
		when(aTrip.getShuttleCategory()).thenReturn(aShuttleCategory);

		anotherTrip = mock(Trip.class);
		when(anotherTrip.getTripDate()).thenReturn(anotherTripDate);
		when(anotherTrip.getShuttleCategory()).thenReturn(aShuttleCategory);

		someTrips = new ArrayList<>();
	}

	@Test
	public void toResponse_shouldReturnEmptyList_whenListHasNoTrips() {
		List<TripResponse> tripResponses = mapper.toResponse(someTrips);

		assertEquals(0, tripResponses.size());
	}

	@Test
	public void toResponse_shouldBuildCorrectResponse_whenListHasOneTrip() {
		someTrips.add(aTrip);

		List<TripResponse> tripResponses = mapper.toResponse(someTrips);

		assertEquals(someTrips.size(), tripResponses.size());
		assertEquals(aTrip.getTripDate().toString(), tripResponses.get(0).getDate());
		assertEquals(aTrip.getShuttleCategory().toString(), aShuttleCategory.toString());
	}

	@Test
	public void toResponse_shouldBuildCorrectResponses_whenListHasManyTrips() {
		someTrips.add(aTrip);
		someTrips.add(anotherTrip);

		List<TripResponse> tripResponses = mapper.toResponse(someTrips);

		assertEquals(someTrips.size(), tripResponses.size());
		assertEquals(aTrip.getTripDate().toString(), tripResponses.get(0).getDate());
		assertEquals(aTrip.getShuttleCategory().toString(), aShuttleCategory.toString());
		assertEquals(anotherTrip.getTripDate().toString(), tripResponses.get(1).getDate());
		assertEquals(anotherTrip.getShuttleCategory().toString(), aShuttleCategory.toString());
	}
}