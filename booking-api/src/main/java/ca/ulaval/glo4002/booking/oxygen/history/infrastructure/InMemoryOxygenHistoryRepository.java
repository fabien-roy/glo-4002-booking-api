package ca.ulaval.glo4002.booking.oxygen.history.infrastructure;

import ca.ulaval.glo4002.booking.oxygen.history.domain.OxygenHistory;

public class InMemoryOxygenHistoryRepository implements OxygenHistoryRepository {

	private OxygenHistory history;

	public InMemoryOxygenHistoryRepository() {
		this.history = new OxygenHistory();
	}

	@Override
	public OxygenHistory getHistory() {
		return history;
	}

	@Override
	public void setHistory(OxygenHistory history) {
		this.history = history;
	}
}
