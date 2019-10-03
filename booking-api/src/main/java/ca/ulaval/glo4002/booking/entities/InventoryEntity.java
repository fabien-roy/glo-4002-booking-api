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
    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
    private List<InventoryItemEntity> inUseTanks = new ArrayList<>();
    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
    private List<InventoryItemEntity> notInUseTanks = new ArrayList<>();

    public InventoryEntity() {

    }

    public InventoryEntity(Long id) {
        this.id = id;
    }

    public InventoryEntity(List<InventoryItemEntity> inUseTanks, List<InventoryItemEntity> notInUseTanks) {
        this.inUseTanks = inUseTanks;
        this.notInUseTanks = notInUseTanks;
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

    public void addInUseTanks(List<InventoryItemEntity> inventoryItems) {
        this.inUseTanks.addAll(inventoryItems);
    }

    public void addNotInUseTanks(List<InventoryItemEntity> inventoryItems) {
        this.notInUseTanks.addAll(inventoryItems);
    }
}
