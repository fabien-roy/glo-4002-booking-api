package ca.ulaval.glo4002.booking.clients;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import ca.ulaval.glo4002.booking.domain.artist.ExternalArtist;

public class ArtistClient {
	
	private static final String EXTERNAL_SERVICE_URL = "http://localhost:8080/artists";
	
	public List<ExternalArtist> getArtists() {
		
		Client restClient = ClientBuilder.newClient();
	
		return restClient
			    .target(EXTERNAL_SERVICE_URL)
			    .request(MediaType.APPLICATION_JSON)
			    .get(new GenericType<List<ExternalArtist>> () {});
	}
}
