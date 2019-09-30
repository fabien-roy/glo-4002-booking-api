package ca.ulaval.glo4002.booking.services;

import static org.mockito.Mockito.*;

import ca.ulaval.glo4002.booking.domainObjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domainObjects.oxygen.productions.OxygenProduction;
import ca.ulaval.glo4002.booking.entities.OxygenTankEntity;
import ca.ulaval.glo4002.booking.parsers.OxygenTankParser;
import ca.ulaval.glo4002.booking.repositories.OxygenRepository;

public class OxygenTankServiceContext {
	public OxygenRepository repository;
	public OxygenTank oxygenTankA;
	public OxygenTank oxygenTankB;
	public OxygenTank oxygenTankE;
	public OxygenTankEntity oxygenTankAEntity;
	public OxygenTankEntity oxygenTankBEntity;
	public OxygenTankEntity oxygenTankEEntity;
	private OxygenProduction oxygenProduction;
	private OxygenTankParser parser = new OxygenTankParser();

	public OxygenTankServiceContext() {
		this.setUpOxygenTanks();
		this.setUpRepository();
	}

	private void setUpRepository() {
		this.repository = mock(OxygenRepository.class);
		// TODO write this
	}

	private void setUpOxygenTanks() {
		// TODO write this
	}
}
