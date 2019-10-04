package ca.ulaval.glo4002.booking.domainobjects.oxygen;

import java.util.ArrayList;
import java.util.List;

public class OxygenTankInventory {

    private Long id;
    private List<OxygenTank> notInUseTanks = new ArrayList<>();
    private List<OxygenTank> inUseTanks = new ArrayList<>();

    public OxygenTankInventory() {

    }

    public OxygenTankInventory(Long id, List<OxygenTank> inUseTanks, List<OxygenTank> notInUseTanks) {
        this.id = id;
        this.notInUseTanks = notInUseTanks;
        this.inUseTanks = inUseTanks;
    }

    public OxygenTankInventory(List<OxygenTank> notInUseTanks, List<OxygenTank> inUseTanks) {
        this.notInUseTanks = notInUseTanks;
        this.inUseTanks = inUseTanks;
    }

    public Long getId() {
        return id;
    }

    public List<OxygenTank> getNotInUseTanks() {
        return notInUseTanks;
    }

    public void setNotInUseTanks(List<OxygenTank> notInUseTanks) {
        this.notInUseTanks = notInUseTanks;
    }

    public List<OxygenTank> getInUseTanks() {
        return inUseTanks;
    }

    public void setInUseTanks(List<OxygenTank> inUseTanks) {
        this.inUseTanks = inUseTanks;
    }
}