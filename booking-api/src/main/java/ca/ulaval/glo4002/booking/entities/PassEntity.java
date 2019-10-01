package ca.ulaval.glo4002.booking.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Passes")
public class PassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private Long categoryId;
    private Long optionId;
    private LocalDate eventDate;
    // @OneToOne(mappedBy = "pass")
    // public OrderItemEntity orderItem;

    public PassEntity() {
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
