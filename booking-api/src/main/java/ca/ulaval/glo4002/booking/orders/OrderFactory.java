package ca.ulaval.glo4002.booking.orders;

import ca.ulaval.glo4002.booking.festival.domain.Festival;
import ca.ulaval.glo4002.booking.orders.rest.exceptions.InvalidOrderDateException;
import ca.ulaval.glo4002.booking.passes.bundles.PassBundleFactory;
import ca.ulaval.glo4002.booking.numbers.NumberGenerator;
import ca.ulaval.glo4002.booking.passes.bundles.PassBundle;
import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class OrderFactory {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    private final Festival festival;
    private final NumberGenerator numberGenerator;
    private final PassBundleFactory passBundleFactory;

    @Inject
    public OrderFactory(Festival festival, NumberGenerator numberGenerator, PassBundleFactory passBundleFactory) {
        this.festival = festival;
        this.numberGenerator = numberGenerator;
        this.passBundleFactory = passBundleFactory;
    }

    public Order build(OrderWithPassesAsEventDatesDto orderDto) {
        // TODO : Make passes nonNullable in Dto
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
        LocalDateTime minimalOrderDate = festival.getMinimumEventDateToOrder().toLocalDateTime();
        LocalDateTime maximalOrderDate = festival.getMaximumEventDateToOrder().toLocalDateTime();

        // TODO : Could use EventDate.isBetweenOrEquals if OrderDate made sense as an EventDate
        if (orderDate.isBefore(minimalOrderDate) || orderDate.isAfter(maximalOrderDate)) {
            throw new InvalidOrderDateException();
        }
    }
}
