package ca.ulaval.glo4002.booking.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OxygenTankServiceTest {

	OxygenTankService subject;
	OxygenTankServiceContext context;

	@BeforeEach
	public void setUp() {
		this.context = new OxygenTankServiceContext();
		this.subject = new OxygenTankService(this.context.repository);
	}

	@Test
	public void findAll_shouldReturnCorrectOxygens() {
		List<Long> tankIds = new ArrayList<>();
		subject.findAll().forEach(oxygenTank -> tankIds.add(oxygenTank.getId()));

		assertEquals(3, tankIds.size());
		assertTrue(tankIds.contains(context.oxygenTankA.getId()));
		assertTrue(tankIds.contains(context.oxygenTankB.getId()));
		assertTrue(tankIds.contains(context.oxygenTankE.getId()));
	}
}
