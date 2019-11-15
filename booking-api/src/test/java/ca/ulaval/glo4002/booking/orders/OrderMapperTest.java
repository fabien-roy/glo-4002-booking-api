package ca.ulaval.glo4002.booking.orders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import ca.ulaval.glo4002.booking.orders.OrderMapper;
import ca.ulaval.glo4002.booking.passes.bundles.PassBundleMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.profits.Money;
import ca.ulaval.glo4002.booking.orders.Order;
import ca.ulaval.glo4002.booking.orders.OrderNumber;
import ca.ulaval.glo4002.booking.passes.bundles.PassBundle;
import ca.ulaval.glo4002.booking.orders.OrderWithPassesAsPassesDto;

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
	void toDto_shouldBuildDtoWithCorrectOrderPrice() {
		OrderWithPassesAsPassesDto orderDto = orderMapper.toDto(order);
		BigDecimal priceValue = order.getPrice().getValue();

		assertEquals(priceValue.doubleValue(), orderDto.getOrderPrice());
	}

	@Test
	public void toDto_shouldBuildDtoOrderPriceWithTwoDigits_whenOrderPriceHasMoreThanTwoDigits() {
		BigDecimal orderPrice = new BigDecimal(123.123);
		Money price = new Money(orderPrice);
		when(order.getPrice()).thenReturn(price);
		String expectedOrderPrice = "123.12";

		OrderWithPassesAsPassesDto orderDto = orderMapper.toDto(order);

		assertEquals(expectedOrderPrice, Double.toString(orderDto.getOrderPrice()));
	}

	@Test
	public void toDto_shouldBuildDtoOrderPriceWithTwoDigits_whenOrderPriceHasLesThanTwoDigits() {
		BigDecimal orderPrice = new BigDecimal(123.1);
		Money price = new Money(orderPrice);
		when(order.getPrice()).thenReturn(price);
		String expectedOrderPrice = "123.1";

		OrderWithPassesAsPassesDto orderDto = orderMapper.toDto(order);

		assertEquals(expectedOrderPrice, Double.toString(orderDto.getOrderPrice()));
	}

	@Test
	void toDto_shouldCallPassListMapper() {
		orderMapper.toDto(order);

		verify(passBundleMapper).toDto(any());
	}
}