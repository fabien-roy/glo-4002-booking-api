package ca.ulaval.glo4002.booking.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// TODO : OXY : Pretty sure this is useless
@Entity(name = "Inventories")
public class InventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public List<InventoryItemEntity> getInventoryItems() {
        return inventoryItems;
    }
}
