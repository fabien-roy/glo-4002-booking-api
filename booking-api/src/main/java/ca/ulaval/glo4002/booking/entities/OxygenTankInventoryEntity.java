package ca.ulaval.glo4002.booking.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Inventories")
public class OxygenTankInventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
	private Long id;
    @OneToMany(mappedBy = "inventory", cascade = CascadeType.MERGE)
    private List<OxygenTankEntity> notInUseTanks = new ArrayList<>();
    @OneToMany(mappedBy = "inventory", cascade = CascadeType.MERGE)
    private List<OxygenTankEntity> inUseTanks = new ArrayList<>();

    public OxygenTankInventoryEntity() {

    }

    public OxygenTankInventoryEntity(Long id, List<OxygenTankEntity> inUseTanks, List<OxygenTankEntity> notInUseTanks) {
        this.id = id;
        this.inUseTanks = inUseTanks;
        this.notInUseTanks = notInUseTanks;
    }

    public OxygenTankInventoryEntity(List<OxygenTankEntity> inUseTanks, List<OxygenTankEntity> notInUseTanks) {
        this.inUseTanks = inUseTanks;
        this.notInUseTanks = notInUseTanks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OxygenTankEntity> getInUseTanks() {
        return inUseTanks;
    }

    public List<OxygenTankEntity> getNotInUseTanks() {
        return notInUseTanks;
    }

    public void setInUseTanks(List<OxygenTankEntity> inUseTanks) {
        this.inUseTanks = inUseTanks;
    }

    public void setNotInUseTanks(List<OxygenTankEntity> notInUseTanks) {
        this.notInUseTanks = notInUseTanks;
    }

    public void addInUseTank(OxygenTankEntity oxygenTank) {
        inUseTanks.add(oxygenTank);
    }
}
