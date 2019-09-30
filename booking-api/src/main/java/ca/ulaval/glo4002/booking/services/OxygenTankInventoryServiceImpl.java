package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainObjects.oxygen.OxygenTankInventory;
import ca.ulaval.glo4002.booking.exceptions.UnusedMethodException;

public class OxygenTankInventoryServiceImpl implements OxygenTankInventoryService {
	
	
	@Override
	public OxygenTankInventory findById(Long id) {
		throw new UnusedMethodException();
	}

	@Override
	public Iterable<OxygenTankInventory> findAll() {
		throw new UnusedMethodException();
	}

	@Override
	public OxygenTankInventory save(OxygenTankInventory object) {
		throw new UnusedMethodException();
	}

	@Override
	public Iterable<OxygenTankInventory> saveAll(Iterable<OxygenTankInventory> objects) {
		throw new UnusedMethodException();
	}
	
	public OxygenTankInventory getInventory() {
		return null;
		//TODO : implements 
	}
}
