package ca.ulaval.glo4002.booking.orders.rest.mappers;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.orders.domain.Order;
import ca.ulaval.glo4002.booking.orders.domain.OrderDate;
import ca.ulaval.glo4002.booking.orders.domain.OrderRefactored;
import ca.ulaval.glo4002.booking.orders.rest.OrderRefactoredRequest;
import ca.ulaval.glo4002.booking.orders.rest.OrderResponse;
import ca.ulaval.glo4002.booking.passes.domain.PassBundle;
import ca.ulaval.glo4002.booking.passes.domain.PassRefactored;
import ca.ulaval.glo4002.booking.passes.rest.PassRefactoredRequest;
import ca.ulaval.glo4002.booking.passes.rest.PassResponse;
import ca.ulaval.glo4002.booking.passes.rest.mappers.PassBundleMapper;
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
	private PassBundleMapper passBundleMapper;
	private OrderDateMapper orderDateMapper;
	private PassMapper passMapper;
	private Order order;
	private OrderRefactored orderRefactored;

	@BeforeEach
	void setUpMapper() {
		passBundleMapper = mock(PassBundleMapper.class);
		orderDateMapper = mock(OrderDateMapper.class);
		passMapper = mock(PassMapper.class);

		orderMapper = new OrderMapper(passBundleMapper, orderDateMapper, passMapper);
	}

	@BeforeEach
	void setUpOrder() {
		order = mock(Order.class);
		when(order.getPrice()).thenReturn(new Money(BigDecimal.valueOf(500)));
		when(order.getPassBundle()).thenReturn(mock(PassBundle.class));

		orderRefactored = mock(OrderRefactored.class);
		when(orderRefactored.getPrice()).thenReturn(new Money(BigDecimal.valueOf(500)));
		when(orderRefactored.getPasses()).thenReturn(Collections.singletonList(mock(PassRefactored.class)));
	}

	@Test
	void fromRequest_shouldThrowInvalidFormatException_whenThereIsNoPass() {
		ZonedDateTime anOrderDate = generateOrderDate();
		OrderRefactoredRequest orderRequest = new OrderRefactoredRequest(anOrderDate.toString(), "VENDOR", null);

		assertThrows(InvalidFormatException.class, () -> orderMapper.fromRequest(orderRequest));
	}

	@Test
	void fromRequest_shouldSetOrderDate() {
		ZonedDateTime anOrderDate = generateOrderDate();
		OrderDate expectedOrderDate = mock(OrderDate.class);
		when(orderDateMapper.fromString(eq(anOrderDate.toString()))).thenReturn(expectedOrderDate);
		PassRefactoredRequest passRequest = mock(PassRefactoredRequest.class);
		OrderRefactoredRequest orderRequest = new OrderRefactoredRequest(anOrderDate.toString(), "VENDOR", passRequest);

		OrderRefactored order = orderMapper.fromRequest(orderRequest);

		assertEquals(expectedOrderDate, order.getOrderDate());
	}

	@Test
	void fromRequest_shouldSetPass() {
		ZonedDateTime anOrderDate = generateOrderDate();
		PassRefactoredRequest passRequest = mock(PassRefactoredRequest.class);
		PassRefactored expectedPass = mock(PassRefactored.class);
		when(passMapper.fromRequest(eq(passRequest))).thenReturn(Collections.singletonList(expectedPass));
		OrderRefactoredRequest orderRequest = new OrderRefactoredRequest(anOrderDate.toString(), "VENDOR", passRequest);

		OrderRefactored order = orderMapper.fromRequest(orderRequest);

		assertEquals(expectedPass, order.getPasses().get(0));
	}

	// TODO : Rename tests of refactored pass logic when old logic is removed

	@Test
	public void toRefactoredResponse_shouldBuildResponseOrderPriceWithTwoDigits_whenOrderPriceHasMoreThanTwoDigits() {
		BigDecimal orderPrice = BigDecimal.valueOf(123.123);
		Money price = new Money(orderPrice);
		when(orderRefactored.getPrice()).thenReturn(price);
		String expectedOrderPrice = "123.12";

		OrderResponse orderResponse = orderMapper.toResponse(orderRefactored);

		assertEquals(expectedOrderPrice, String.valueOf(orderResponse.getOrderPrice()));
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
		when(orderRefactored.getPrice()).thenReturn(price);
		String expectedOrderPrice = "123.1";

		OrderResponse orderResponse = orderMapper.toResponse(orderRefactored);

		assertEquals(expectedOrderPrice, String.valueOf(orderResponse.getOrderPrice()));
	}

	@Test
	public void toResponse_shouldBuildResponseOrderPriceWithTwoDigits_whenOrderPriceHasLessThanTwoDigits() {
		BigDecimal orderPrice = BigDecimal.valueOf(123.1);
		Money price = new Money(orderPrice);
		when(order.getPrice()).thenReturn(price);
		String expectedOrderPrice = "123.1";

		OrderResponse orderResponse = orderMapper.toResponse(order);

		assertEquals(expectedOrderPrice, String.valueOf(orderResponse.getOrderPrice()));
	}

	@Test
	void toRefactoredResponse_shouldSetPass_whenThereIsASinglePass() {
		List<PassResponse> expectedPasses = Collections.singletonList(mock(PassResponse.class));
		when(passMapper.toResponse(any())).thenReturn(expectedPasses);

		OrderResponse orderResponse = orderMapper.toResponse(orderRefactored);

		assertEquals(expectedPasses.get(0), orderResponse.getPasses().get(0));
	}

	@Test
	void toRefactoredResponse_shouldSetPass_whenThereIsAreMultiplePasses() {
	    PassResponse expectedPass = mock(PassResponse.class);
		PassResponse otherExpectedPass = mock(PassResponse.class);
		List<PassResponse> expectedPasses = Arrays.asList(expectedPass, otherExpectedPass);
		when(passMapper.toResponse(any())).thenReturn(expectedPasses);

		OrderResponse orderResponse = orderMapper.toResponse(orderRefactored);

		assertEquals(expectedPasses.get(0), orderResponse.getPasses().get(0));
		assertEquals(expectedPasses.get(1), orderResponse.getPasses().get(1));
	}

	@Test
	void toResponse_shouldCreatePassBundle() {
		orderMapper.toResponse(order);

		verify(passBundleMapper).toResponse(any());
	}

	private ZonedDateTime generateOrderDate() {
		return ZonedDateTime.of(FestivalConfiguration.getDefaultStartOrderDate().getValue().plusDays(1), ZoneId.systemDefault());
	}
}