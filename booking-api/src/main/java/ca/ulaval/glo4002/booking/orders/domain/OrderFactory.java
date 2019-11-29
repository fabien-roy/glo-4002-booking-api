package ca.ulaval.glo4002.booking.orders.domain;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.orders.rest.OrderRequest;
import ca.ulaval.glo4002.booking.orders.rest.exceptions.InvalidOrderDateException;
import ca.ulaval.glo4002.booking.passes.domain.PassBundleFactory;
import ca.ulaval.glo4002.booking.numbers.NumberGenerator;
import ca.ulaval.glo4002.booking.passes.domain.PassBundle;
import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class OrderFactory {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    private final FestivalConfiguration festivalConfiguration;
    private final NumberGenerator numberGenerator;
    private final PassBundleFactory passBundleFactory;

    @Inject
    public OrderFactory(FestivalConfiguration festivalConfiguration, NumberGenerator numberGenerator, PassBundleFactory passBundleFactory) {
        this.festivalConfiguration = festivalConfiguration;
        this.numberGenerator = numberGenerator;
        this.passBundleFactory = passBundleFactory;
    }

    public Order create(OrderRequest orderRequest) {
        // TODO : Make passes nonNullable in Requests
        if (orderRequest.getPasses() == null) {
            throw new InvalidFormatException();
        }

        OrderNumber orderNumber = new OrderNumber(numberGenerator.generate(), orderRequest.getVendorCode());
        LocalDateTime orderDate = createOrderDate(orderRequest.getOrderDate());
        PassBundle passBundle = passBundleFactory.create(orderRequest.getPasses());

        return new Order(orderNumber, orderDate, passBundle);
    }

    private LocalDateTime createOrderDate(String textOrderDate) {
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
        LocalDateTime minimalOrderDate = festivalConfiguration.getMinimumEventDateToOrder().toLocalDateTime();
        LocalDateTime maximalOrderDate = festivalConfiguration.getMaximumEventDateToOrder().toLocalDateTime();

        // TODO : Could use EventDate.isBetweenOrEquals if OrderDate made sense as an EventDate
        if (orderDate.isBefore(minimalOrderDate) || orderDate.isAfter(maximalOrderDate)) {
            throw new InvalidOrderDateException();
        }
    }
}
