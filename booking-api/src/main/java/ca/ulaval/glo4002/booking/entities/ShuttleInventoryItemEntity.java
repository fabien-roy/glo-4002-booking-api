package ca.ulaval.glo4002.booking.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ShuttleInventories")
public class ShuttleInventoryItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long id;
    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
    private List<ShuttleEntity> arrivalShuttles = new ArrayList<>();
    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
    private List<ShuttleEntity> departureShuttles = new ArrayList<>();

    public ShuttleInventoryItemEntity(Long id, List<ShuttleEntity> arrivalShuttles, List<ShuttleEntity> departureShuttles) {
        this.id = id;
        this.arrivalShuttles = arrivalShuttles;
        this.departureShuttles = departureShuttles;
    }

    public List<ShuttleEntity> getArrivalShuttles() {
        return arrivalShuttles;
    }

    public List<ShuttleEntity> getDepartureShuttles() {
        return departureShuttles;
    }
}
