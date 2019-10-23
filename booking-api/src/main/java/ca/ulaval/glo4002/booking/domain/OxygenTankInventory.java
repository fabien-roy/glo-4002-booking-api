package ca.ulaval.glo4002.booking.domain;

import java.util.HashMap;
import java.util.Map;

public class OxygenTankInventory {

    private Map<Long, Long> notInUseTanks;
    private Map<Long, Long> inUseTank;

    public OxygenTankInventory() {
        this.notInUseTanks = new HashMap<>();
        this.inUseTank = new HashMap<>();
    }

    public void addTankToInventory(Long oxygenCategory, Long quantity) {

    }

}
