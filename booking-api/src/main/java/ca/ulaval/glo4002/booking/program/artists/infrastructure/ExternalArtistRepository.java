package ca.ulaval.glo4002.booking.program.artists.infrastructure;

import ca.ulaval.glo4002.booking.program.artists.domain.Artist;
import ca.ulaval.glo4002.booking.program.rest.exceptions.InvalidProgramException;

import javax.inject.Inject;
import java.util.List;

public class ExternalArtistRepository implements ArtistRepository {

	private final ExternalArtistClient externalArtistClient;
	private final ExternalArtistConverter externalArtistConverter;

	@Inject
	public ExternalArtistRepository(ExternalArtistClient externalArtistClient, ExternalArtistConverter externalArtistConverter) {
		this.externalArtistClient = externalArtistClient;
		this.externalArtistConverter = externalArtistConverter;
	}

	@Override
	public List<Artist> findAll() {
		List<ExternalArtist> externalArtists = externalArtistClient.getArtists();

		return externalArtistConverter.convert(externalArtists);
	}

	@Override
	public Artist findByName(String name) {
	    List<Artist> artists = findAll();

		return artists
				.stream()
				.filter(artist -> artist.getName().equals(name))
				.findAny()
				.orElseThrow(InvalidProgramException::new);
	}
}
