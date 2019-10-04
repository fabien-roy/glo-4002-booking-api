package ca.ulaval.glo4002.booking.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Inventories")
public class InventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
	private Long id;
    @OneToMany(mappedBy = "inventory")
    private List<InventoryItemEntity> inUseTanks = new ArrayList<>();
    @OneToMany(mappedBy = "inventory")
    private List<InventoryItemEntity> notInUseTanks = new ArrayList<>();

    public InventoryEntity() {

    }

    public InventoryEntity(List<InventoryItemEntity> inUseTanks, List<InventoryItemEntity> notInUseTanks) {
        this.inUseTanks = inUseTanks;
        this.notInUseTanks = notInUseTanks;
    }

    public InventoryEntity(Long id, List<InventoryItemEntity> inUseTanks, List<InventoryItemEntity> notInUseTanks) {
        this.id = id;
        this.inUseTanks = inUseTanks;
        this.notInUseTanks = notInUseTanks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<InventoryItemEntity> getInUseTanks() {
        return inUseTanks;
    }

    public List<InventoryItemEntity> getNotInUseTanks() {
        return notInUseTanks;
    }

    public void clearInventoryItems() {
        inUseTanks.clear();
        notInUseTanks.clear();
    }

    public void addInUseTank(List<InventoryItemEntity> inUseTanks) {
        this.inUseTanks = inUseTanks;
    }

    public void addNotInUseTank(List<InventoryItemEntity> notInUseTanks) {
        this.notInUseTanks = notInUseTanks;
    }

    public void updateInUseTanks(List<InventoryItemEntity> inventoryItemEntities) {
        this.inUseTanks = inventoryItemEntities;
    }

    public void updateNotInUseTanks(List<InventoryItemEntity> inventoryItemEntities) {
        this.notInUseTanks = inventoryItemEntities;
    }
}
