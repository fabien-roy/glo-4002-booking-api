package ca.ulaval.glo4002.booking.orders.rest.mappers;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.orders.domain.Order;
import ca.ulaval.glo4002.booking.orders.domain.OrderDate;
import ca.ulaval.glo4002.booking.orders.rest.OrderRequest;
import ca.ulaval.glo4002.booking.orders.rest.OrderResponse;
import ca.ulaval.glo4002.booking.passes.domain.Pass;
import ca.ulaval.glo4002.booking.passes.rest.PassRefactoredRequest;
import ca.ulaval.glo4002.booking.passes.rest.PassResponse;
import ca.ulaval.glo4002.booking.passes.rest.mappers.PassMapper;
import ca.ulaval.glo4002.booking.profits.domain.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class OrderMapperTest {

	private OrderMapper orderMapper;
	private OrderDateMapper orderDateMapper;
	private PassMapper passMapper;
	private Order order;

	@BeforeEach
	void setUpMapper() {
		orderDateMapper = mock(OrderDateMapper.class);
		passMapper = mock(PassMapper.class);

		orderMapper = new OrderMapper(orderDateMapper, passMapper);
	}

	@BeforeEach
	void setUpOrder() {
		order = mock(Order.class);
		when(order.getPrice()).thenReturn(new Money(BigDecimal.valueOf(500)));
		when(order.getPasses()).thenReturn(Collections.singletonList(mock(Pass.class)));
	}

	@Test
	void fromRequest_shouldThrowInvalidFormatException_whenThereIsNoPass() {
		ZonedDateTime anOrderDate = generateOrderDate();
		OrderRequest orderRequest = new OrderRequest(anOrderDate.toString(), "VENDOR", null);

		assertThrows(InvalidFormatException.class, () -> orderMapper.fromRequest(orderRequest));
	}

	@Test
	void fromRequest_shouldSetOrderDate() {
		ZonedDateTime anOrderDate = generateOrderDate();
		OrderDate expectedOrderDate = mock(OrderDate.class);
		when(orderDateMapper.fromString(eq(anOrderDate.toString()))).thenReturn(expectedOrderDate);
		PassRefactoredRequest passRequest = mock(PassRefactoredRequest.class);
		OrderRequest orderRequest = new OrderRequest(anOrderDate.toString(), "VENDOR", passRequest);

		Order order = orderMapper.fromRequest(orderRequest);

		assertEquals(expectedOrderDate, order.getOrderDate());
	}

	@Test
	void fromRequest_shouldSetPass() {
		ZonedDateTime anOrderDate = generateOrderDate();
		PassRefactoredRequest passRequest = mock(PassRefactoredRequest.class);
		Pass expectedPass = mock(Pass.class);
		when(passMapper.fromRequest(eq(passRequest))).thenReturn(Collections.singletonList(expectedPass));
		OrderRequest orderRequest = new OrderRequest(anOrderDate.toString(), "VENDOR", passRequest);

		Order order = orderMapper.fromRequest(orderRequest);

		assertEquals(expectedPass, order.getPasses().get(0));
	}

	@Test
	public void toResponse_shouldBuildResponseOrderPriceWithTwoDigits_whenOrderPriceHasMoreThanTwoDigits() {
		BigDecimal orderPrice = BigDecimal.valueOf(123.123);
		Money price = new Money(orderPrice);
		when(order.getPrice()).thenReturn(price);
		String expectedOrderPrice = "123.12";

		OrderResponse orderResponse = orderMapper.toResponse(order);

		assertEquals(expectedOrderPrice, String.valueOf(orderResponse.getOrderPrice()));
	}

	// TODO : Fix this test, it should be "123.10"
	@Test
	public void toRefactoredResponse_shouldBuildResponseOrderPriceWithTwoDigits_whenOrderPriceHasLessThanTwoDigits() {
		BigDecimal orderPrice = BigDecimal.valueOf(123.1);
		Money price = new Money(orderPrice);
		when(order.getPrice()).thenReturn(price);
		String expectedOrderPrice = "123.1";

		OrderResponse orderResponse = orderMapper.toResponse(order);

		assertEquals(expectedOrderPrice, String.valueOf(orderResponse.getOrderPrice()));
	}

	@Test
	void toResponse_shouldSetPass_whenThereIsASinglePass() {
		List<PassResponse> expectedPasses = Collections.singletonList(mock(PassResponse.class));
		when(passMapper.toResponse(any())).thenReturn(expectedPasses);

		OrderResponse orderResponse = orderMapper.toResponse(order);

		assertEquals(expectedPasses.get(0), orderResponse.getPasses().get(0));
	}

	@Test
	void toResponse_shouldSetPass_whenThereIsAreMultiplePasses() {
	    PassResponse expectedPass = mock(PassResponse.class);
		PassResponse otherExpectedPass = mock(PassResponse.class);
		List<PassResponse> expectedPasses = Arrays.asList(expectedPass, otherExpectedPass);
		when(passMapper.toResponse(any())).thenReturn(expectedPasses);

		OrderResponse orderResponse = orderMapper.toResponse(order);

		assertEquals(expectedPasses.get(0), orderResponse.getPasses().get(0));
		assertEquals(expectedPasses.get(1), orderResponse.getPasses().get(1));
	}

	private ZonedDateTime generateOrderDate() {
		return ZonedDateTime.of(FestivalConfiguration.getDefaultStartOrderDate().getValue().plusDays(1), ZoneId.systemDefault());
	}
}