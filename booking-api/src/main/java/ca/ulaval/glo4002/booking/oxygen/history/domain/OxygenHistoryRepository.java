package ca.ulaval.glo4002.booking.oxygen.history.domain;

public interface OxygenHistoryRepository {

	OxygenHistory getHistory();

	void updateHistory(OxygenHistory inventory);
}
