package ca.ulaval.glo4002.booking.program.artists;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.profits.Money;

public class InMemoryArtistRepositoryTest {

	private ArtistRepository repository;
	
	@BeforeEach
	public void setUpRepository() {
		repository = new InMemoryArtistRepository();
	}
	
	@Test
	public void saveAll_shouldSaveAllArtists() {
		List<BookingArtist> artists = new ArrayList<>();
		Integer aId = 1;
		String aName = "A name";
		Money aCost = new Money(new BigDecimal(1));
		Integer aNumberOfPeople = 3;
		String aMusicStyle = "Rap";
		Integer aPopularityRank = 1;
		List<Availability> someAvailabilities = new ArrayList<>();
		artists.add(new BookingArtist(aId, aName, aCost, aNumberOfPeople, aMusicStyle, aPopularityRank, someAvailabilities));
		
		repository.saveAll(artists);
		List<BookingArtist> returnedArtists = repository.findAll();
		
		assertEquals(artists.size(), returnedArtists.size());
	}
}
