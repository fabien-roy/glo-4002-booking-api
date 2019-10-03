package ca.ulaval.glo4002.booking.domainobjects.report;

import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class History {

    private Map<LocalDate, List<OxygenTank>> requestedOxygenTanks;
    private Map<LocalDate, List<OxygenTank>> producedOxygenTanks;

    public History(Map<LocalDate, List<OxygenTank>> requestedOxygenTanks, Map<LocalDate, List<OxygenTank>> producedOxygenTanks) {
        this.producedOxygenTanks = producedOxygenTanks;
        this.requestedOxygenTanks = requestedOxygenTanks;
    }

    public Map<LocalDate, List<OxygenTank>> getRequestedOxygenTanks() {
        return requestedOxygenTanks;
    }

    public Map<LocalDate, List<OxygenTank>> getProducedOxygenTanks() {
        return producedOxygenTanks;
    }
}
