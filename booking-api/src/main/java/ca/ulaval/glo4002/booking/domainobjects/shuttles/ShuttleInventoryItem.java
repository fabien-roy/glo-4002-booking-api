package ca.ulaval.glo4002.booking.domainobjects.shuttles;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ShuttleInventoryItem {

    private Long id;
    private Map<LocalDate, List<Shuttle>> arrivalShuttles = new TreeMap<>();
    private Map<LocalDate, List<Shuttle>> departureShuttles = new TreeMap<>();

    public Long getId() {
        return id;
    }

    public Map<LocalDate, List<Shuttle>> getArrivalShuttles() {
        return arrivalShuttles;
    }

    public Map<LocalDate, List<Shuttle>> getDepartureShuttles() {
        return departureShuttles;
    }
}
