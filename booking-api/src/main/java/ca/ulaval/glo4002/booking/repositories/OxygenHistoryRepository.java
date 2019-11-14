package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenHistory;

public interface OxygenHistoryRepository {

	OxygenHistory getHistory();

	void setHistory(OxygenHistory inventory);
}
