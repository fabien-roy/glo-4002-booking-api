package ca.ulaval.glo4002.booking.program.artists.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ExternalArtistClientTest {

	private static WireMockServer wiremockServer;

	@BeforeAll
	public static void setupClient() {
		wiremockServer = new WireMockServer(8080);
		wiremockServer.start();
	}
	
	@BeforeEach
	public void endpointSetup() throws JsonProcessingException {
		ExternalArtist externalArtist = new ExternalArtist();
		ObjectMapper objectMapper = new ObjectMapper();
		String response = objectMapper.writeValueAsString(Collections.singleton(externalArtist));

		stubFor(get(urlEqualTo("/artists")).
				willReturn(WireMock.aResponse().withHeader("Content-Type", "application/json").
						withBody(response)));
	}

	@AfterAll
	public static void stopServer() {
		wiremockServer.stop();
	}

	@Test
	public void getArtists_returnsAllArtists() {
		List<ExternalArtist> externalArtists = new ExternalArtistClient().getArtists();

		assertFalse(externalArtists.isEmpty());
	}
}
