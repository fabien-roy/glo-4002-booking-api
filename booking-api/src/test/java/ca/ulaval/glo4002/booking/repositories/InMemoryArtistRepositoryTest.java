package ca.ulaval.glo4002.booking.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domain.artist.Availability;
import ca.ulaval.glo4002.booking.domain.artist.BookingArtist;
import ca.ulaval.glo4002.booking.domain.money.Money;

public class InMemoryArtistRepositoryTest {

	private ArtistRepository repository;
	
	@BeforeEach
	public void setUpRepository() {
		repository = new InMemoryArtistRepository();
	}
	
	@Test
	@Ignore("WIP")
	public void saveAll_shouldSaveAllArtists() {
		List<BookingArtist> artists = new ArrayList<>();
		String aName = "A name";
		Money aCost = new Money(new BigDecimal(1));
		Integer aNumberOfPeople = 3;
		String aMusicStyle = "Rap";
		Integer aPopularityRank = 1;
		List<Availability> someAvailabilities = new ArrayList<>();
		
		BookingArtist aBookingArtist = new BookingArtist(aName, aCost, aNumberOfPeople, aMusicStyle, aPopularityRank, someAvailabilities);
	}
}
