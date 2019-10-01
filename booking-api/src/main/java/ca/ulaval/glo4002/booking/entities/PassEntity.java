package ca.ulaval.glo4002.booking.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity(name = "Passes")
public class PassEntity extends OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    private Long categoryId;
    private Long optionId;
    private LocalDate eventDate;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getOptionId() {
        return optionId;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }
}
