package ca.ulaval.glo4002.booking.oxygen.report.services;

import ca.ulaval.glo4002.booking.oxygen.history.domain.OxygenHistory;
import ca.ulaval.glo4002.booking.oxygen.history.infrastructure.OxygenHistoryRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.domain.OxygenInventory;
import ca.ulaval.glo4002.booking.oxygen.inventory.infrastructure.OxygenInventoryRepository;
import ca.ulaval.glo4002.booking.oxygen.report.rest.OxygenReportDto;
import ca.ulaval.glo4002.booking.oxygen.report.rest.mappers.OxygenReportMapper;

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
