package ca.ulaval.glo4002.booking.orders.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.passes.domain.PassBundle;

class OrderTest {

	private Order order;
	private OrderDate orderDate;
	private PassBundle passBundle;
	private OrderNumber orderNumber;

	@BeforeEach
	void setUpVariables() {
		orderDate = new OrderDate(LocalDateTime.of(FestivalConfiguration.getDefaultStartEventDate().getValue(), LocalTime.MIDNIGHT));
		passBundle = mock(PassBundle.class);
		orderNumber = mock(OrderNumber.class);

		when(passBundle.getPrice()).thenReturn(mock(Money.class));
		when(passBundle.getPasses()).thenReturn(new ArrayList<>());
		when(orderNumber.getVendorCode()).thenReturn("VENDOR");
	}

	@Test
	void constructing_shouldSetCorrectOrderDate() {
		order = new Order(orderNumber, orderDate, passBundle);

		assertEquals(order.getOrderDate(), orderDate);
	}

	@Test
	void constructing_shouldSetCorrectPassBundle() {
		order = new Order(orderNumber, orderDate, passBundle);

		assertEquals(order.getPassBundle(), passBundle);
	}

	@Test
	void constructing_shouldSetCorrectOrderNumber() {
		order = new Order(orderNumber, orderDate, passBundle);

		assertEquals(order.getOrderNumber(), orderNumber);
	}

	@Test
	void getPrice_shouldGetCorrectPrice() {
		order = new Order(orderNumber, orderDate, passBundle);

		assertEquals(order.getPrice(), passBundle.getPrice());
	}

	@Test
	void getPasses_shouldGetCorrectPassesList() {
		order = new Order(orderNumber, orderDate, passBundle);

		assertEquals(order.getPasses(), passBundle.getPasses());
	}

	@Test
	void getVendorCode_shouldGetCorrectVendorCode() {
		order = new Order(orderNumber, orderDate, passBundle);

		assertEquals(order.getVendorCode(), orderNumber.getVendorCode());
	}

}
