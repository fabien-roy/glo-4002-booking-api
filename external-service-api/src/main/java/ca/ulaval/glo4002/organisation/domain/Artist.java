package ca.ulaval.glo4002.organisation.domain;

 import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.rest.core.annotation.RestResource;


 @Entity
 public class Artist {

     @Id
     @GeneratedValue
     private Integer id;

     @Column
     private String name;

     @Column(name = "nb_people")
     private Integer nbPeople;

     @Column(name = "music_style")
     private String musicStyle;

     @Column
     private Integer price;

     @Column(name="popularity_rank")
     private Integer popularityRank;

     @OneToMany(fetch = FetchType.LAZY, mappedBy = "artist")
     @RestResource(exported = false)
     private List<ArtistAvailability> artistAvailabilities;

     public Integer getId() {
         return id;
     }

     public String getName() {
         return name;
     }

     public Integer getNbPeople() {
         return nbPeople;
     }

     public String getMusicStyle()  {
         return musicStyle;
     }

     public Integer getPrice() {
         return price;
     }

     public Integer getPopularityRank() {return popularityRank; }

     public List<ArtistAvailability> getAvailabilities() {
         return artistAvailabilities;
     }

 }