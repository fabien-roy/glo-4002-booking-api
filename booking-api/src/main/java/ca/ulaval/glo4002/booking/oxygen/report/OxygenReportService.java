package ca.ulaval.glo4002.booking.oxygen.report;

import ca.ulaval.glo4002.booking.oxygen.history.OxygenHistory;
import ca.ulaval.glo4002.booking.oxygen.history.OxygenHistoryRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventory;
import ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventoryRepository;

import javax.inject.Inject;

public class OxygenReportService {

    private final OxygenInventoryRepository inventoryRepository;
	private final OxygenHistoryRepository historyRepository;
	private final OxygenReportMapper mapper;

	@Inject
	public OxygenReportService(OxygenInventoryRepository inventoryRepository, OxygenHistoryRepository historyRepository, OxygenReportMapper mapper) {
	    this.inventoryRepository = inventoryRepository;
		this.historyRepository = historyRepository;
		this.mapper = mapper;
	}

	public OxygenReportDto getOxygenReport() {
		OxygenInventory inventory = inventoryRepository.getInventory();
		OxygenHistory history = historyRepository.getHistory();

		return mapper.toDto(inventory, history);
	}
}
