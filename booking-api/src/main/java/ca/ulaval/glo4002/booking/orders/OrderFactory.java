package ca.ulaval.glo4002.booking.orders;

import ca.ulaval.glo4002.booking.passes.bundles.PassBundleFactory;
import ca.ulaval.glo4002.booking.numbers.NumberGenerator;
import ca.ulaval.glo4002.booking.passes.bundles.PassBundle;
import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;

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

    private LocalDateTime buildOrderDate(String textOrderDate) {
        LocalDateTime orderDate = parseLocalDateTime(textOrderDate);

        validateOrderDate(orderDate);

        return orderDate;
    }

    private LocalDateTime parseLocalDateTime(String textOrderDate) {
        try {
            return ZonedDateTime.parse(textOrderDate, DATE_TIME_FORMATTER).toLocalDateTime();
        } catch (Exception exception) {
            throw new InvalidFormatException();
        }
    }

    // TODO : REP : Check if orderdate over or equal to 180 days before festival start date
    // TODO : REP : Check if orderdate under or equal to festival start date
    private void validateOrderDate(LocalDateTime orderDate) {
        if (orderDate.isBefore(START_DATE_TIME) || orderDate.isAfter(END_DATE_TIME)) {
            throw new InvalidOrderDateException();
        }
    }
}
