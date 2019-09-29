package ca.ulaval.glo4002.booking.entities;

import javax.persistence.Entity;

@Entity
public class PassEntity {

    @javax.persistence.Id
	public Long id;
    public Long categoryId;
    public Long optionId;
    public String eventDate;

    public PassEntity() {
    }

    public PassEntity(Long id, Long categoryId, Long optionId, String eventDate) {
        this.id = id;
        this.categoryId = categoryId;
        this.optionId = optionId;
        this.eventDate = eventDate;
    }
}
