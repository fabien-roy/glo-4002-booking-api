package ca.ulaval.glo4002.booking.entities;

import javax.persistence.*;

@Entity(name = "InventoryItems")
public class InventoryItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
	private Long id;
    private Long categoryId;
    private Long quantityInUse;
    private Long quantityStored;
    @ManyToOne
    InventoryEntity inventory;

    public InventoryItemEntity() {

    }

    public InventoryItemEntity(Long categoryId, Long quantityInUse, Long quantityStored) {
        this.categoryId = categoryId;
        this.quantityInUse = quantityInUse;
        this.quantityStored = quantityStored;
    }

    public InventoryItemEntity(Long id, Long categoryId, Long quantityInUse, Long quantityStored) {
        this.id = id;
        this.categoryId = categoryId;
        this.quantityInUse = quantityInUse;
        this.quantityStored = quantityStored;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getQuantityInUse() {
        return quantityInUse;
    }

    public Long getQuantityStored() {
        return quantityStored;
    }
}
