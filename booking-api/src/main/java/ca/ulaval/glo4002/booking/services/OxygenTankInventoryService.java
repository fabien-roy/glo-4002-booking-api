package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.factories.OxygenTankFactory;
import ca.ulaval.glo4002.booking.repositories.OxygenTankInventoryRepository;

public class OxygenTankInventoryService {
	private final OxygenTankInventoryRepository repository;
	private final OxygenTankFactory factory;

	public OxygenTankInventoryService(OxygenTankInventoryRepository repository, OxygenTankFactory factory) {
		this.repository = repository;
		this.factory = factory;
	}

	// TODO buildOxygenTank
}
