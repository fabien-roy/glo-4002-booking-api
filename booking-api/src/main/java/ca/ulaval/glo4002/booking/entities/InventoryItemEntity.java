package ca.ulaval.glo4002.booking.entities;

import javax.persistence.*;

@Entity(name = "InventoryItems")
public class InventoryItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="inventoryItemId", nullable = false)
    private InventoryEntity inventory;
    private Long oxygenCategoryId;
    private Long quantity;

    public InventoryItemEntity() {

    }

    public InventoryItemEntity(Long oxygenCategoryId, Long quantity) {
        this.oxygenCategoryId = oxygenCategoryId;
        this.quantity = quantity;
    }

    public Long getOxygenCategoryId() {
        return oxygenCategoryId;
    }

    public void setOxygenCategoryId(Long oxygenCategoryId) {
        this.oxygenCategoryId = oxygenCategoryId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Long getId() {
        return id;
    }
}
