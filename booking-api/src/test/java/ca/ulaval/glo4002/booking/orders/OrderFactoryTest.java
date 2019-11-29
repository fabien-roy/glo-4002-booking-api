package ca.ulaval.glo4002.booking.orders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import ca.ulaval.glo4002.booking.festival.Festival;
import ca.ulaval.glo4002.booking.orders.rest.exceptions.InvalidOrderDateException;
import ca.ulaval.glo4002.booking.passes.bundles.PassBundleFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.numbers.NumberGenerator;
import ca.ulaval.glo4002.booking.passes.bundles.PassBundleDto;
import ca.ulaval.glo4002.booking.passes.PassOptions;
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
	void build_shouldParseDtoWithCorrectanOrderDate() {
		ZonedDateTime anOrderDate = ZonedDateTime.of(festival.getMinimumEventDateToOrder().toLocalDateTime().plusDays(1), ZoneId.systemDefault());
		PassBundleDto passBundleDto = mock(PassBundleDto.class);
		when(passBundleDto.getPassOption()).thenReturn(PassOptions.PACKAGE.toString());
		OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(anOrderDate.toString(), "TEAM", passBundleDto);

		Order order = factory.build(orderDto);

		assertEquals(anOrderDate.toLocalDateTime(), order.getOrderDate());
	}

	@Test
	void build_shouldParseDtoWithCorrectVendorCode() {
		ZonedDateTime anOrderDate = ZonedDateTime.of(festival.getMinimumEventDateToOrder().toLocalDateTime().plusDays(1), ZoneId.systemDefault());
		PassBundleDto passBundleDto = mock(PassBundleDto.class);
		when(passBundleDto.getPassOption()).thenReturn(PassOptions.PACKAGE.toString());
		OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(anOrderDate.toString(), "TEAM",
				passBundleDto);

		Order order = factory.build(orderDto);

		assertEquals(orderDto.getVendorCode(), order.getVendorCode());
	}

	@Test
	void build_shouldThrowInvalidFormatException_whenThereIsNoPass() {
		ZonedDateTime anOrderDate = ZonedDateTime.of(festival.getMinimumEventDateToOrder().toLocalDateTime().plusDays(1), ZoneId.systemDefault());
		OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(anOrderDate.toString(), "TEAM",
				null);

		assertThrows(InvalidFormatException.class, () -> factory.build(orderDto));
	}

	@Test
	void build_shouldThrowInvalidFormatException_whenanOrderDateIsInvalid() {
		String anInvalidOrderDate = "anInvalidDate";
		OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(anInvalidOrderDate, "TEAM",
				mock(PassBundleDto.class));

		assertThrows(InvalidFormatException.class, () -> factory.build(orderDto));
	}

	@Test
	void build_shouldThrowInvalidanOrderDateException_whenAnOrderDateIsUnderBounds() {
		LocalDateTime anUnderBoundValue = festival.getMinimumEventDateToOrder().toLocalDateTime().minusDays(1);
		ZonedDateTime anUnderBoundZonedValue = ZonedDateTime.of(anUnderBoundValue, ZoneId.systemDefault());
		OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(anUnderBoundZonedValue.toString(),
				"TEAM", mock(PassBundleDto.class));

		assertThrows(InvalidOrderDateException.class, () -> factory.build(orderDto));
	}

	@Test
	void build_shouldThrowInvalidanOrderDateException_whenAnOrderDateIsOverBounds() {
		LocalDateTime anOverBoundValue = festival.getMaximumEventDateToOrder().toLocalDateTime().plusDays(1);
		ZonedDateTime anOverBoundZonedValue = ZonedDateTime.of(anOverBoundValue, ZoneId.systemDefault());
		OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(anOverBoundZonedValue.toString(),
				"TEAM", mock(PassBundleDto.class));

		assertThrows(InvalidOrderDateException.class, () -> factory.build(orderDto));
	}
}