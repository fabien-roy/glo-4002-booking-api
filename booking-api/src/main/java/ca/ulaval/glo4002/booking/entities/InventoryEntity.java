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
    private List<InventoryItemEntity> inventoryItems = new ArrayList<>();

    public InventoryEntity() {

    }

    public InventoryEntity(Long id) {
        this.id = id;
    }

    public InventoryEntity(List<InventoryItemEntity> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<InventoryItemEntity> getInventoryItems() {
        return inventoryItems;
    }

    public void clearInventoryItems() {
        inventoryItems.clear();
    }

    public void addInventoryItems(List<InventoryItemEntity> inventoryItems) {
        this.inventoryItems.addAll(inventoryItems);
    }
}
