package ca.ulaval.glo4002.booking.program.artists.infrastructure;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.program.artists.domain.Artist;
import ca.ulaval.glo4002.booking.program.artists.domain.ArtistConverter;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExternalArtistConverterTest {

	private ExternalArtistConverter artistConverter;
	private FestivalConfiguration festivalConfiguration;

	@BeforeEach
	public void setUpConverter() {
		artistConverter = new ExternalArtistConverter(festivalConfiguration);
	}

	@BeforeEach
	void setUpConfiguration() {
		festivalConfiguration = mock(FestivalConfiguration.class);

		when(festivalConfiguration.getStartEventDate()).thenReturn(FestivalConfiguration.getDefaultStartEventDate());
		when(festivalConfiguration.getEndEventDate()).thenReturn(FestivalConfiguration.getDefaultEndEventDate());
	}

	// TODO
}
