package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.NumberGenerator;
import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.domain.orders.OrderNumber;
import ca.ulaval.glo4002.booking.domain.passes.PassBundle;
import ca.ulaval.glo4002.booking.dto.orders.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.exceptions.InvalidOrderDateException;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class OrderFactory {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;
    public static final LocalDateTime START_DATE_TIME = LocalDateTime.of(2050, 1, 1, 0, 0);
    public static final LocalDateTime END_DATE_TIME = LocalDateTime.of(2050, 7, 17, 0, 0);

    private final NumberGenerator numberGenerator;
    private final PassBundleFactory passBundleFactory;

    @Inject
    public OrderFactory(NumberGenerator numberGenerator, PassBundleFactory passBundleFactory) {
        this.numberGenerator = numberGenerator;
        this.passBundleFactory = passBundleFactory;
    }

    public Order build(OrderWithPassesAsEventDatesDto orderDto) {
        if (orderDto.getPasses() == null) {
            throw new InvalidFormatException();
        }

        OrderNumber orderNumber = new OrderNumber(numberGenerator.generate(), orderDto.getVendorCode());
        LocalDateTime orderDate = buildOrderDate(orderDto.getOrderDate());
        PassBundle passBundle = passBundleFactory.build(orderDto.getPasses());

        return new Order(orderNumber, orderDate, passBundle);
    }

    private LocalDateTime buildOrderDate(String sentOrderDate) {
        LocalDateTime orderDate = parseLocalDateTime(sentOrderDate);

        validateOrderDate(orderDate);

        return orderDate;
    }

    private LocalDateTime parseLocalDateTime(String orderDate) {
        try {
            return ZonedDateTime.parse(orderDate, DATE_TIME_FORMATTER).toLocalDateTime();
        } catch (Exception exception) {
            throw new InvalidFormatException();
        }
    }

    private void validateOrderDate(LocalDateTime orderDate) {
        if (orderDate.isBefore(START_DATE_TIME) || orderDate.isAfter(END_DATE_TIME)) {
            throw new InvalidOrderDateException();
        }
    }
}
