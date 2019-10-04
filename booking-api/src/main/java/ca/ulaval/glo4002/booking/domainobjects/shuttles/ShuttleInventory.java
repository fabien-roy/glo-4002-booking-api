package ca.ulaval.glo4002.booking.domainobjects.shuttles;

import ca.ulaval.glo4002.booking.constants.ShuttleConstants;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class ShuttleInventory {

    private Long id;
    private Map<Long, ShuttleInventoryItem> arrivalShuttles = new TreeMap<>();
    private Map<Long, ShuttleInventoryItem> departureShuttles = new TreeMap<>();

    // TODO : TRANS : Make sure this is created with empty categories
    public ShuttleInventory() {
        arrivalShuttles.put(ShuttleConstants.Categories.ET_SPACESHIP_ID, new ShuttleInventoryItem());
        arrivalShuttles.put(ShuttleConstants.Categories.MILLENNIUM_FALCON_ID, new ShuttleInventoryItem());
        arrivalShuttles.put(ShuttleConstants.Categories.SPACE_X_ID, new ShuttleInventoryItem());
        departureShuttles.put(ShuttleConstants.Categories.ET_SPACESHIP_ID, new ShuttleInventoryItem());
        departureShuttles.put(ShuttleConstants.Categories.MILLENNIUM_FALCON_ID, new ShuttleInventoryItem());
        departureShuttles.put(ShuttleConstants.Categories.SPACE_X_ID, new ShuttleInventoryItem());
    }

    public ShuttleInventory(Long id, Map<Long, ShuttleInventoryItem> departureShuttles, Map<Long, ShuttleInventoryItem> arrivalShuttles) {
        this.id = id;
        this.departureShuttles = departureShuttles;
        this.arrivalShuttles = arrivalShuttles;
    }

    public Long getId() {
        return id;
    }

    public Map<Long, ShuttleInventoryItem> getArrivalShuttles() {
        return arrivalShuttles;
    }

    public Map<Long, ShuttleInventoryItem> getDepartureShuttles() {
        return departureShuttles;
    }

    public void setArrivalShuttles(Long categoryId, ShuttleInventoryItem shuttleInventoryItems) {
        arrivalShuttles.put(categoryId, shuttleInventoryItems);
    }

    public void setDepartureShuttles(Long categoryId, ShuttleInventoryItem shuttleInventoryItems) {
        departureShuttles.put(categoryId, shuttleInventoryItems);
    }

    // TODO : TRANS
    public void addArrivalShuttle(LocalDate date, Shuttle shuttle) {
        // arrivalShuttles.get(shuttle.getShuttleCategory().getId()).get(date).add(shuttle);
    }

    // TODO : TRANS
    public void addDepartureShuttle(LocalDate date, Shuttle shuttle) {
        // departureShuttles.get(shuttle.getShuttleCategory().getId()).get(date).add(shuttle);
    }
}