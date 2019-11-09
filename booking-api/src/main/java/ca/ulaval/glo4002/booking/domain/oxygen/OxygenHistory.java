package ca.ulaval.glo4002.booking.domain.oxygen;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OxygenHistory {

	private Map<LocalDate, List<OxygenTank>> requestedOxygenTanks;
	private Map<LocalDate, List<OxygenTank>> producedOxygenTanks;

	public OxygenHistory() {
		this.requestedOxygenTanks = new HashMap<>();
		this.producedOxygenTanks = new HashMap<>();
	}

	public OxygenHistory(Map<LocalDate, List<OxygenTank>> requestedOxygenTanks, Map<LocalDate, List<OxygenTank>> producedOxygenTanks) {
		this.requestedOxygenTanks = requestedOxygenTanks;
		this.producedOxygenTanks = producedOxygenTanks;
	}

	public Map<LocalDate, List<OxygenTank>> getRequestedOxygenTanks() {
		return requestedOxygenTanks;
	}

	public Map<LocalDate, List<OxygenTank>> getProducedOxygenTanks() {
		return producedOxygenTanks;
	}

	public List<OxygenTank> getRequestedOxygenTanksForDate(LocalDate date) {
		return requestedOxygenTanks.get(date);
	}

	public List<OxygenTank> getProducedOxygenTanksForDate(LocalDate date) {
		return producedOxygenTanks.get(date);
	}

	public void addRequestedTankToHistory(LocalDate date, List<OxygenTank> oxygenTanks) {
		requestedOxygenTanks.put(date, oxygenTanks);
	}

	public void addProducedTankToHistory(LocalDate date, List<OxygenTank> oxygenTanks) {
		producedOxygenTanks.put(date, oxygenTanks);
	}
}