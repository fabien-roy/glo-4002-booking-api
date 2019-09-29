package ca.ulaval.glo4002.booking.entities;

import javax.persistence.*;

@Entity
public class PassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
    public Long categoryId;
    public Long optionId;
    public String eventDate;
    @OneToOne(mappedBy = "pass")
    public OrderItemEntity orderItem;

    public PassEntity() {
    }

    public PassEntity(Long id, Long categoryId, Long optionId, String eventDate) {
        this.id = id;
        this.categoryId = categoryId;
        this.optionId = optionId;
        this.eventDate = eventDate;
    }
}
