package ca.ulaval.glo4002.booking.orders.rest.mappers;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.orders.domain.OrderDate;
import ca.ulaval.glo4002.booking.orders.rest.exceptions.InvalidOrderDateException;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class OrderDateMapper {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    private final FestivalConfiguration festivalConfiguration;

    @Inject
    public OrderDateMapper(FestivalConfiguration festivalConfiguration) {
        this.festivalConfiguration = festivalConfiguration;
    }

    public OrderDate fromString(String orderDate) {
        OrderDate parsedOrderDate = parse(orderDate);

        validateOrderDate(parsedOrderDate);

        return parsedOrderDate;
    }

    private OrderDate parse(String orderDate) {
        LocalDateTime orderDateValue;

        try {
            orderDateValue = ZonedDateTime.parse(orderDate, DATE_TIME_FORMATTER).toLocalDateTime();
        } catch (Exception exception) {
            throw new InvalidFormatException();
        }

        return new OrderDate(orderDateValue);
    }

    private void validateOrderDate(OrderDate orderDate) {
        OrderDate startOrderDate = festivalConfiguration.getStartOrderDate();
        OrderDate endOrderDate = festivalConfiguration.getEndOrderDate();

        if (!orderDate.isBetweenOrEquals(startOrderDate, endOrderDate)) {
            throw new InvalidOrderDateException();
        }
    }
}
