package ca.ulaval.glo4002.organisation.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
public class ArtistAvailability {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id", nullable = false)
    @RestResource(exported = false)
    private Artist artist;

    @Column
    private String availability;

    public String getAvailability() {
        return availability;
    }

 }