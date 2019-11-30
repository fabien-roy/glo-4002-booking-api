package ca.ulaval.glo4002.booking.orders.rest.mappers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import ca.ulaval.glo4002.booking.orders.domain.Order;
import ca.ulaval.glo4002.booking.orders.domain.OrderNumber;
import ca.ulaval.glo4002.booking.orders.rest.OrderResponse;
import ca.ulaval.glo4002.booking.passes.rest.mappers.PassBundleMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.passes.domain.PassBundle;

class OrderMapperTest {

	private OrderMapper orderMapper;
	private PassBundleMapper passBundleMapper;
	private Order order;

	@BeforeEach
	void setUpMapper() {
		passBundleMapper = mock(PassBundleMapper.class);
		orderMapper = new OrderMapper(passBundleMapper);
	}

	@BeforeEach
	void setUpOrder() {
		OrderNumber orderNumber = mock(OrderNumber.class);
		order = mock(Order.class);
		when(orderNumber.toString()).thenReturn("expectedOrderNumber");
		when(order.getOrderNumber()).thenReturn(orderNumber);
		when(order.getPrice()).thenReturn(new Money(new BigDecimal(500)));
		when(order.getPassBundle()).thenReturn(mock(PassBundle.class));
	}

	@Test
	void toResponse_shouldBuildResponseWithCorrectOrderPrice() {
		OrderResponse orderResponse = orderMapper.toResponse(order);
		BigDecimal priceValue = order.getPrice().getValue();

		assertEquals(priceValue.doubleValue(), orderResponse.getOrderPrice());
	}

	@Test
	public void toResponse_shouldBuildResponseOrderPriceWithTwoDigits_whenOrderPriceHasMoreThanTwoDigits() {
		BigDecimal orderPrice = new BigDecimal(123.123);
		Money price = new Money(orderPrice);
		when(order.getPrice()).thenReturn(price);
		String expectedOrderPrice = "123.12";

		OrderResponse orderResponse = orderMapper.toResponse(order);

		assertEquals(expectedOrderPrice, String.valueOf(orderResponse.getOrderPrice()));
	}

	@Test
	public void toResponse_shouldBuildResponseOrderPriceWithTwoDigits_whenOrderPriceHasLesThanTwoDigits() {
		BigDecimal orderPrice = new BigDecimal(123.1);
		Money price = new Money(orderPrice);
		when(order.getPrice()).thenReturn(price);
		String expectedOrderPrice = "123.1";

		OrderResponse orderResponse = orderMapper.toResponse(order);

		assertEquals(expectedOrderPrice, String.valueOf(orderResponse.getOrderPrice()));
	}

	@Test
	void toResponse_shouldCallPassListMapper() {
		orderMapper.toResponse(order);

		verify(passBundleMapper).toResponse(any());
	}
}