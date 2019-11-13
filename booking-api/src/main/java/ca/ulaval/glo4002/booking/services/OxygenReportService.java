package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenHistory;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenInventory;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenReportDto;
import ca.ulaval.glo4002.booking.mappers.OxygenReportMapper;
import ca.ulaval.glo4002.booking.repositories.OxygenInventoryRepository;

import javax.inject.Inject;

public class OxygenReportService {

    private final OxygenInventoryRepository repository;
    private final OxygenReportMapper mapper;

	@Inject
	public OxygenReportService(OxygenInventoryRepository repository, OxygenReportMapper mapper) {
	    this.repository = repository;
	    this.mapper = mapper;
	}

	// TODO : OxygenReportService.getOxygenReport
	public OxygenReportDto getOxygenReport() {
		/*
		OxygenInventory inventory = repository.getInventory();

		return mapper.toDto(inventory);
		*/

		return null;
	}
}
