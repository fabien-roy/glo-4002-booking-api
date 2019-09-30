package ca.ulaval.glo4002.booking.services;

import org.junit.jupiter.api.BeforeEach;

class OxygenTankServiceTest {

	OxygenTankService subject;
	OxygenTankServiceContext context;

	@BeforeEach
	public void setUp() {
		this.context = new OxygenTankServiceContext();
		this.subject = new OxygenTankService(this.context.repository);
	}

	// TODO Test something
}
