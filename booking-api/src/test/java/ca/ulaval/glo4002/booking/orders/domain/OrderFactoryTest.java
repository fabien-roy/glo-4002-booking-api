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
	private FestivalConfiguration festivalConfiguration; // TODO : Is FestivalConfiguration necessary in order factory tests?

	@BeforeEach
	void setUpFactory() {
	    festivalConfiguration = new FestivalConfiguration();
		NumberGenerator numberGenerator = new NumberGenerator();
		OrderDateFactory orderDateFactory = mock(OrderDateFactory.class);
		PassBundleFactory passBundleFactory = mock(PassBundleFactory.class);

		this.factory = new OrderFactory(numberGenerator, orderDateFactory, passBundleFactory);
	}

	@Test
	void create_shouldParseRequestWithCorrectVendorCode() {
		ZonedDateTime anOrderDate = ZonedDateTime.of(festivalConfiguration.getStartOrderDate().getValue().plusDays(1), ZoneId.systemDefault());
		PassBundleRequest passBundleRequest = mock(PassBundleRequest.class);
		when(passBundleRequest.getPassOption()).thenReturn(PassOptions.PACKAGE.toString());
		OrderRequest orderRequest = new OrderRequest(anOrderDate.toString(), "TEAM", passBundleRequest);

		Order order = factory.create(orderRequest);

		assertEquals(orderRequest.getVendorCode(), order.getVendorCode());
	}

	@Test
	void create_shouldThrowInvalidFormatException_whenThereIsNoPass() {
		ZonedDateTime anOrderDate = ZonedDateTime.of(festivalConfiguration.getStartOrderDate().getValue().plusDays(1), ZoneId.systemDefault());
		OrderRequest orderRequest = new OrderRequest(anOrderDate.toString(), "TEAM", null);

		assertThrows(InvalidFormatException.class, () -> factory.create(orderRequest));
	}
}