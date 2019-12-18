package ca.ulaval.glo4002.booking.oxygen.history.domain;

import ca.ulaval.glo4002.booking.oxygen.history.domain.OxygenHistory;

public interface OxygenHistoryRepository {

	OxygenHistory getHistory();

	void updateHistory(OxygenHistory inventory);
}
