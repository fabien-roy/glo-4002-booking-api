package ca.ulaval.glo4002.booking.program.artists.infrastructure;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

public class ExternalArtistClientTest {

	private static WireMockServer wiremockServer;
	// TODO : Review this test class, use ObjectMapper
	private static final String response = "[ {\n" +
			"  \"id\" : 1,\n" + 
			"  \"name\" : \"Sun 41\",\n" + 
			"  \"nbPeople\" : 5,\n" + 
			"  \"musicStyle\" : \"pop\",\n" + 
			"  \"price\" : 68000,\n" + 
			"  \"popularityRank\" : 9,\n" + 
			"  \"availabilities\" : [ {\n" + 
			"    \"availability\" : \"2050-01-20\"\n" + 
			"  }, {\n" + 
			"    \"availability\" : \"2050-07-18\"\n" + 
			"  }, {\n" + 
			"    \"availability\" : \"2050-07-21\"\n" + 
			"  }, {\n" + 
			"    \"availability\" : \"2050-07-22\"\n" + 
			"  }, {\n" + 
			"    \"availability\" : \"2050-07-23\"\n" + 
			"  }, {\n" + 
			"    \"availability\" : \"2050-08-24\"\n" + 
			"  } ]\n" + 
			"}, {\n" + 
			"  \"id\" : 2,\n" + 
			"  \"name\" : \"Black Earth Peas\",\n" + 
			"  \"nbPeople\" : 4,\n" + 
			"  \"musicStyle\" : \"pop\",\n" + 
			"  \"price\" : 200000,\n" + 
			"  \"popularityRank\" : 8,\n" + 
			"  \"availabilities\" : [ ]\n" + 
			"}, {\n" + 
			"  \"id\" : 3,\n" + 
			"  \"name\" : \"Bruno Mars\",\n" + 
			"  \"nbPeople\" : 1,\n" + 
			"  \"musicStyle\" : \"pop\",\n" + 
			"  \"price\" : 90000,\n" + 
			"  \"popularityRank\" : 10,\n" + 
			"  \"availabilities\" : [ ]\n" + 
			"}, {\n" + 
			"  \"id\" : 4,\n" + 
			"  \"name\" : \"Mumford and Suns\",\n" + 
			"  \"nbPeople\" : 4,\n" + 
			"  \"musicStyle\" : \"folk\",\n" + 
			"  \"price\" : 125000,\n" + 
			"  \"popularityRank\" : 17,\n" + 
			"  \"availabilities\" : [ ]\n" + 
			"}, {\n" + 
			"  \"id\" : 5,\n" + 
			"  \"name\" : \"Kid Rocket\",\n" + 
			"  \"nbPeople\" : 1,\n" + 
			"  \"musicStyle\" : \"pop\",\n" + 
			"  \"price\" : 350000,\n" + 
			"  \"popularityRank\" : 18,\n" + 
			"  \"availabilities\" : [ ]\n" + 
			"}, {\n" + 
			"  \"id\" : 6,\n" + 
			"  \"name\" : \"Lady Gamma\",\n" + 
			"  \"nbPeople\" : 1,\n" + 
			"  \"musicStyle\" : \"pop\",\n" + 
			"  \"price\" : 68000,\n" + 
			"  \"popularityRank\" : 7,\n" + 
			"  \"availabilities\" : [ ]\n" + 
			"}, {\n" + 
			"  \"id\" : 7,\n" + 
			"  \"name\" : \"Cyndi Dauppler\",\n" + 
			"  \"nbPeople\" : 1,\n" + 
			"  \"musicStyle\" : \"pop\",\n" + 
			"  \"price\" : 25000,\n" + 
			"  \"popularityRank\" : 14,\n" + 
			"  \"availabilities\" : [ ]\n" + 
			"}, {\n" + 
			"  \"id\" : 8,\n" + 
			"  \"name\" : \"Kelvin Harris\",\n" + 
			"  \"nbPeople\" : 1,\n" + 
			"  \"musicStyle\" : \"pop\",\n" + 
			"  \"price\" : 200000,\n" + 
			"  \"popularityRank\" : 15,\n" + 
			"  \"availabilities\" : [ ]\n" + 
			"}, {\n" + 
			"  \"id\" : 9,\n" + 
			"  \"name\" : \"Suns N’ Roses\",\n" + 
			"  \"nbPeople\" : 7,\n" + 
			"  \"musicStyle\" : \"rock\",\n" + 
			"  \"price\" : 98000,\n" + 
			"  \"popularityRank\" : 19,\n" + 
			"  \"availabilities\" : [ ]\n" + 
			"}, {\n" + 
			"  \"id\" : 10,\n" + 
			"  \"name\" : \"Eclipse Presley\",\n" + 
			"  \"nbPeople\" : 1,\n" + 
			"  \"musicStyle\" : \"rock\",\n" + 
			"  \"price\" : 321000,\n" + 
			"  \"popularityRank\" : 1,\n" + 
			"  \"availabilities\" : [ ]\n" + 
			"}, {\n" + 
			"  \"id\" : 11,\n" + 
			"  \"name\" : \"30 Seconds to Mars\",\n" + 
			"  \"nbPeople\" : 2,\n" + 
			"  \"musicStyle\" : \"rock\",\n" + 
			"  \"price\" : 124000,\n" + 
			"  \"popularityRank\" : 16,\n" + 
			"  \"availabilities\" : [ ]\n" + 
			"}, {\n" + 
			"  \"id\" : 12,\n" + 
			"  \"name\" : \"Coldray\",\n" + 
			"  \"nbPeople\" : 5,\n" + 
			"  \"musicStyle\" : \"pop\",\n" + 
			"  \"price\" : 607000,\n" + 
			"  \"popularityRank\" : 13,\n" + 
			"  \"availabilities\" : [ ]\n" + 
			"}, {\n" + 
			"  \"id\" : 13,\n" + 
			"  \"name\" : \"Megadearth\",\n" + 
			"  \"nbPeople\" : 4,\n" + 
			"  \"musicStyle\" : \"rock\",\n" + 
			"  \"price\" : 120000,\n" + 
			"  \"popularityRank\" : 12,\n" + 
			"  \"availabilities\" : [ ]\n" + 
			"}, {\n" + 
			"  \"id\" : 14,\n" + 
			"  \"name\" : \"David Glowie\",\n" + 
			"  \"nbPeople\" : 1,\n" + 
			"  \"musicStyle\" : \"rock\",\n" + 
			"  \"price\" : 390000,\n" + 
			"  \"popularityRank\" : 4,\n" + 
			"  \"availabilities\" : [ ]\n" + 
			"}, {\n" + 
			"  \"id\" : 15,\n" + 
			"  \"name\" : \"XRay Charles\",\n" + 
			"  \"nbPeople\" : 1,\n" + 
			"  \"musicStyle\" : \"jazz\",\n" + 
			"  \"price\" : 110000,\n" + 
			"  \"popularityRank\" : 5,\n" + 
			"  \"availabilities\" : [ ]\n" + 
			"}, {\n" + 
			"  \"id\" : 16,\n" + 
			"  \"name\" : \"Freddie Mercury\",\n" + 
			"  \"nbPeople\" : 1,\n" + 
			"  \"musicStyle\" : \"rock\",\n" + 
			"  \"price\" : 298000,\n" + 
			"  \"popularityRank\" : 3,\n" + 
			"  \"availabilities\" : [ ]\n" + 
			"}, {\n" + 
			"  \"id\" : 17,\n" + 
			"  \"name\" : \"Rolling Stars\",\n" + 
			"  \"nbPeople\" : 4,\n" + 
			"  \"musicStyle\" : \"rock\",\n" + 
			"  \"price\" : 550550,\n" + 
			"  \"popularityRank\" : 6,\n" + 
			"  \"availabilities\" : [ ]\n" + 
			"}, {\n" + 
			"  \"id\" : 18,\n" + 
			"  \"name\" : \"Simple Planet\",\n" + 
			"  \"nbPeople\" : 5,\n" + 
			"  \"musicStyle\" : \"pop\",\n" + 
			"  \"price\" : 200000,\n" + 
			"  \"popularityRank\" : 11,\n" + 
			"  \"availabilities\" : [ ]\n" + 
			"}, {\n" + 
			"  \"id\" : 19,\n" + 
			"  \"name\" : \"Novana\",\n" + 
			"  \"nbPeople\" : 3,\n" + 
			"  \"musicStyle\" : \"grunge\",\n" + 
			"  \"price\" : 410000,\n" + 
			"  \"popularityRank\" : 2,\n" + 
			"  \"availabilities\" : [ ]\n" + 
			"} ]";
	
	@BeforeAll
	public static void setupClient() {
		wiremockServer = new WireMockServer(8080);
		wiremockServer.start();
	}
	
	@BeforeEach
	public void endpointSetup() {
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
