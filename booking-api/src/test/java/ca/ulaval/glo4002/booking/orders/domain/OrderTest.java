package ca.ulaval.glo4002.booking.orders.domain;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.passes.domain.PassBundle;
import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.profits.domain.ProfitReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderTest {

	private Order order;
	private PassBundle passBundle;
	private OrderNumber orderNumber;

	@BeforeEach
	void setUpOrder() {
		OrderDate orderDate = FestivalConfiguration.getDefaultStartOrderDate();
		passBundle = mock(PassBundle.class);
		orderNumber = mock(OrderNumber.class);

		when(passBundle.getPrice()).thenReturn(mock(Money.class));
		when(passBundle.getPasses()).thenReturn(new ArrayList<>());
		when(orderNumber.getVendorCode()).thenReturn("VENDOR");

		order = new Order(orderNumber, orderDate, passBundle);
	}

	@Test
	void getPrice_shouldGetCorrectPrice() {
		assertEquals(order.getPrice(), passBundle.getPrice());
	}

	@Test
	void getPasses_shouldGetCorrectPassesList() {
		assertEquals(order.getPasses(), passBundle.getPasses());
	}

	@Test
	void getVendorCode_shouldGetCorrectVendorCode() {
		assertEquals(order.getVendorCode(), orderNumber.getVendorCode());
	}

	@Test
	void updateProfit_shouldUpdateProfitOfPassBundle() {
		ProfitReport profitReport = mock(ProfitReport.class);

		order.updateProfit(profitReport);

		verify(passBundle).updateProfit(eq(profitReport));
	}
}
