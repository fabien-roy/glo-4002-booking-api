package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenHistory;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenInventory;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenReportDto;
import ca.ulaval.glo4002.booking.mappers.OxygenReportMapper;
import ca.ulaval.glo4002.booking.repositories.OxygenHistoryRepository;
import ca.ulaval.glo4002.booking.repositories.OxygenInventoryRepository;

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
