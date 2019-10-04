package ca.ulaval.glo4002.booking.domainobjects.shuttles;

import java.util.ArrayList;
import java.util.List;

public class ShuttleInventory {

    private Long id;
    private List<Shuttle> arrivalShuttles = new ArrayList<>();
    private List<Shuttle> departureShuttles = new ArrayList<>();

    public ShuttleInventory() {
    }

    public ShuttleInventory(Long id, List<Shuttle> departureShuttles, List<Shuttle> arrivalShuttles) {
        this.id = id;
        this.departureShuttles = departureShuttles;
        this.arrivalShuttles = arrivalShuttles;
    }

    public Long getId() {
        return id;
    }

    public List<Shuttle> getArrivalShuttles() {
        return arrivalShuttles;
    }

    public List<Shuttle> getDepartureShuttles() {
        return departureShuttles;
    }

    public void addArrivalShuttle(Shuttle shuttle) {
        arrivalShuttles.add(shuttle);
    }

    public void addDepartureShuttle(Shuttle shuttle) {
        departureShuttles.add(shuttle);
    }
}