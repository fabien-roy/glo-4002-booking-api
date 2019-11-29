package ca.ulaval.glo4002.booking.oxygen.history.infrastructure;

import ca.ulaval.glo4002.booking.oxygen.history.domain.OxygenHistory;

public interface OxygenHistoryRepository {

	OxygenHistory getHistory();

	void setHistory(OxygenHistory inventory);
}
