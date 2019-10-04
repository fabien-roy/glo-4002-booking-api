package ca.ulaval.glo4002.booking.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "Passes")
public class PassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    private Long categoryId;
    private Long optionId;
    private LocalDate eventDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="orderId", nullable = false)
    private OrderEntity order;

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

    public void setOrder(OrderEntity order) {
        this.order = order;
    }
}
