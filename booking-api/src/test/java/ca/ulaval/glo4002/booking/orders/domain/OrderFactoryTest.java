package ca.ulaval.glo4002.booking.orders.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
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
	private FestivalConfiguration festivalConfiguration;

	@BeforeEach
	void setUpFactory() {
	    festivalConfiguration = new FestivalConfiguration(); // TODO : Should Configuration be mocked in OrderFactoryTest?
		NumberGenerator numberGenerator = new NumberGenerator();
		PassBundleFactory passBundleFactory = mock(PassBundleFactory.class);

		this.factory = new OrderFactory(festivalConfiguration, numberGenerator, passBundleFactory);
	}

	@Test
	void build_shouldParseRequestWithCorrectAnOrderDate() {
		ZonedDateTime anOrderDate = ZonedDateTime.of(festivalConfiguration.getMinimumEventDateToOrder().toLocalDateTime().plusDays(1), ZoneId.systemDefault());
		PassBundleRequest passBundleRequest = mock(PassBundleRequest.class);
		when(passBundleRequest.getPassOption()).thenReturn(PassOptions.PACKAGE.toString());
		OrderRequest orderRequest = new OrderRequest(anOrderDate.toString(), "TEAM", passBundleRequest);

		Order order = factory.create(orderRequest);

		assertEquals(anOrderDate.toLocalDateTime(), order.getOrderDate());
	}

	@Test
	void build_shouldParseRequestWithCorrectVendorCode() {
		ZonedDateTime anOrderDate = ZonedDateTime.of(festivalConfiguration.getMinimumEventDateToOrder().toLocalDateTime().plusDays(1), ZoneId.systemDefault());
		PassBundleRequest passBundleRequest = mock(PassBundleRequest.class);
		when(passBundleRequest.getPassOption()).thenReturn(PassOptions.PACKAGE.toString());
		OrderRequest orderRequest = new OrderRequest(anOrderDate.toString(), "TEAM",
				passBundleRequest);

		Order order = factory.create(orderRequest);

		assertEquals(orderRequest.getVendorCode(), order.getVendorCode());
	}

	@Test
	void build_shouldThrowInvalidFormatException_whenThereIsNoPass() {
		ZonedDateTime anOrderDate = ZonedDateTime.of(festivalConfiguration.getMinimumEventDateToOrder().toLocalDateTime().plusDays(1), ZoneId.systemDefault());
		OrderRequest orderRequest = new OrderRequest(anOrderDate.toString(), "TEAM",
				null);

		assertThrows(InvalidFormatException.class, () -> factory.create(orderRequest));
	}

	@Test
	void build_shouldThrowInvalidFormatException_whenanOrderDateIsInvalid() {
		String anInvalidOrderDate = "anInvalidDate";
		OrderRequest orderRequest = new OrderRequest(anInvalidOrderDate, "TEAM",
				mock(PassBundleRequest.class));

		assertThrows(InvalidFormatException.class, () -> factory.create(orderRequest));
	}

	@Test
	void build_shouldThrowInvalidanOrderDateException_whenAnOrderDateIsUnderBounds() {
		LocalDateTime anUnderBoundValue = festivalConfiguration.getMinimumEventDateToOrder().toLocalDateTime().minusDays(1);
		ZonedDateTime anUnderBoundZonedValue = ZonedDateTime.of(anUnderBoundValue, ZoneId.systemDefault());
		OrderRequest orderRequest = new OrderRequest(anUnderBoundZonedValue.toString(),
				"TEAM", mock(PassBundleRequest.class));

		assertThrows(InvalidOrderDateException.class, () -> factory.create(orderRequest));
	}

	@Test
	void build_shouldThrowInvalidanOrderDateException_whenAnOrderDateIsOverBounds() {
		LocalDateTime anOverBoundValue = festivalConfiguration.getMaximumEventDateToOrder().toLocalDateTime().plusDays(1);
		ZonedDateTime anOverBoundZonedValue = ZonedDateTime.of(anOverBoundValue, ZoneId.systemDefault());
		OrderRequest orderRequest = new OrderRequest(anOverBoundZonedValue.toString(),
				"TEAM", mock(PassBundleRequest.class));

		assertThrows(InvalidOrderDateException.class, () -> factory.create(orderRequest));
	}
}