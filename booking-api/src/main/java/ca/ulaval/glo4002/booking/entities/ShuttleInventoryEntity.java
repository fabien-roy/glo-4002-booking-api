package ca.ulaval.glo4002.booking.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ShuttleInventories")
public class ShuttleInventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long id;
    @OneToMany(mappedBy = "inventory", cascade = CascadeType.MERGE)
    private List<ShuttleEntity> arrivalShuttles = new ArrayList<>();
    @OneToMany(mappedBy = "inventory", cascade = CascadeType.MERGE)
    private List<ShuttleEntity> departureShuttles = new ArrayList<>();

    public ShuttleInventoryEntity() {
    }

    public ShuttleInventoryEntity(Long id, List<ShuttleEntity> arrivalShuttles, List<ShuttleEntity> departureShuttles) {
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

    public void addArrivalShuttle(ShuttleEntity shuttle) {
        arrivalShuttles.add(shuttle);
    }

    public void addDepartureShuttle(ShuttleEntity shuttle) {
        departureShuttles.add(shuttle);
    }
}
