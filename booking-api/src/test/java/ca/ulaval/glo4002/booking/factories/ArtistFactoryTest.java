package ca.ulaval.glo4002.booking.factories;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import ca.ulaval.glo4002.booking.factories.ArtistFactory;
import ca.ulaval.glo4002.organisation.domain.Artist;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.organisation.repositories.ArtistRepository;

import java.util.ArrayList;
import java.util.List;

public class ArtistFactoryTest {

	private static ArtistFactory artistFactory;
	
	@BeforeEach
	public static void converterSetUp() {
		artistFactory = new ArtistFactory();
	}

	// TODO : ArtistFactory tests
}
