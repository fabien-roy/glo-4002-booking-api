package ca.ulaval.glo4002.booking.orders.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import ca.ulaval.glo4002.booking.festival.domain.Festival;
import ca.ulaval.glo4002.booking.orders.rest.OrderRequest;
import ca.ulaval.glo4002.booking.orders.rest.exceptions.InvalidOrderDateException;
import ca.ulaval.glo4002.booking.passes.domain.PassBundleFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.numbers.NumberGenerator;
import ca.ulaval.glo4002.booking.passes.rest.PassBundleRequest;
import ca.ulaval.glo4002.booking.passes.domain.PassOptions;
import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;

class OrderFactoryTest {

	private OrderFactory factory;
	private Festival festival;

	@BeforeEach
	void setUpFactory() {
	    festival = new Festival(); // TODO : Should Configuration be mocked in OrderFactoryTest?
		NumberGenerator numberGenerator = new NumberGenerator();
		PassBundleFactory passBundleFactory = mock(PassBundleFactory.class);

		this.factory = new OrderFactory(festival, numberGenerator, passBundleFactory);
	}

	@Test
	void build_shouldParseDtoWithCorrectAnOrderDate() {
		ZonedDateTime anOrderDate = ZonedDateTime.of(festival.getMinimumEventDateToOrder().toLocalDateTime().plusDays(1), ZoneId.systemDefault());
		PassBundleRequest passBundleRequest = mock(PassBundleRequest.class);
		when(passBundleRequest.getPassOption()).thenReturn(PassOptions.PACKAGE.toString());
		OrderRequest orderDto = new OrderRequest(anOrderDate.toString(), "TEAM", passBundleRequest);

		Order order = factory.create(orderDto);

		assertEquals(anOrderDate.toLocalDateTime(), order.getOrderDate());
	}

	@Test
	void build_shouldParseDtoWithCorrectVendorCode() {
		ZonedDateTime anOrderDate = ZonedDateTime.of(festival.getMinimumEventDateToOrder().toLocalDateTime().plusDays(1), ZoneId.systemDefault());
		PassBundleRequest passBundleRequest = mock(PassBundleRequest.class);
		when(passBundleRequest.getPassOption()).thenReturn(PassOptions.PACKAGE.toString());
		OrderRequest orderDto = new OrderRequest(anOrderDate.toString(), "TEAM",
				passBundleRequest);

		Order order = factory.create(orderDto);

		assertEquals(orderDto.getVendorCode(), order.getVendorCode());
	}

	@Test
	void build_shouldThrowInvalidFormatException_whenThereIsNoPass() {
		ZonedDateTime anOrderDate = ZonedDateTime.of(festival.getMinimumEventDateToOrder().toLocalDateTime().plusDays(1), ZoneId.systemDefault());
		OrderRequest orderDto = new OrderRequest(anOrderDate.toString(), "TEAM",
				null);

		assertThrows(InvalidFormatException.class, () -> factory.create(orderDto));
	}

	@Test
	void build_shouldThrowInvalidFormatException_whenanOrderDateIsInvalid() {
		String anInvalidOrderDate = "anInvalidDate";
		OrderRequest orderDto = new OrderRequest(anInvalidOrderDate, "TEAM",
				mock(PassBundleRequest.class));

		assertThrows(InvalidFormatException.class, () -> factory.create(orderDto));
	}

	@Test
	void build_shouldThrowInvalidanOrderDateException_whenAnOrderDateIsUnderBounds() {
		LocalDateTime anUnderBoundValue = festival.getMinimumEventDateToOrder().toLocalDateTime().minusDays(1);
		ZonedDateTime anUnderBoundZonedValue = ZonedDateTime.of(anUnderBoundValue, ZoneId.systemDefault());
		OrderRequest orderDto = new OrderRequest(anUnderBoundZonedValue.toString(),
				"TEAM", mock(PassBundleRequest.class));

		assertThrows(InvalidOrderDateException.class, () -> factory.create(orderDto));
	}

	@Test
	void build_shouldThrowInvalidanOrderDateException_whenAnOrderDateIsOverBounds() {
		LocalDateTime anOverBoundValue = festival.getMaximumEventDateToOrder().toLocalDateTime().plusDays(1);
		ZonedDateTime anOverBoundZonedValue = ZonedDateTime.of(anOverBoundValue, ZoneId.systemDefault());
		OrderRequest orderDto = new OrderRequest(anOverBoundZonedValue.toString(),
				"TEAM", mock(PassBundleRequest.class));

		assertThrows(InvalidOrderDateException.class, () -> factory.create(orderDto));
	}
}