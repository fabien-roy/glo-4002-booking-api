package ca.ulaval.glo4002.booking.orders;

import ca.ulaval.glo4002.booking.configuration.Configuration;
import ca.ulaval.glo4002.booking.passes.bundles.PassBundleFactory;
import ca.ulaval.glo4002.booking.numbers.NumberGenerator;
import ca.ulaval.glo4002.booking.passes.bundles.PassBundle;
import ca.ulaval.glo4002.booking.errors.InvalidFormatException;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class OrderFactory {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    private final Configuration configuration;
    private final NumberGenerator numberGenerator;
    private final PassBundleFactory passBundleFactory;

    @Inject
    public OrderFactory(Configuration configuration, NumberGenerator numberGenerator, PassBundleFactory passBundleFactory) {
        this.configuration = configuration;
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

    private void validateOrderDate(LocalDateTime orderDate) {
        LocalDateTime minimalOrderDate = configuration.getMinimumEventDateToOrder().toLocalDateTime();
        LocalDateTime maximalOrderDate = configuration.getMaximumEventDateToOrder().toLocalDateTime();

        // TODO : Could use EventDate.isBetweenOrEquals if OrderDate made sense as an EventDate
        if (orderDate.isBefore(minimalOrderDate) || orderDate.isAfter(maximalOrderDate)) {
            throw new InvalidOrderDateException();
        }
    }
}
