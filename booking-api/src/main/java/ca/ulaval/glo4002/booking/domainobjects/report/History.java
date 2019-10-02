package ca.ulaval.glo4002.booking.domainobjects.report;

import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class History {

    private Map<LocalDate, List<OxygenTank>> producedOxygenTanks;
    private Map<LocalDate, List<OxygenTank>> requestedOxygenTanks;

    public History(Map<LocalDate, List<OxygenTank>> producedOxygenTanks, Map<LocalDate, List<OxygenTank>> requestedOxygenTanks) {
        this.producedOxygenTanks = producedOxygenTanks;
        this.requestedOxygenTanks = requestedOxygenTanks;
    }

    public Map<LocalDate, List<OxygenTank>> getProducedOxygenTanks() {
        return producedOxygenTanks;
    }

    public Map<LocalDate, List<OxygenTank>> getRequestedOxygenTanks() {
        return requestedOxygenTanks;
    }
}
