package ca.ulaval.glo4002.booking.program.artists.infrastructure;

import ca.ulaval.glo4002.booking.program.artists.domain.Artist;

import java.util.ArrayList;
import java.util.List;

// TODO : Remove outdated InMemoryArtistRepository
public class InMemoryArtistRepository implements ArtistRepository {
	
	private List<Artist> artists;
	
	public InMemoryArtistRepository() {
		artists = new ArrayList<>();
	}

	public void saveAll(List<Artist> artists) {
		List<String> artistNames = new ArrayList<>();
		this.artists.stream().map(Artist::getName).forEach(artistNames::add);

		for(Artist artist : artists) {
			if (!artistNames.contains(artist.getName())) {
				this.artists.add(artist);
			}
		}
	}

	@Override
	public Artist findByName(String name) {
		return null;
	}

	@Override
	public List<Artist> findAll() {
		return artists;
	}
}
