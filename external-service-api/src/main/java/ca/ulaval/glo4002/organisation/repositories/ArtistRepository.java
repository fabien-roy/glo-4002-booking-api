package ca.ulaval.glo4002.organisation.repositories;

import java.util.List;
import org.springframework.data.repository.query.Param;
import ca.ulaval.glo4002.organisation.domain.Artist;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RestResource;

public interface ArtistRepository extends Repository<Artist, Integer> {
  @RestResource(exported = false)
  Artist findOneById(@Param("id") Integer id);

  @RestResource(exported = false)
  List<Artist> findAll();

  @RestResource(exported = false)
  Artist save(Artist artist);
}