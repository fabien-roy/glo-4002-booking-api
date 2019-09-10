package ca.ulaval.glo4002.organisation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ca.ulaval.glo4002.organisation.domain.Artist;
import ca.ulaval.glo4002.organisation.repositories.ArtistRepository;

@RepositoryRestController
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistRepository repository;

    @Autowired
    public ArtistController(ArtistRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public @ResponseBody Artist getArtistById(@PathVariable("id") Integer id) {
        return repository.findOneById(id);
    }

    @GetMapping("")
    public @ResponseBody List<Artist> getArtists() {
        return repository.findAll();
    }

}
