package ca.ulaval.glo4002.booking.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;

@Entity(name = "Passes")
public class PassEntity extends OrderItemEntity {

    public Long categoryId;
    public Long optionId;
    public LocalDate eventDate;

    public PassEntity() {
    }

    public PassEntity(Long categoryId, Long optionId){
        this.categoryId = categoryId;
        this.optionId = optionId;
    }

    public PassEntity(Long id, Long categoryId, Long optionId) {
        this.id = id;
        this.categoryId = categoryId;
        this.optionId = optionId;
    }

    public PassEntity(Long categoryId, Long optionId, LocalDate eventDate){
        this.categoryId = categoryId;
        this.optionId = optionId;
        this.eventDate = eventDate;
    }

    public PassEntity(Long id, Long categoryId, Long optionId, LocalDate eventDate) {
        this.id = id;
        this.categoryId = categoryId;
        this.optionId = optionId;
        this.eventDate = eventDate;
    }
}
